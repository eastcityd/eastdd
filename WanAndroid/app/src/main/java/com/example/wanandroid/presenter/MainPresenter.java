package com.example.wanandroid.presenter;

import com.example.wanandroid.Interface.IBaseCallBack;
import com.example.wanandroid.Interface.MainView;
import com.example.wanandroid.bean.ExitBean;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.bean.MainArticleInfoBean;
import com.example.wanandroid.bean.MainBannerListBean;
import com.example.wanandroid.bean.ProjectCommunityInfoBean;
import com.example.wanandroid.bean.TreeInfoBean;
import com.example.wanandroid.bean.RegisterBean;
import com.example.wanandroid.bean.ResultBean;
import com.example.wanandroid.model.MainModel;
import com.example.wanandroid.mvp.BasePresenter;
import com.example.wanandroid.mvp.IBaseView;

/**
 * @author hwq
 * @date 2019/12/29.
 * GitHub：
 * Email：
 * Description：
 */
public class MainPresenter extends BasePresenter {

    private MainView iBaseView;
    private MainModel model;

    public MainPresenter(IBaseView iBaseView) {
        this.iBaseView = (MainView) iBaseView;
        model = new MainModel(iBaseView.getContext());
    }

    /**
     * 获取首页轮播图列表
     */
    public void getBannerList() {
        iBaseView.showLoading();
        model.getBannerList(new IBaseCallBack<MainBannerListBean>() {
            @Override
            public void onSuccess(MainBannerListBean data) {
                iBaseView.hideLoading();
                iBaseView.getBannerList(data);
            }

            @Override
            public void onFailure(ResultBean resultBean) {
                iBaseView.hideLoading();
                iBaseView.showToast(resultBean.getMsg());
            }

            @Override
            public void onError() {
                iBaseView.hideLoading();
                iBaseView.showErr("");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取首页文章列表
     */
    public void getMainArticleList(int p) {
        iBaseView.showLoading();
        model.getMainArticleList("" + p, new IBaseCallBack<MainArticleInfoBean>() {
            @Override
            public void onSuccess(MainArticleInfoBean data) {
                iBaseView.hideLoading();
                iBaseView.getMainArticleList(data);
            }

            @Override
            public void onFailure(ResultBean resultBean) {
                iBaseView.hideLoading();
                iBaseView.showToast(resultBean.getMsg());
            }

            @Override
            public void onError() {
                iBaseView.hideLoading();
                iBaseView.showErr("");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取知识体系树
     */

    public void getTree() {
        iBaseView.showLoading();
        model.getTree(new IBaseCallBack<TreeInfoBean>() {
            @Override
            public void onSuccess(TreeInfoBean data) {
                iBaseView.hideLoading();
                iBaseView.getTree(data);
            }

            @Override
            public void onFailure(ResultBean resultBean) {
                iBaseView.hideLoading();
                iBaseView.showToast(resultBean.getMsg());
            }

            @Override
            public void onError() {
                iBaseView.hideLoading();
                iBaseView.showErr("");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getProjectCommunityList(int p) {
        iBaseView.showLoading();
        model.getProjectCommunityList("" + p, new IBaseCallBack<ProjectCommunityInfoBean>() {
            @Override
            public void onSuccess(ProjectCommunityInfoBean data) {
                iBaseView.hideLoading();
                iBaseView.getProjectCommunityList(data);
            }

            @Override
            public void onFailure(ResultBean resultBean) {
                iBaseView.hideLoading();
                iBaseView.showToast(resultBean.getMsg());
            }

            @Override
            public void onError() {
                iBaseView.hideLoading();
                iBaseView.showErr("");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public  void Login(String username, String password){
        iBaseView.showLoading();
        model.Login("" + username, "" + password, new IBaseCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean data) {
                iBaseView.hideLoading();
                iBaseView.Login(data);
            }

            @Override
            public void onFailure(ResultBean resultBean) {
                iBaseView.hideLoading();
                iBaseView.showToast(resultBean.getMsg());
            }

            @Override
            public void onError() {
                iBaseView.hideLoading();
                iBaseView.showErr("");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public  void Register(String username, String password, String repassword){
        iBaseView.showLoading();
        model.Register("" + username, "" + password, "" + repassword, new IBaseCallBack<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean data) {
                iBaseView.hideLoading();
                iBaseView.Register(data);
            }

            @Override
            public void onFailure(ResultBean resultBean) {
                iBaseView.hideLoading();
                iBaseView.showToast(resultBean.getMsg());
            }

            @Override
            public void onError() {
                iBaseView.hideLoading();
                iBaseView.showErr("");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public  void Exit(){
        iBaseView.showLoading();
        model.Exit(new IBaseCallBack<ExitBean>() {
            @Override
            public void onSuccess(ExitBean data) {
                iBaseView.hideLoading();
                iBaseView.Exit(data);
            }

            @Override
            public void onFailure(ResultBean resultBean) {
                iBaseView.hideLoading();
                iBaseView.showToast(resultBean.getMsg());
            }

            @Override
            public void onError() {
                iBaseView.hideLoading();
                iBaseView.showErr("");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
