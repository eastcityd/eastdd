package com.example.wanandroid.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * @author hwq
 * @date 2020/1/5.
 * GitHub：
 * Email：
 * Description：
 */
public class ProgressWebView extends WebView {

    //private final DialogUtil mDialogUtil;
    private ProgressBar progressbar;
    private setWebTitleListen titlisten;
    private String title = "";
    private boolean isClearHistory = false;
    private OnScrollChangeListener mOnScrollChangeListener;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                1, 0, 0));
        addView(progressbar);
        // setWebViewClient(new WebViewClient(){});
        //mDialogUtil = new DialogUtil(context);
        setWebChromeClient(new WebChromeClient());
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
                if(isClearHistory){
                    clearHistory();
                    isClearHistory = false;
                }
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
			/*if (newProgress>=80){
				mDialogUtil.dismiss();
			}*/
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            // TODO Auto-generated method stub
            super.onReceivedTitle(view, title);
            if (titlisten != null) {
                titlisten.setWebTitle(title);
            }
        }

    }

    public void setTitleListen(setWebTitleListen titleListen) {
        this.titlisten = titleListen;
    }

    /**
     * 朱 设置网页标题，2015.11.25
     */
    public interface setWebTitleListen {
        void setWebTitle(String title);
    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // webview的高度
        float webcontent = getContentHeight() * getScale();
        // 当前webview的高度
        float webnow = getHeight() + getScrollY();
        if (null!=mOnScrollChangeListener){
            if (Math.abs(webcontent - webnow) < 1) {
                //处于底端
                mOnScrollChangeListener.onPageEnd(l, t, oldl, oldt);
            } else if (getScrollY() == 0) {
                //处于顶端
                mOnScrollChangeListener.onPageTop(l, t, oldl, oldt);
            } else {
                mOnScrollChangeListener.onScrollChanged(l, t, oldl, oldt);
            }
        }
    }

    public void setOnScrollChangeListener(OnScrollChangeListener listener) {
        this.mOnScrollChangeListener = listener;
    }
    public interface OnScrollChangeListener {
        public void onPageEnd(int l, int t, int oldl, int oldt);
        public void onPageTop(int l, int t, int oldl, int oldt);
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
