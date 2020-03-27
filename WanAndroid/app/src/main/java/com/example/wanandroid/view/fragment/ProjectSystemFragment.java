package com.example.wanandroid.view.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;

import com.example.wanandroid.dialog.ConfirmCancelDialog;
import com.example.wanandroid.mvp.BaseFragment;
import com.example.wanandroid.view.activity.LbsActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectSystemFragment extends BaseFragment{

    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.bt_start)
    Button bt_start;
    @BindView(R.id.bt_reset)
    Button bt_reset;
    @BindView(R.id.bt_cancel)
    Button bt_cancel;
    @BindView(R.id.btn_lbs) Button btn_lbs;


    private String str;
    private Timer timer;
    private static int count = 10;
    private String cancelstring;
    private Timer canceltimer;
    private static int COUNT;

    private ConfirmCancelDialog confirmCancelDialog;

    @Override
    public int getContentViewId() {
        return R.layout.project_fragment_system;
    }

    @Override
    public void initAllMembersView(Bundle var1) {
        ButterKnife.bind(this, rootView);

        initCountDown();

        initReset();

        initCancel();

        btn_lbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LbsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initCancel() {
        confirmCancelDialog = new ConfirmCancelDialog(context);
        confirmCancelDialog.setTitle("删除");
        confirmCancelDialog.setContent("是否自动删除？");
        confirmCancelDialog.setCancelText("取消");
        confirmCancelDialog.create();
        confirmCancelDialog.setCanceledOnTouchOutside(false);
        confirmCancelDialog.setListener(new ConfirmCancelDialog.OnConfirmCancelListener() {
            @Override
            public void onConfirm() {
                confirmCancelDialog.dismiss();
                canceltimer.cancel();
            }

            @Override
            public void onCancel() {
                confirmCancelDialog.dismiss();
                canceltimer.cancel();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmCancelDialog.show();

                initCancelTimer();
            }
        });
    }

    private void initCancelTimer(){
        COUNT = 3;
        canceltimer = new Timer();
        canceltimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = COUNT;
                if (COUNT != -2){
                    COUNT --;
                }else {
                    return;
                }
                cancelhandler.sendMessage(message);
            }
        }, 0, 1000);
    }
    private Handler cancelhandler = new Handler(){
        public void handleMessage(Message msg){
            int COUNT = msg.arg1;
            if (COUNT == -1){
                Toast.makeText(context, "未点取消，已自动取消", Toast.LENGTH_SHORT).show();
                confirmCancelDialog.dismiss();
                canceltimer.cancel();
            }else {
                cancelstring = "取消（" + COUNT + "秒）";
                confirmCancelDialog.setCancelText(cancelstring);
            }
        }
    };

    private void initCountDown() {
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = bt_start.getText().toString();
                if (str.equals("开始")){
                    bt_start.setText("暂停");
                    initView();
                }else {
                    bt_start.setText("开始");
                    timer.cancel();
                }
            }
        });
    }

   private void initView(){
        timer = new Timer();

         //每一秒发送一次消息给handler更新UI
         //schedule(TimerTask task, long delay, long period)

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = count;
                if (count != -2){
                    count --;
                }else {
                    return;
                }
                handler.sendMessage(message);
            }
        }, 0, 1000);
    }
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            int count = msg.arg1;
            if (count == -1){
                Toast.makeText(context, "倒计时结束", Toast.LENGTH_SHORT).show();
            }else {
                tv_time.setText(count+"");
            }
        }
    };

    private void initReset() {
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count !=10){
                    timer.cancel();
                }
                bt_start.setText("开始");
                tv_time.setText("10");
                count = 10;
            }
        });
    }
}
