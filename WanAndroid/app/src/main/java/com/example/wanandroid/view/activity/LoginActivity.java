package com.example.wanandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.wanandroid.Interface.MainView;
import com.example.wanandroid.R;
import com.example.wanandroid.bean.ExitBean;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.bean.MainArticleInfoBean;
import com.example.wanandroid.bean.MainBannerListBean;
import com.example.wanandroid.bean.ProjectCommunityInfoBean;
import com.example.wanandroid.bean.TreeInfoBean;
import com.example.wanandroid.bean.RegisterBean;
import com.example.wanandroid.config.SpConfig;
import com.example.wanandroid.mvp.BaseActivity;
import com.example.wanandroid.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements MainView {

    @BindView(R.id.head_img_left) ImageView iv_back;
    @BindView(R.id.et_username) EditText et_username;
    @BindView(R.id.et_password) EditText et_password;
    @BindView(R.id.btn_login) Button btn_login;
    @BindView(R.id.btn_register) Button btn_register;

    private Context context;
    private MainPresenter presenter = new MainPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        context = this;
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                presenter.Login(username, password);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void getBannerList(MainBannerListBean bannerListBean) {}

    @Override
    public void getMainArticleList(MainArticleInfoBean mainArticleInfoBean) {}

    @Override
    public void getTree(TreeInfoBean treeInfoBean) {

    }


    @Override
    public void getProjectCommunityList(ProjectCommunityInfoBean projectCommunityInfoBean) {}

    @Override
    public void Login(LoginBean loginBean) {
        int code = loginBean.getErrorCode();
        SpConfig.getInstance(context).saveLoginBean(loginBean);
        if (code == 0) {
            Intent data = new Intent();
            data.putExtra("loginbean", loginBean);
            setResult(RESULT_OK, data);
            finish();
        } else {
            showToast(loginBean.getErrorMsg());
        }
    }

    @Override
    public void Register(RegisterBean registerBean) {}

    @Override
    public void Exit(ExitBean exitBean) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 2){
                String user = data.getStringExtra("username");
                String pass = data.getStringExtra("password");
                et_username.setText(user);
                et_password.setText(pass);
                presenter.Login(user, pass);
            }
        }
    }
}

