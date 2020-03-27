package com.example.wanandroid.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.example.wanandroid.mvp.BaseFragment;
import com.example.wanandroid.presenter.MainPresenter;
import com.example.wanandroid.view.MainActivity;
import com.example.wanandroid.view.activity.LoginActivity;
import com.example.wanandroid.view.activity.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends BaseFragment implements MainView {

    @BindView(R.id.img_login) ImageView img_login;
    @BindView(R.id.nickname) TextView nickname;
    @BindView(R.id.tv_register) TextView tv_register;

    private boolean isLogin = false;
    private LoginBean loginBean;

    private MainPresenter presenter;

    @Override
    public int getContentViewId() {
        return R.layout.mine_fragment;
    }

    @Override
    public void initAllMembersView(Bundle var1) {
        ButterKnife.bind(this, rootView);
        loginBean = SpConfig.getInstance(context).getLoginBean();
        if (loginBean != null){
            nickname.setText(loginBean.getData().getNickname());
            img_login.setImageResource(R.drawable.ic_user);
            tv_register.setText("退出");
            isLogin = true;
        }else {
            img_login.setImageResource(R.drawable.ic_nologinuser);
            tv_register.setText("注册");
            nickname.setText("点击头像登录");
            isLogin = false;
        }
        presenter = new MainPresenter(this);
        initClick();

    }

    private void initClick() {
        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin == false){
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivityForResult(intent, 1);
                }
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin == false){
                    Intent intent = new Intent(context, RegisterActivity.class);
                    startActivityForResult(intent, 2);
                }else {
                    presenter.Exit();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                loginBean = data.getParcelableExtra("loginbean");
                nickname.setText(loginBean.getData().getNickname());
                img_login.setImageResource(R.drawable.ic_user);
                tv_register.setText("退出");
                isLogin = true;
                ((MainActivity)getAcitvity()).setLoginData(loginBean);
            }else if (requestCode == 2){
                String user = data.getStringExtra("username");
                String pass = data.getStringExtra("password");
                presenter.Login(user, pass);
                nickname.setText(user);
                img_login.setImageResource(R.drawable.ic_user);
                tv_register.setText("退出");
                isLogin = true;
                ((MainActivity)getAcitvity()).setRegisterData(user);
            }
        }
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

    }

    @Override
    public void Exit(ExitBean exitBean) {
        int code = exitBean.getErrorCode();
        if (code == 0){
            img_login.setImageResource(R.drawable.ic_nologinuser);
            tv_register.setText("注册");
            nickname.setText("点击头像登录");
            isLogin = false;
            ((MainActivity)getAcitvity()).clearLoginData();
        }
    }

    public void setLoginData(LoginBean loginBean){
        nickname.setText(loginBean.getData().getNickname());
        img_login.setImageResource(R.drawable.ic_user);
        tv_register.setText("退出");
        isLogin = true;
    }
}
