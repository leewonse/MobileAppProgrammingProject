package com.example.administrator.androidprogramming_project_smartmenu;

/**
 * Created by Administrator on 2015-11-17.
 */
public class Recycler_item {

    private int id;
    private String store;
    private String menu;
    private String lower;
    private String higher;
    private String location;

    public int getId(){return this.id;}
    public String getStore(){return this.store;}
    public String getMenu(){return this.menu;}
    public String getLower(){return this.lower;}
    public String getHigher(){return this.higher;}
    public String getLocation(){return this.location;}

    public Recycler_item(int id, String store, String menu, String lower, String higher, String location){
        this.id=id;
        this.store=store;
        this.menu=menu;
        this.lower=lower;
        this.higher=higher;
        this.location=location;
    }
}