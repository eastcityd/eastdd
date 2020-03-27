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
import com.example.wanandroid.mvp.BaseActivity;
import com.example.wanandroid.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity implements MainView {

    @BindView(R.id.head_img_left) ImageView register_back;
    @BindView(R.id.et_username) EditText et_username;
    @BindView(R.id.et_password) EditText et_password;
    @BindView(R.id.et_repassword) EditText et_repassword;
    @BindView(R.id.btn_login) Button btn_login;
    @BindView(R.id.btn_register) Button btn_register;

    private MainPresenter presenter = new MainPresenter(this);

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        ButterKnife.bind(this);
        context = this;

        initListener();

    }

    private void initListener() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String repassword = et_repassword.getText().toString().trim();
                presenter.Register(username, password, repassword);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getBannerList(MainBannerListBean bannerListBean) {

    }

    @Override
    public void getMainArticleList(MainArticleInfoBean mainArticleInfoBean) {

    }

    @Override
    public void getTree(TreeInfoBean treeInfoBean) {

    }


    @Override
    public void getProjectCommunityList(ProjectCommunityInfoBean projectCommunityInfoBean) {

    }

    @Override
    public void Login(LoginBean loginBean) {

    }

    @Override
    public void Register(RegisterBean registerBean) {
        int code = registerBean.getErrorCode();
        if (code == 0){
            Intent data = new Intent();
            data.putExtra("username", et_username.getText().toString());
            data.putExtra("password", et_password.getText().toString());
            setResult(RESULT_OK, data);
            finish();
        }else {
            showToast(registerBean.getErrorMsg());
        }
    }

    @Override
    public void Exit(ExitBean exitBean) {

    }
}
