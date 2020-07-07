package com.example.test01.two.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test01.R;
import com.example.test01.two.bean.TabItem;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private Context mContext;

    private List<TabItem> mList = new ArrayList<>();

    private OnItemClickListener mListener;

    public SimpleAdapter(Context context, List<TabItem> list,OnItemClickListener onItemClickListener) {
        mContext = context;
        mList = list;
        mListener=onItemClickListener;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.simple_recycle_item,parent,false);

        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, final int position) {
        holder.tv_tittle.setText(mList.get(position).getTitle());
        holder.tv_tittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tittle;
        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tittle=itemView.findViewById(R.id.simple_item_tittle);
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
}
