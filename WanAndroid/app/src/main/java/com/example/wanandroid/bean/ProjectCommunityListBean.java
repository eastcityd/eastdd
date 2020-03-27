package com.example.wanandroid.bean;

import java.util.ArrayList;

public class ProjectCommunityListBean {

    private ArrayList<ProjectCommunityBean> datas;

    private int curPage;

    private int offset;

    private boolean over;

    private int pageCount;

    private int size;

    private int total;

    public void setDatas(ArrayList<ProjectCommunityBean> datas) {
        this.datas = datas;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<ProjectCommunityBean> getDatas() {
        return datas;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isOver() {
        return over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getSize() {
        return size;
    }

    public int getTotal() {
        return total;
    }
}
