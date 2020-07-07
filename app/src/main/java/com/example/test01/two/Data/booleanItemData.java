package com.example.test01.two.Data;

import com.example.test01.two.bean.ItemBean;

public class booleanItemData extends ItemBean {
    public booleanItemData(String tittle, String tittleEnglish, String content) {
        super(tittle, tittleEnglish, content);
    }

    public booleanItemData(String tittle, String tittleEnglish, String content, int type) {
        super(tittle, tittleEnglish, content, type);
    }

    public booleanItemData(String tittle, String content) {
        super(tittle, content);
    }
}
