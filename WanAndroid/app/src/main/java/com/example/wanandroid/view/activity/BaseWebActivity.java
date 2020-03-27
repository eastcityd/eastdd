package com.example.wanandroid.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wanandroid.mvp.BaseActivity;
import com.example.wanandroid.utils.log;
import com.example.wanandroid.view.widget.ProgressWebView;

/**
 * @author hwq
 * @date 2020/1/5.
 * GitHub：
 * Email：
 * Description：
 */
public class BaseWebActivity extends BaseActivity {

    /**
     * webview的操作
     */
    @SuppressLint("JavascriptInterface")
    protected void webprocess(final ProgressWebView webview_pv, String url) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            webview_pv.getSettings().setSafeBrowsingEnabled(false);
        }
        WebSettings webSettings = webview_pv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        webSettings.setSavePassword(true);

        webSettings.setSupportZoom(true); //支持缩放，默认为true。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview_pv.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                webview_pv.requestFocus();
                return false;
            }

        });
        webview_pv.setWebViewClient(new LeonWebViewClient());
        webview_pv.setVisibility(View.VISIBLE);
        webview_pv.clearView();
        webview_pv.loadUrl(url);
    }

    private class LeonWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            log.e("url:"+url);
            view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
        /*    if(loaDialogUtil.isShow()){
                loaDialogUtil.dismiss();
            }*/
            view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            view.getSettings().setBlockNetworkImage(false);
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                       SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onLoadResource(WebView view, String url) {

            super.onLoadResource(view, url);

        }

    }

    public class jsInterface {
        Context context;

        public jsInterface(Context context) {
            super();
            this.context = context;
        }

        @JavascriptInterface
        public void demo(){

        }
    }
}
