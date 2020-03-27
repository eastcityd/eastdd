package com.example.wanandroid.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.wanandroid.Interface.MainView;
import com.example.wanandroid.R;
import com.example.wanandroid.adapter.ArticleAdapter;
import com.example.wanandroid.bean.ExitBean;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.bean.MainArticleBean;
import com.example.wanandroid.bean.MainArticleInfoBean;
import com.example.wanandroid.bean.MainBannerBean;
import com.example.wanandroid.bean.MainBannerListBean;
import com.example.wanandroid.bean.ProjectCommunityInfoBean;
import com.example.wanandroid.bean.TreeInfoBean;
import com.example.wanandroid.bean.RegisterBean;
import com.example.wanandroid.mvp.BaseFragment;
import com.example.wanandroid.presenter.MainPresenter;
import com.example.wanandroid.view.activity.WebActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hwq
 * @date 2019/12/28.
 * GitHub：
 * Email：
 * Description：首页fragment
 */
public class MainFragment extends BaseFragment implements MainView {

    @BindView(R.id.banner_view) BannerViewPager bannerViewPager;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.appBarLayout) AppBarLayout appBarLayout;
    @BindView(R.id.rlv_main) SwipeRecyclerView rlv_main;

    private MainPresenter presenter;
    private ArticleAdapter adapter;
    private int p = 0;
    private ArrayList<MainArticleBean> articleList = new ArrayList<>();

    @Override
    public int getContentViewId() {
        return R.layout.main_fragment;
    }

    @Override
    public void initAllMembersView(Bundle var1) {
        ButterKnife.bind(this, rootView);

        initData();

        initView();

        initList();

        initListener();
    }

    private void initListener() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipeRefreshLayout.setEnabled(true);
                } else {
                    swipeRefreshLayout.setEnabled(false);
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        p = 1;
                        presenter.getMainArticleList(p);
                    }
                }, 500);
            }
        });

        rlv_main.setLoadMoreListener(()->
                new Handler().postDelayed(()->
                        presenter.getMainArticleList(p), 500));

        adapter.setOnItemClickListener(position ->
                startActivity(WebActivity.createIntent(context,articleList.get(position).getLink())));
    }

    private void initList() {
        adapter = new ArticleAdapter(getContext(), articleList);
        rlv_main.loadMoreFinish(false, true);
        rlv_main.setLayoutManager(new LinearLayoutManager(getContext()));
        rlv_main.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(context,R.color.transparent),
                0, 20));
        rlv_main.setAdapter(adapter);
    }

    private void initView() {
    }

    private void initData() {
        presenter = new MainPresenter(this);
        presenter.getBannerList();
        presenter.getMainArticleList(p);
    }

    @Override
    public void getBannerList(MainBannerListBean bannerListBean) {
        bannerViewPager.setInterval(3000)
                .setCanLoop(true)
                .setAutoPlay(true)
                .setIndicatorColor(Color.parseColor("#ffffff"), Color.parseColor("#000000"))
//                .setIndicatorGravity(IndicatorGravity.END)
                .setScrollDuration(1000)
                .setPageMargin(BannerUtils.dp2px(0))
                .setHolderCreator(MainBannerViewHolder::new)
                .setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
                    @Override
                    public void onPageClick(int position) {
                        startActivity(WebActivity.createIntent(context,bannerListBean.getData().get(position).getUrl()));
                    }
                }).create(bannerListBean.getData());
    }

    @Override
    public void getMainArticleList(MainArticleInfoBean mainArticleInfoBean) {
        if (p == 0) {
            articleList.clear();
        }
        if (mainArticleInfoBean.getData().isOver()) {
            rlv_main.loadMoreFinish(true,false);
        } else {
            rlv_main.loadMoreFinish(false,true);
            p++;
        }
        articleList.addAll(mainArticleInfoBean.getData().getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getTree(TreeInfoBean projectSystemTreeBean) {

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

    }

    public class MainBannerViewHolder implements ViewHolder<MainBannerBean> {
        @Override
        public int getLayoutId() {
            return R.layout.main_banner_item;
        }

        @Override
        public void onBind(View itemView, MainBannerBean data, int position, int size) {
            ImageView imageView = itemView.findViewById(R.id.iv_banner_img);
            TextView textView = itemView.findViewById(R.id.tv_banner_title);
            Glide.with(context)
                    .load(data.getImagePath())
                    .into(imageView);
            textView.setText(data.getTitle());
        }
    }

}
