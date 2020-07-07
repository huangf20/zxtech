package com.example.test01.two.bean;

import java.util.List;

public class TabItem {
    String mTitle;
    String mPath;
    List<ItemBean> mConfigs;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public List<ItemBean> getConfigs() {
        return mConfigs;
    }

    public void setConfigs(List<ItemBean> configs) {
        mConfigs = configs;
    }

    public TabItem(String title, String path, List<ItemBean> configs) {
        mTitle = title;
        mPath = path;
        mConfigs = configs;
    }

    public TabItem(String title) {
        mTitle = title;
    }
}
