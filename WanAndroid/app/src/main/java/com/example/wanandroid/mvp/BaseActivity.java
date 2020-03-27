package com.example.wanandroid.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.example.wanandroid.dialog.LoadDialog;
import com.example.wanandroid.utils.ScreenUtil;

public class BaseActivity extends AppCompatActivity implements IBaseView {

    public boolean isSetStatusBar = true;
    private LoadDialog loadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDialog=new LoadDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isSetStatusBar) {
            setTextStatusBar();
        }
    }

    public void setTextStatusBar() {
        setTextStatusBar(R.id.head_empty);
    }

    public void setImageStatusBar() {
        setImageStatusBar(R.id.head_empty);
    }

    public void setTextStatusBar(int emptyID){
        ScreenUtil.setHead(this,findViewById(emptyID),
                android.R.color.white,ScreenUtil.StatusBarStyle.COLOR);
        ScreenUtil.setStatusBarLightMode(this);
    }

    public void setImageStatusBar(int emptyID){
        ScreenUtil.setHead(this, findViewById(emptyID),
                android.R.color.transparent, ScreenUtil.StatusBarStyle.IMAGE);
        ScreenUtil.setStatusBarDarkMode(this);
    }

    @Override
    public void showLoading() {
        if(!loadDialog.isShowing()){
            loadDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if(loadDialog.isShowing()){
            loadDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        try {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Looper.prepare();
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    @Override
    public void showErr(String msg) {
        if(msg!=null&&!msg.isEmpty()){
            Toast.makeText(getContext(),msg, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(),"发生错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getAcitvity() {
        return this;
    }


    @Override
    protected void onDestroy() {
        if(loadDialog.isShowing()){
            loadDialog.dismiss();
        }
        super.onDestroy();
    }
}