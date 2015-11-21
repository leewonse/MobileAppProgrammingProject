package com.example.administrator.androidprogramming_project_smartmenu;

/**
 * Created by Administrator on 2015-11-17.
 */
public class Recycler_item {

    private String store;
    private String menu;
    private String lower;
    private String higher;
    private String location;
    private int gallery;

    public String getStore(){return this.store;}
    public String getMenu(){return this.menu;}
    public String getLower(){return this.lower;}
    public String getHigher(){return this.higher;}
    public String getLocation(){return this.location;}

    public Recycler_item(String store, String menu, String lower, String higher, String location, int gallery){
        this.store=store;
        this.menu=menu;
        this.lower=lower;
        this.higher=higher;
        this.location=location;
        this.gallery=gallery;

    }
}
