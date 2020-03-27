package com.example.wanandroid.bean;

import java.util.ArrayList;

/**
 * @author hwq
 * @date 2020/1/1.
 * GitHub：
 * Email：
 * Description：
 */
public class MainArticleListBean {

    private ArrayList<MainArticleBean> datas;

    private int curPage;

    private int offset;

    private boolean over;

    private int pageCount;

    private int size;

    private int total;

    public ArrayList<MainArticleBean> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<MainArticleBean> datas) {
        this.datas = datas;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
