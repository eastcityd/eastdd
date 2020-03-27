package com.example.wanandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.MainArticleBean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hwq
 * @date 2020/1/2.
 * GitHub：
 * Email：
 * Description：
 */
public class ArticleAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<MainArticleBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public ArticleAdapter(Context context, ArrayList<MainArticleBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.article_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads) {
        final ViewHolder h = (ViewHolder) holder;
        MainArticleBean data = list.get(position);
        if (payloads.size() == 0) {
            commonBind(h, data, position);
        } else {

        }
    }

    private void commonBind(ViewHolder h, MainArticleBean data, final int position) {
        h.tv_author.setText(data.getAuthor().equals("") ? data.getShareUser() : data.getAuthor());
        if (data.getNiceDate().equals("刚刚")) {
            h.tv_new.setVisibility(View.VISIBLE);
        } else {
            h.tv_new.setVisibility(View.GONE);
        }
        if (data.getSuperChapterName().equals("公众号")) {
            h.tv_public.setVisibility(View.VISIBLE);
        } else {
            h.tv_public.setVisibility(View.GONE);
        }
        h.tv_time.setText(data.getNiceDate());
        h.tv_title.setText(data.getTitle());
        h.tv_sharer.setText(data.getSuperChapterName() + "·" + data.getChapterName());
        h.iv_thumb.setColorFilter(Color.argb(255, 200,200,200));
        h.ll_item.setOnClickListener(v -> {
           if (listener != null) {
               listener.toWebDetail(position);
           }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{

        /**
         * web详情页
         * @param position
         */
        void toWebDetail(int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_ll) LinearLayout ll_item;

        @BindView(R.id.tv_author) TextView tv_author;
        @BindView(R.id.tv_top) TextView tv_top;
        @BindView(R.id.tv_public) TextView tv_public;
        @BindView(R.id.tv_new) TextView tv_new;
        @BindView(R.id.tv_time) TextView tv_time;

        @BindView(R.id.iv_img) ImageView iv_img;
        @BindView(R.id.tv_title) TextView tv_title;

        @BindView(R.id.tv_sharer) TextView tv_sharer;
        @BindView(R.id.iv_thumb) ImageView iv_thumb;

        public ViewHolder(View v) {
            super(v);
            ScreenAdapterTools.getInstance().loadView(v);
            ButterKnife.bind(this, v);
        }
    }
}
