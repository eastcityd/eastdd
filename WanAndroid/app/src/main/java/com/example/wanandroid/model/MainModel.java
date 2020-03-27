package com.example.wanandroid.model;

import android.content.Context;

import com.example.wanandroid.Interface.IBaseCallBack;
import com.example.wanandroid.bean.ExitBean;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.bean.MainArticleInfoBean;
import com.example.wanandroid.bean.MainBannerListBean;
import com.example.wanandroid.bean.ProjectCommunityInfoBean;
import com.example.wanandroid.bean.TreeInfoBean;
import com.example.wanandroid.bean.RegisterBean;
import com.example.wanandroid.bean.ResultBean;
import com.example.wanandroid.config.UnifiedService;
import com.example.wanandroid.mvp.RXModel;
import com.example.wanandroid.network.MyObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hwq
 * @date 2019/12/29.
 * GitHub：
 * Email：
 * Description：
 */
public class MainModel extends RXModel {

    public MainModel(Context context) {
        super(context);
    }

    /**
     * 获取首页轮播图列表
     * @param iBaseCallBack
     */
    public void getBannerList(final IBaseCallBack<MainBannerListBean> iBaseCallBack) {
        getServices(UnifiedService.class, PROJECT_TYPE.WANANDROID)
                .getBannerList("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<MainBannerListBean>(context) {
                    @Override
                    public void succeed(MainBannerListBean bannerListBean) {
                        iBaseCallBack.onSuccess(bannerListBean);
                    }

                    @Override
                    public void failed(ResultBean resultBean) {
                        iBaseCallBack.onFailure(resultBean);
                    }

                    @Override
                    public void error(Throwable e) {
                        iBaseCallBack.onError();
                    }
                });
    }

    /**
     * 获取首页文章列表
     * @param p             页码
     * @param iBaseCallBack
     */
    public void getMainArticleList(String p, final IBaseCallBack<MainArticleInfoBean> iBaseCallBack) {
        getServices(UnifiedService.class, PROJECT_TYPE.WANANDROID)
                .getMainArticleList(p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<MainArticleInfoBean>(context){
                    @Override
                    public void succeed(MainArticleInfoBean mainArticleInfoBean) {
                        iBaseCallBack.onSuccess(mainArticleInfoBean);
                    }

                    @Override
                    public void failed(ResultBean resultBean) {
                        iBaseCallBack.onFailure(resultBean);
                    }

                    @Override
                    public void error(Throwable e) {
                        iBaseCallBack.onError();
                    }
                });
    }

    /**
     * 获取知识体系树
     * @param iBaseCallBack
     */
    public void getTree(final IBaseCallBack<TreeInfoBean> iBaseCallBack) {
        getServices(UnifiedService.class, PROJECT_TYPE.WANANDROID)
                .getTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<TreeInfoBean>(context) {
                    @Override
                    public void succeed(TreeInfoBean projectSystemTreeBean) {
                        iBaseCallBack.onSuccess(projectSystemTreeBean);
                    }

                    @Override
                    public void failed(ResultBean resultBean) {
                        iBaseCallBack.onFailure(resultBean);
                    }

                    @Override
                    public void error(Throwable e) {
                        iBaseCallBack.onError();
                    }
                });
    }

    /**
     * 获取社区文章列表
     * @param p  页码
     * @param iBaseCallBack
     */
    public void getProjectCommunityList(String p, final IBaseCallBack<ProjectCommunityInfoBean>
            iBaseCallBack) {
        getServices(UnifiedService.class, PROJECT_TYPE.WANANDROID)
                .getProjectCommunityList(p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<ProjectCommunityInfoBean>(context) {
                    @Override
                    public void succeed(ProjectCommunityInfoBean projectCommunityInfoBean) {
                        iBaseCallBack.onSuccess(projectCommunityInfoBean);
                    }

                    @Override
                    public void failed(ResultBean resultBean) {
                        iBaseCallBack.onFailure(resultBean);
                    }

                    @Override
                    public void error(Throwable e) {
                        iBaseCallBack.onError();
                    }
                });
    }

    /**
     * 登录注册退出
     * @param username
     * @param password
     * @param iBaseCallBack
     */

    public void Login(String username, String password,
                      final IBaseCallBack<LoginBean> iBaseCallBack) {
        getServices(UnifiedService.class, PROJECT_TYPE.WANANDROID)
                .Login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<LoginBean>(context) {
                    @Override
                    public void succeed(LoginBean loginBean) {
                        iBaseCallBack.onSuccess(loginBean);
                    }

                    @Override
                    public void failed(ResultBean resultBean) {
                        iBaseCallBack.onFailure(resultBean);
                    }

                    @Override
                    public void error(Throwable e) {
                        iBaseCallBack.onError();
                    }
                });
    }

    public void Register(String username, String password, String repassword,
                         final IBaseCallBack<RegisterBean> iBaseCallBack) {
        getServices(UnifiedService.class, PROJECT_TYPE.WANANDROID)
                .Register(username, password, repassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<RegisterBean>(context) {
                    @Override
                    public void succeed(RegisterBean registerBean) {
                        iBaseCallBack.onSuccess(registerBean);
                    }

                    @Override
                    public void failed(ResultBean resultBean) {
                        iBaseCallBack.onFailure(resultBean);
                    }

                    @Override
                    public void error(Throwable e) {
                        iBaseCallBack.onError();
                    }
                });
    }

    public void Exit(final IBaseCallBack<ExitBean> iBaseCallBack){
        getServices(UnifiedService.class, PROJECT_TYPE.WANANDROID)
                .Exit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<ExitBean>(context) {
                    @Override
                    public void succeed(ExitBean exitBean) {
                        iBaseCallBack.onSuccess(exitBean);
                    }

                    @Override
                    public void failed(ResultBean resultBean) {
                        iBaseCallBack.onFailure(resultBean);
                    }

                    @Override
                    public void error(Throwable e) {
                        iBaseCallBack.onError();
                    }
                });
    }
}
