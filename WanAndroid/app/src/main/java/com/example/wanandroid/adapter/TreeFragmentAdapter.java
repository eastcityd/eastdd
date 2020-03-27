package com.example.wanandroid.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wanandroid.view.fragment.ProjectCommunityFragment;
import com.example.wanandroid.view.fragment.ProjectSystemFragment;
import com.example.wanandroid.view.fragment.TreeInfoFragment;


public class TreeFragmentAdapter extends FragmentPagerAdapter {

    private final String[] mTitles;

    public TreeFragmentAdapter(@NonNull FragmentManager fm, String[] titles) {
        super(fm);
        this.mTitles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new TreeInfoFragment();
        }else if (position == 1){
            return new ProjectSystemFragment();
        }
        return new ProjectCommunityFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mTitles[position];
    }

}
