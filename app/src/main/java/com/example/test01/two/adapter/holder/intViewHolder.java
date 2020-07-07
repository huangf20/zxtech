package com.example.test01.two.adapter.holder;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test01.R;
import com.example.test01.two.Data.intItemData;
import com.example.test01.two.adapter.MultiItemsAdapter;

public class intViewHolder extends AbsViewHolder<intItemData> {

    private MultiItemsAdapter.OnItemClickListener mOnItemClickListener;
    TextView mTvTittle;
    EditText mTvContent;

    public intViewHolder(MultiItemsAdapter multiItemsAdapter, View itemView) {
        super(multiItemsAdapter,itemView);
        mOnItemClickListener = multiItemsAdapter.getOnItemClickListener();
        mTvTittle = itemView.findViewById(R.id.tv_tittle2);
        mTvContent = itemView.findViewById(R.id.tv_content2);
    }

    @Override
    public void bindData(intItemData data, final int position) {
        mTvTittle.setText(data.getTittle().toString());
        mTvContent.setHint(data.getContent().toString());
        mTvContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                mOnItemClickListener.onTextChange(mTvContent.getText().toString(),position);
            }
        });

    }


}
