package com.example.wanandroid.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.example.wanandroid.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;


/**
 * 确认取消Dialog
 * Created by ZhuYongdi on 2019/5/21.
 */
public class ConfirmCancelDialog extends Dialog {

    protected View view;
    protected LinearLayout ll_content;
    protected TextView tv_hint;
    protected TextView tv_content;
    protected TextView tv_line;
    protected LinearLayout ll_confirm_cancel;
    protected TextView tv_add_black;
    protected TextView tv_confirm;
    protected View view_space;
    protected TextView tv_cancel;
    protected TextView tv_confirm_not_Cancel;


    private OnConfirmCancelListener listener;

    public ConfirmCancelDialog(@NonNull Context context) {
        super(context);
        view = LayoutInflater.from(context).inflate(R.layout.dialog_confirm_cancel, null);
        ScreenAdapterTools.getInstance().loadView(view);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        window.setContentView(view);
        window.setGravity(Gravity.CENTER);
        initUI();
    }

    private void initUI() {
        ll_content = view.findViewById(R.id.content_ll);
        tv_hint = view.findViewById(R.id.tv_hint);
        tv_content = view.findViewById(R.id.tv_content);
        tv_line = view.findViewById(R.id.line_tv);
        ll_confirm_cancel = view.findViewById(R.id.ll_confirm_cancel);
        tv_add_black = view.findViewById(R.id.tv_add_black);
        tv_confirm = view.findViewById(R.id.tv_confirm);
        view_space = view.findViewById(R.id.space_view);
        tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_confirm_not_Cancel = view.findViewById(R.id.tv_confirm2);
        tv_add_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onConfirm();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onConfirm();
                }
            }
        });
        tv_confirm_not_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onConfirm();
                }
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCancel();
                }
            }
        });
    }

    public void setTitle(String title) {
        tv_hint.setText(title);
    }

    public void setTitleGravity(int gravity) {
        tv_hint.setGravity(gravity);
    }

    public void setContent(String content) {
        tv_content.setText(content);
    }

    public void setContent(@StringRes int content) {
        tv_content.setText(content);
    }

    public void setConfirmText(String content) {
        tv_confirm.setText(content);
    }

    public void setConfirmText(@StringRes int content) {
        tv_confirm.setText(content);
    }

    public void setCancelText(String content) {
        tv_cancel.setText(content);
    }

    public void setCancelText(@StringRes int content) {
        tv_cancel.setText(content);
    }

    public void setConfirmPadding(int left, int top, int right, int bottom) {
        tv_confirm.setPadding(left, top, right, bottom);
    }

    public void setListener(OnConfirmCancelListener listener) {
        this.listener = listener;
    }

    public void setConfirmOnly() {
        ll_confirm_cancel.setVisibility(View.GONE);
        tv_confirm_not_Cancel.setVisibility(View.VISIBLE);
    }

    public interface OnConfirmCancelListener {

        void onConfirm();

        void onCancel();

    }

}
