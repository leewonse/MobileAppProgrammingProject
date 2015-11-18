package com.example.administrator.androidprogramming_project_smartmenu;

/**
 * Created by Administrator on 2015-11-17.
 */
public class Recycler_item {

    private String store;
    private String menu;
    private int lower;
    private int higher;
    private String location;
    public String getStore(){return this.store;}
    public String getMenu(){return this.menu;}
    public int getLower(){return this.lower;}
    public int getHigher(){return this.higher;}
    public String getLocation(){return this.location;}
    public int gallery;

    public Recycler_item(String store, String menu, int lower, int higher, String location, int gallery){
        this.store=store;
        this.menu=menu;
        this.lower=lower;
        this.higher=higher;
        this.location=location;
        this.gallery=gallery;
    }
}
