package com.example.test01.two.Data;

import com.example.test01.two.bean.ItemBean;

public class BooleanItemData extends ItemBean {
    public BooleanItemData(String tittle, String tittleEnglish, String content) {
        super(tittle, tittleEnglish, content);
    }

    public BooleanItemData(String tittle, String tittleEnglish, String content, int type) {
        super(tittle, tittleEnglish, content, type);
    }

    public BooleanItemData(String tittle, String content) {
        super(tittle, content);
    }
}
