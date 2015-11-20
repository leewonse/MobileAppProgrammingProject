package com.example.administrator.androidprogramming_project_smartmenu;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2015-11-19.
 */
public class Database {

    public static final class CreateDB implements BaseColumns {
        public static final String STORE = "store";
        public static final String MENU = "menu";
        public static final int LOWER = 0;
        public static final int HIGHER = 0;
        public static final String LOCATION = "location";
        public static final String _TABLENAME = "DBTABLE";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +STORE+" text not null , "
                        +MENU+" text not null , "
                        +LOWER+" text not null ;"
                        +HIGHER+" text not null , "
                        +LOCATION+" text not null);";
    }
}
