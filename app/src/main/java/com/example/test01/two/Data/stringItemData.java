package com.example.test01.two.Data;

import com.example.test01.two.bean.ItemBean;

public class stringItemData extends ItemBean {
    public stringItemData(String tittle, String content, int type) {
        super(tittle, content, type);
    }

    public stringItemData(String tittle, String tittleEnglish, String content, int type) {
        super(tittle, tittleEnglish, content, type);
    }

    public stringItemData(String tittle, String tittleEnglish, String content) {
        super(tittle, tittleEnglish, content);
    }
}
