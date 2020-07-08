package com.example.test01.two.adapter.holder;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.test01.R;
import com.example.test01.two.Data.IntItemData;
import com.example.test01.two.adapter.MultiItemsAdapter;
import com.example.test01.two.view.AlertDialogView;

public class intViewHolder extends AbsViewHolder<IntItemData> {

    private MultiItemsAdapter.OnItemClickListener mOnItemClickListener;
    TextView mTvTittle;
    EditText mTvContent;
    Context mContext;
    public intViewHolder(MultiItemsAdapter multiItemsAdapter, View itemView) {
        super(multiItemsAdapter,itemView);
        mOnItemClickListener = multiItemsAdapter.getOnItemClickListener();
        mTvTittle = itemView.findViewById(R.id.tv_tittle2);
        mTvContent = itemView.findViewById(R.id.tv_content2);
        mContext=multiItemsAdapter.getContext();
    }

    @Override
    public void bindData(final IntItemData data, final int position) {
        mTvTittle.setText(data.getTittle());
        mTvContent.setHint(data.getContent());
        mTvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialogView alertDialogView=new AlertDialogView(mContext,data.getTittleEnglish());
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(data.getTittleEnglish()).setIcon(android.R.drawable.ic_dialog_info).setView(alertDialogView)
                        .setNegativeButton("Cancel", null);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        String s = alertDialogView.getText();
                        if(s!=null)
                        {
                            alertDialogView.insertData(s);
                        }
                        mTvContent.setText(s);
                    }
                });
                builder.show();
            }
        });
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
