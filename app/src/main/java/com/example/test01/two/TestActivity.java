package com.example.test01.two;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test01.R;
import com.example.test01.two.Data.stringItemData;
import com.example.test01.two.Data.intItemData;
import com.example.test01.two.Data.booleanItemData;
import com.example.test01.two.adapter.SimpleAdapter;
import com.example.test01.two.bean.ItemBean;
import com.example.test01.two.bean.TabItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestActivity extends AppCompatActivity implements SimpleAdapter.OnItemClickListener {


    private FragmentManager mFragmentManager;
    private Fragment mShowFragmentNow;
    private Map<Integer, ShowFragment> mFragmentMap = new HashMap<>();
    private List<TabItem> mTabItems = new ArrayList<>();
    private RecyclerView mSimpleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

        TabItem tabItem = mTabItems.get(0);
        ShowFragment showFragment = new ShowFragment(tabItem.getTitle(), tabItem.getPath(), tabItem.getConfigs());
        replaceFragment(showFragment, false);
    }

    private void initView() {
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, mTabItems, this);
        mSimpleRecyclerView = findViewById(R.id.simple_recylcer);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mSimpleRecyclerView.setLayoutManager(layoutManager);
        mSimpleRecyclerView.setHasFixedSize(true);
        mSimpleRecyclerView.setAdapter(simpleAdapter);
    }

    private void initData() {
        List<ItemBean> bookstoreitems = new ArrayList<>();
        List<ItemBean> readerItems = new ArrayList<>();
        List<ItemBean> h5Items = new ArrayList<>();
        List<ItemBean> welfareItems = new ArrayList<>();
        List<ItemBean> booklistItems = new ArrayList<>();
        List<ItemBean> conditionqueryItems = new ArrayList<>();
        List<ItemBean> listpageItems = new ArrayList<>();
        List<ItemBean> pagepayItems = new ArrayList<>();
        List<ItemBean> ranklistItems = new ArrayList<>();
        List<ItemBean> usercenterItems=new ArrayList<>();
        List<ItemBean> searchItems=new ArrayList<>();
        List<ItemBean> catalogItems=new ArrayList<>();

        readerItems.add(new intItemData("书籍ID", "bookId", "321"));
        readerItems.add(new stringItemData("章节ID", "chapterId", "1"));
        readerItems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        mTabItems.add(new TabItem("阅读器", "reader", readerItems));

        bookstoreitems.add(new stringItemData("页面id", "id", "1"));
        bookstoreitems.add(new stringItemData("频道", "channel", "boy"));
        bookstoreitems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        mTabItems.add(new TabItem("书库", "bookstore", bookstoreitems));

        catalogItems.add(new stringItemData("书籍ID","bookId","321"));
        catalogItems.add(new stringItemData("书籍名称","bookName","物理魔法师"));
        catalogItems.add(new stringItemData("高亮的章节ID","chapterId","1"));
        catalogItems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        mTabItems.add(new TabItem("目录", "catalog", catalogItems));

        h5Items.add(new stringItemData("url链接", "url", "www.baidu.com"));
        h5Items.add(new stringItemData("标题", "title", "tittle"));
        h5Items.add(new booleanItemData("是否允许下拉刷新", "enableRefresh", "true"));
        mTabItems.add(new TabItem("H5", "website", h5Items));

        mTabItems.add(new TabItem("福利", "welfare", welfareItems));

        booklistItems.add(new stringItemData("书单ID", "id", "54"));
        booklistItems.add(new stringItemData("书单名字", "title", "女生精选"));
        booklistItems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        mTabItems.add(new TabItem("书单详情", "booklist", booklistItems));

        conditionqueryItems.add(new stringItemData("对应的categotyId", "categoryId", "123"));
        conditionqueryItems.add(new stringItemData("title", "title", "精选"));
        conditionqueryItems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        mTabItems.add(new TabItem("筛选", "condition_query", conditionqueryItems));

        ranklistItems.add(new stringItemData("频道","channel","boy"));
        ranklistItems.add(new stringItemData("排行id","id","1"));
        ranklistItems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        mTabItems.add(new TabItem("排行","ranklist",ranklistItems));

        usercenterItems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        mTabItems.add(new TabItem("我的 ","usercenter",usercenterItems));

        searchItems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        mTabItems.add(new TabItem("搜索","search",searchItems));


        listpageItems.add(new stringItemData("默认展示的pageId", "pageId", "1"));
        listpageItems.add(new stringItemData("页面类型", "pageType", "free_page"));
        listpageItems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        listpageItems.add(new stringItemData("展示样式", "showType", "0"));
        mTabItems.add(new TabItem("列表页面", "list_page", listpageItems));

        pagepayItems.add(new stringItemData("付款金额", "payAmount", "10000"));
        pagepayItems.add(new stringItemData("购买的书币数量", "credits", "100"));
        pagepayItems.add(new stringItemData("赠送的书币数量", "creditsGift", "10"));
        pagepayItems.add(new stringItemData("充值类型id", "packageId", "1"));
        pagepayItems.add(new stringItemData("充值计划id", "rechargePlanId", "1"));
        pagepayItems.add(new stringItemData("跳转来源(例如:推送: push)", "source", "test"));
        mTabItems.add(new TabItem("充值页面", "page_pay", pagepayItems));

        mFragmentManager = getSupportFragmentManager();
        mShowFragmentNow = new ShowFragment();
    }

    private void replaceFragment(Fragment fragment, boolean isAdd) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (!isAdd) {
            transaction
                    .hide(mShowFragmentNow)
                    .add(R.id.rightlayout, fragment);
        } else {
            transaction
                    .hide(mShowFragmentNow)
                    .show(fragment);

        }
        mShowFragmentNow = fragment;
        transaction.commit();
    }


    //点击事件实现
    @Override
    public void onItemClick(int position) {
        ShowFragment showFragment;
        boolean isAdd = false;
        TabItem tabItem = mTabItems.get(position);

        if (!mFragmentMap.containsKey(position)) {
            showFragment = new ShowFragment(tabItem.getTitle(), tabItem.getPath(), tabItem.getConfigs());
            mFragmentMap.put(position, showFragment);
        } else {
            showFragment = mFragmentMap.get(position);
            isAdd = true;
        }
        replaceFragment(showFragment, isAdd);


    }
}
