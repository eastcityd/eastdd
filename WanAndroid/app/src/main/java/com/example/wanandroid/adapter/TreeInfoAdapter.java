package com.example.wanandroid.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.TreeBean;
import com.example.wanandroid.bean.TreeChildrenBean;
import com.example.wanandroid.view.widget.FlowLayout;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TreeInfoAdapter extends RecyclerView.Adapter<TreeInfoAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<TreeBean> list;
    private OnItemClickListener listener;

    public TreeInfoAdapter(Context context, ArrayList<TreeBean> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(inflater.inflate(R.layout.tree_item, parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.fl.removeAllViews();
        final TreeBean bean = list.get(i);
        //获取标题
        String name = bean.getName();
        //获取相关信息标题
        List<TreeChildrenBean> mList = bean.getChildren();
        //添加
        holder.title.setText(name);
        for (int j = 0; j < mList.size(); j++){
            TextView textView = (TextView) inflater.from(context).inflate(R.layout.item_label, null);
            String s = mList.get(j).getName();
            textView.setText(s);
            holder.fl.addView(textView);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(TreeBean bean);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.mTv_Title) TextView title;
        @BindView(R.id.mFl) FlowLayout fl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ScreenAdapterTools.getInstance().loadView(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
