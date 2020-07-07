package com.example.test01.two.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test01.two.bean.ItemBean;

public interface IViewHolder {
    //RecyclerView.ViewHolder createViewHolder();

    //int getViewType();

    void bindViewHolder(ItemBean item);
}
