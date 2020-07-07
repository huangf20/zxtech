package com.example.test01.two.bean;

public class ItemBean {
    private String tittle;
    private String tittleEnglish;
    private String content;
    private int type;

    public ItemBean(String tittle, String tittleEnglish, String content) {
        this.tittle = tittle;
        this.tittleEnglish = tittleEnglish;
        this.content = content;
    }

    public String getTittleEnglish() {
        return tittleEnglish;
    }

    public void setTittleEnglish(String tittleEnglish) {
        this.tittleEnglish = tittleEnglish;
    }

    public ItemBean(String tittle, String tittleEnglish, String content, int type) {
        this.tittle = tittle;
        this.tittleEnglish = tittleEnglish;
        this.content = content;
        this.type = type;
    }

    public ItemBean(String tittle, String content, int type) {
        this.tittle = tittle;
        this.content = content;
        this.type = type;
    }



    public ItemBean(String tittle, String content) {
        this.tittle = tittle;
        this.content = content;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTittle() {
        return tittle;
    }

    public String getContent() {
        return content;
    }
}
