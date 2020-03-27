package com.example.wanandroid.view.fragment;


import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.TreeFragmentAdapter;
import com.example.wanandroid.mvp.BaseFragment;
import com.google.android.material.tabs.TabLayout;


import butterknife.BindView;
import butterknife.ButterKnife;

public class TreeFragment extends BaseFragment {

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.view_pager) ViewPager viewPager;

    @Override
    public int getContentViewId() {
        return R.layout.tree_fragment;
    }

    @Override
    public void initAllMembersView(Bundle var1) {
        ButterKnife.bind(this, rootView);

        initData();
    }


    private void initData(){
        String[] mTitle = getResources().getStringArray(R.array.project_top_tab);
        viewPager.setAdapter(new TreeFragmentAdapter(getChildFragmentManager(), mTitle));
        tabLayout.setupWithViewPager(viewPager);
    }
}
