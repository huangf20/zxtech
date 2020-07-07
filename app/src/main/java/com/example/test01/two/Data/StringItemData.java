package com.example.test01.two.Data;

import com.example.test01.two.bean.ItemBean;

public class StringItemData extends ItemBean {
    public StringItemData(String tittle, String content, int type) {
        super(tittle, content, type);
    }

    public StringItemData(String tittle, String tittleEnglish, String content, int type) {
        super(tittle, tittleEnglish, content, type);
    }

    public StringItemData(String tittle, String tittleEnglish, String content) {
        super(tittle, tittleEnglish, content);
    }
}
