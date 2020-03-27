package com.example.wanandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.wanandroid.R;
import com.example.wanandroid.view.widget.ProgressWebView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hwq
 * @date 2020/1/5.
 * GitHub：
 * Email：
 * Description：
 */
public class WebActivity extends BaseWebActivity {

    @BindView(R.id.head_text_center) TextView tv_title;
    @BindView(R.id.webview) ProgressWebView webView;

    private Context context;
    private String url;

    public static Intent createIntent(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url",url);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        ButterKnife.bind(this);
        context = this;

        initData();

        initView();

        initListener();
    }

    private void initListener() {
        webView.setTitleListen((String title) ->
                tv_title.setText(title));
    }

    private void initView() {
        webprocess(webView,url);
    }

    private void initData() {
        url = getIntent().getStringExtra("url");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.head_ll_left})
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.head_ll_left:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                break;
        }
    }
}
