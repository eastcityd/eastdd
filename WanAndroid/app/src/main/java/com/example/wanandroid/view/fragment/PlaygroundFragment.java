package com.example.wanandroid.view.fragment;

import android.os.Bundle;
import android.os.Handler;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wanandroid.Interface.MainView;
import com.example.wanandroid.R;
import com.example.wanandroid.adapter.CommunityAdapter;
import com.example.wanandroid.bean.ExitBean;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.bean.MainArticleInfoBean;
import com.example.wanandroid.bean.MainBannerListBean;
import com.example.wanandroid.bean.ProjectCommunityBean;
import com.example.wanandroid.bean.ProjectCommunityInfoBean;
import com.example.wanandroid.bean.TreeInfoBean;
import com.example.wanandroid.bean.RegisterBean;
import com.example.wanandroid.mvp.BaseFragment;
import com.example.wanandroid.presenter.MainPresenter;
import com.example.wanandroid.view.activity.WebActivity;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaygroundFragment extends BaseFragment implements MainView {

    @BindView(R.id.srl_playground)
    SwipeRefreshLayout srl_playground;
    @BindView(R.id.rlv_playground)
    SwipeRecyclerView rlv_playground;

    private MainPresenter presenter;
    private CommunityAdapter adapter;
    private int p = 0;
    private ArrayList<ProjectCommunityBean> articleList = new ArrayList<>();

    @Override
    public int getContentViewId() {
        return R.layout.playground_fragment;
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
        srl_playground.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl_playground.setRefreshing(false);
                        p = 1;
                        presenter.getMainArticleList(p);
                    }
                }, 500);
            }
        });

        rlv_playground.setLoadMoreListener(()->
                new Handler().postDelayed(()->
                        presenter.getMainArticleList(p), 500));

        adapter.setOnItemClickListener(position ->
                startActivity(WebActivity.createIntent(context,articleList.get(position).getLink())));
    }

    private void initList() {
        adapter = new CommunityAdapter(getContext(), articleList);
        rlv_playground.loadMoreFinish(false, true);
        rlv_playground.setLayoutManager(new LinearLayoutManager(getContext()));
        rlv_playground.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(context, R.color.transparent), 0, 20));
        rlv_playground.setAdapter(adapter);
    }

    private void initView() {
    }

    private void initData() {
        presenter = new MainPresenter(this);
        presenter.getProjectCommunityList(p);
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
        if (p == 0){
            articleList.clear();
        }
        if (projectCommunityInfoBean.getData().isOver()){
            rlv_playground.loadMoreFinish(true, false);
        }else {
            rlv_playground.loadMoreFinish(false, true);
            p++;
        }
        articleList.addAll(projectCommunityInfoBean.getData().getDatas());
        adapter.notifyDataSetChanged();
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
}
