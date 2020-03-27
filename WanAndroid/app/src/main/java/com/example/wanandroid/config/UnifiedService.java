package com.example.wanandroid.config;

import com.example.wanandroid.bean.ExitBean;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.bean.MainArticleInfoBean;
import com.example.wanandroid.bean.MainBannerListBean;
import com.example.wanandroid.bean.ProjectCommunityInfoBean;
import com.example.wanandroid.bean.TreeInfoBean;
import com.example.wanandroid.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UnifiedService {

    @FormUrlEncoded
    @POST("banner/json")
    Observable<MainBannerListBean> getBannerList(@Field("a") String a);

    @GET("/article/list/{p}/json")
    Observable<MainArticleInfoBean> getMainArticleList(@Path("p") String p);

    @GET("/tree/json")
    Observable<TreeInfoBean> getTree();

    @GET("/user_article/list/{p}/json")
    Observable<ProjectCommunityInfoBean> getProjectCommunityList(@Path("p") String p);

    @FormUrlEncoded
    @POST("/user/login")
    Observable<LoginBean> Login(@Field("username") String username,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("/user/register")
    Observable<RegisterBean> Register(@Field("username") String username,
                                      @Field("password") String password,
                                      @Field("repassword") String repassword);

    @GET("/user/logout/json")
    Observable<ExitBean> Exit();

}
