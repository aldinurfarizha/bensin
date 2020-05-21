package com.example.bensin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class full_harian extends AppCompatActivity {
WebView chart_harian;
ProgressBar progress_harian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_harian);
        chart_harian=(WebView)findViewById(R.id.grafik_harian);
        progress_harian=(ProgressBar)findViewById(R.id.progres_harian);
        chart_harian.setWebViewClient(new full_harian.myWebclient());
        chart_harian.getSettings().setJavaScriptEnabled(true);
        chart_harian.loadUrl(URLs.GET_CHART_HARIAN_FULL);
    }
    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progress_harian.setVisibility(View.GONE);
            chart_harian.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(full_harian.this, "Koneksi Error,Tunggu Sebentar", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    chart_harian.setWebViewClient(new myWebclient());
                    chart_harian.getSettings().setJavaScriptEnabled(true);
                    chart_harian.loadUrl(URLs.GET_CHART_HARIAN);
                }
            }, 3000);

        }
    }
}
