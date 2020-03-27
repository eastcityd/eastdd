package com.example.wanandroid.view.fragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.Interface.MainView;
import com.example.wanandroid.R;
import com.example.wanandroid.adapter.TreeInfoAdapter;
import com.example.wanandroid.bean.ExitBean;
import com.example.wanandroid.bean.LoginBean;
import com.example.wanandroid.bean.MainArticleInfoBean;
import com.example.wanandroid.bean.MainBannerListBean;
import com.example.wanandroid.bean.ProjectCommunityInfoBean;
import com.example.wanandroid.bean.RegisterBean;
import com.example.wanandroid.bean.TreeBean;
import com.example.wanandroid.bean.TreeInfoBean;
import com.example.wanandroid.mvp.BaseFragment;
import com.example.wanandroid.presenter.MainPresenter;
import com.example.wanandroid.view.widget.FlowLayout;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TreeInfoFragment extends BaseFragment implements MainView {

    @BindView(R.id.rlv_tree)
    RecyclerView rlv_tree;

    private TreeInfoAdapter treeInfoAdapter;
    private MainPresenter presenter;
    private ArrayList<TreeBean> treeBeans;

    @Override
    public int getContentViewId() {
        return R.layout.tree_info_fragment;
    }

    @Override
    public void initAllMembersView(Bundle var1) {
        ButterKnife.bind(this, rootView);

        initData();

    }

    private void initData() {
        rlv_tree.setLayoutManager(new LinearLayoutManager(context));
        treeBeans = new ArrayList<>();
        treeInfoAdapter = new TreeInfoAdapter(context, treeBeans);
        rlv_tree.setAdapter(treeInfoAdapter);
        presenter = new MainPresenter(this);
        presenter.getTree();

        treeInfoAdapter.setOnItemClickListener(new TreeInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TreeBean bean) {
                Toast.makeText(context, "暂未实现", Toast.LENGTH_SHORT).show();
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
        List<TreeBean> data = treeInfoBean.getData();
        treeBeans.clear();
        treeBeans.addAll(data);
        treeInfoAdapter.notifyDataSetChanged();
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
}
