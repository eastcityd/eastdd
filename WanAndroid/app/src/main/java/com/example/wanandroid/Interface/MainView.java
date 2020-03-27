package com.example.wanandroid.Interface;

import com.example.wanandroid.bean.ExitBean;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.bean.MainArticleInfoBean;
import com.example.wanandroid.bean.MainBannerListBean;
import com.example.wanandroid.bean.ProjectCommunityInfoBean;
import com.example.wanandroid.bean.TreeInfoBean;
import com.example.wanandroid.bean.RegisterBean;
import com.example.wanandroid.mvp.IBaseView;

/**
 * @author hwq
 * @date 2019/12/29.
 * GitHub：
 * Email：
 * Description：
 */
public interface MainView extends IBaseView {

    /**
     * 获得首页轮播图列表
     * @param bannerListBean
     */
    void getBannerList(MainBannerListBean bannerListBean);

    /**
     * 获得首页文章列表
     * @param mainArticleInfoBean
     */
    void getMainArticleList(MainArticleInfoBean mainArticleInfoBean);

    /**
     * 获得知识体系树
     * @param treeInfoBean
     */

    void getTree(TreeInfoBean treeInfoBean);

    /**
     * 获得社区文章列表
     * @param projectCommunityInfoBean
     */
    void getProjectCommunityList(ProjectCommunityInfoBean projectCommunityInfoBean);

    /**
     * 登录
     * @param loginBean
     */
    void Login(LoginBean loginBean);

    /**
     * 注册
     * @param registerBean
     */
    void Register(RegisterBean registerBean);


    /**
     * 退出登录
     * @param exitBean
     */
    void Exit(ExitBean exitBean);
}
