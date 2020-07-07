package com.example.test01.two.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test01.R;
import com.example.test01.two.Data.stringItemData;
import com.example.test01.two.Data.intItemData;
import com.example.test01.two.Data.booleanItemData;
import com.example.test01.two.adapter.holder.AbsViewHolder;
import com.example.test01.two.adapter.holder.stringViewHolder;
import com.example.test01.two.adapter.holder.intViewHolder;
import com.example.test01.two.adapter.holder.booleanViewHolder;
import com.example.test01.two.bean.ItemBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MultiItemsAdapter extends RecyclerView.Adapter<AbsViewHolder> {


    private static final String TAG = MultiItemsAdapter.class.getSimpleName();
    private OnItemClickListener mOnItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    private Context mContext;

    private List<ItemBean> mList = new ArrayList<>();

    public MultiItemsAdapter(OnItemClickListener onItemClickListener, Context context) {
        mOnItemClickListener = onItemClickListener;
        mContext = context;
    }

    public MultiItemsAdapter(Context context) {
        mContext = context;
    }

    public void setDataList(List<ItemBean> list) {
        Log.d(TAG, "setVerticalDataList: " + list.size());
        mList = list;
        notifyDataSetChanged();

    }


    public List<Class<? extends ItemBean>> mItemTypes = new ArrayList<>();
    public List<Class<? extends AbsViewHolder>> mHolderTypes = new ArrayList<>();
    public List<Integer> mViewTypes = new ArrayList<>();


    {
        registerItem(stringItemData.class, stringViewHolder.class, R.layout.recycle_item_string);
        registerItem(intItemData.class, intViewHolder.class, R.layout.recycle_item_int);
        registerItem(booleanItemData.class, booleanViewHolder.class, R.layout.recycle_itemboolean);
    }

    private void registerItem(Class<? extends ItemBean> dataClass, Class<? extends AbsViewHolder> holderClass, int layoutId) {
        mItemTypes.add(dataClass);
        mHolderTypes.add(holderClass);
        mViewTypes.add(layoutId);
    }

    @NonNull
    @Override
    public AbsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return data -> holder

        // data -> type
        // type -> holder

        View view = LayoutInflater.from(mContext).inflate(mViewTypes.get(viewType), parent, false);

        Class holder = mHolderTypes.get(viewType);

        try {
            Constructor constructor = holder.getConstructor(MultiItemsAdapter.class, View.class);
            Object absHolder = constructor.newInstance(MultiItemsAdapter.this, view);
            return (AbsViewHolder) absHolder;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull AbsViewHolder holder, int position) {
        //加一个position参数是为了监听文本框改变时，返回到实现类中，有确定的位置
        holder.bindData(mList.get(position), position);
    }


    @Override
    public int getItemViewType(int position) {
        return mItemTypes.indexOf(mList.get(position).getClass());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnItemClickListener {
        void onTextChange(String text, int position);
    }
}
