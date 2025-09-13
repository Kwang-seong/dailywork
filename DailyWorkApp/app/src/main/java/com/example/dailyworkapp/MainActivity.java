package com.example.dailyworkapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    private FirebaseAnalytics firebaseAnalytics; // 🔹 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 🔹 Firebase Analytics 초기화
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // 🔹 테스트용 이벤트 전송
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "main_webview");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "웹뷰 로드");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "webview");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);

        // 🔹 WebView 설정
        webView = new WebView(this);
        setContentView(webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.evaluateJavascript("loadData(Android.load())", null);
            }
        });

        webView.loadUrl("file:///android_asset/daily_report.html");
    }

    // 🔽 WebAppInterface 클래스는 그대로 두시면 됩니다
    public static class WebAppInterface {
        Context mContext;
        SharedPreferences prefs;

        WebAppInterface(Context context) {
            mContext = context;
            prefs = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        }

        @JavascriptInterface
        public void save(String text) {
            prefs.edit().putString("memo", text).apply();
            Toast.makeText(mContext, "저장됨", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public String load() {
            return prefs.getString("memo", "");
        }
    }
}
