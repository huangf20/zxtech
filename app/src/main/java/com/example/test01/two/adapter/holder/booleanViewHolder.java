package com.example.test01.two.adapter.holder;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.test01.R;
import com.example.test01.two.Data.booleanItemData;
import com.example.test01.two.adapter.MultiItemsAdapter;

public class booleanViewHolder extends AbsViewHolder<booleanItemData> {

    private MultiItemsAdapter.OnItemClickListener mOnItemClickListener;
    TextView mTvTittle, mTvContent;
    Switch aSwitch;

    public booleanViewHolder(MultiItemsAdapter multiItemsAdapter, View itemView) {
        super(multiItemsAdapter,itemView);
        mOnItemClickListener = multiItemsAdapter.getOnItemClickListener();
        mTvTittle = itemView.findViewById(R.id.tv_tittle3);
        mTvContent = itemView.findViewById(R.id.tv_content3);
        aSwitch=itemView.findViewById(R.id.item_switch);


    }

    @Override
    public void bindData(booleanItemData data, final int position) {
        mTvTittle.setText(data.getTittle().toString());
        mTvContent.setHint(data.getContent().toString());
        if(data.getContent().toString().equals("true"))
            aSwitch.setChecked(true);
        else
            aSwitch.setChecked(false);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mTvContent.setText(b+"");
                mOnItemClickListener.onTextChange(b+"",position);
            }
        });
    }


}
