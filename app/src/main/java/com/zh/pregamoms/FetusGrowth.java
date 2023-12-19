package com.zh.pregamoms;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.monstertechno.adblocker.AdBlockerWebView;
import com.monstertechno.adblocker.util.AdBlocker;

import static com.zh.pregamoms.R.layout;

public class FetusGrowth extends AppCompatActivity {
    private WebView webView;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_fetus_growth);

        webView=findViewById(R.id.webview1);

        progressDialog = new ProgressDialog(FetusGrowth.this);

        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setBlockNetworkImage(false);

        new AdBlockerWebView.init(this).initializeWebView(webView);
        webView.setWebViewClient(new FetusGrowth.Browser_home(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);


            }


            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                if (progressDialog != null){
                    progressDialog.dismiss();
                }

            }


        });
        webView.loadUrl("https://www.babycenter.com/pregnancy/your-baby/fetal-development-week-by-week_10406730");
    }

    static class Browser_home extends WebViewClient {

        Browser_home() {}

        @SuppressWarnings("deprecation")
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

            return AdBlockerWebView.blockAds(view,url) ? AdBlocker.createEmptyResource() :
                    super.shouldInterceptRequest(view, url);

        }

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}