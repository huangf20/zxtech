package com.example.test01.two.adapter.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test01.two.adapter.MultiItemsAdapter;
import com.example.test01.two.bean.ItemBean;

public abstract class AbsViewHolder<T extends ItemBean> extends RecyclerView.ViewHolder {

    private MultiItemsAdapter.OnItemClickListener mOnItemClickListener;

    public AbsViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public AbsViewHolder( MultiItemsAdapter multiItemsAdapter,@NonNull View itemView) {
        super(itemView);
        this.mOnItemClickListener=multiItemsAdapter.getOnItemClickListener();
    }

    public abstract void bindData(T data,int position);
}
