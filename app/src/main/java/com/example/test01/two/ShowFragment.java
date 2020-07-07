package com.example.test01.two;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test01.R;
import com.example.test01.two.Path.Path;
import com.example.test01.two.adapter.MultiItemsAdapter;
import com.example.test01.two.bean.ItemBean;
import com.example.test01.two.itemdecoration.RecycleItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ShowFragment extends Fragment implements MultiItemsAdapter.OnItemClickListener {

    private Uri.Builder mBuilder;
    private String mTittle, mPath;
    private View mView;
    private TextView mTvContent, mTvTittle;
    private RecyclerView mRecyclerView;
    private Button btGo, btUpte;
    private List<ItemBean> mItemBeanList = new ArrayList<>();

    private List<String> mValueList = new ArrayList<>();

    public ShowFragment() {
    }

    public ShowFragment(List<ItemBean> list) {
        this.mItemBeanList = list;
    }


    public ShowFragment(String tittle, String content, List<ItemBean> list) {
        mTittle = tittle;
        mPath = content;
        mItemBeanList = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.string_layout, container, false);

        initDate();
        initView();

        btUpte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setContentTv();


            }
        });

        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage(Path.pkgName);
                intent.setData(mBuilder.build());
                startActivity(intent);
            }
        });

        return mView;
    }

    private void initDate() {
        mBuilder = new Uri.Builder()
                .scheme(Path.scheme)
                .authority(Path.pkgName)
                .path(mPath);

        for (int i = 0; i < mItemBeanList.size(); i++) {
            mValueList.add("");
            mBuilder.appendQueryParameter(mItemBeanList.get(i).getTittleEnglish(), mItemBeanList.get(i).getContent());
        }
    }

    private void setContentTv() {
        mTvContent.setText(mBuilder.build().toString());
    }


    private void initView() {
        btGo = mView.findViewById(R.id.bt_go);
        btUpte = mView.findViewById(R.id.bt_update);
        mTvContent = mView.findViewById(R.id.text_detial_one);
        mTvTittle = mView.findViewById(R.id.text_tittle);
        mTvTittle.setText("-" + mTittle + "-");
        mTvContent.setText(mBuilder.build().toString());

        MultiItemsAdapter adapter = new MultiItemsAdapter(this, getContext());
        mRecyclerView = mView.findViewById(R.id.rcv_vertical);

        LinearLayoutManager managerVertical = new LinearLayoutManager(getContext());
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(managerVertical);
        mRecyclerView.addItemDecoration(new RecycleItemDecoration(getContext(), RecycleItemDecoration.VERTICAL_LIST));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);

        adapter.setDataList(mItemBeanList);
    }

    @Override
    public void onTextChange(String text, int position) {
        mValueList.set(position, text);
        mBuilder.clearQuery();
        for (int i = 0; i < mValueList.size(); i++) {
            if (!mValueList.get(i).equals("")) {
                mBuilder.appendQueryParameter(mItemBeanList.get(i).getTittleEnglish(), mValueList.get(i));
            }
            else {
                mBuilder.appendQueryParameter(mItemBeanList.get(i).getTittleEnglish(),mItemBeanList.get(i).getContent());
            }
        }
        setContentTv();
    }
}
