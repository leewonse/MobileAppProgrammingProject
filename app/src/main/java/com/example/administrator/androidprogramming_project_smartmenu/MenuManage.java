package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015-11-15.
 */
public class MenuManage extends Activity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_enroll = new Intent(getApplicationContext(), MenuEnroll.class);
                startActivity(Go_enroll);
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Recycler_item> items=new ArrayList<>();

        items.add(new Recycler_item("store","menu",3000,7000,"중대",R.drawable.cardbasic));
        items.add(new Recycler_item("store","menu",3000,7000,"중대",R.drawable.cardbasic));
        items.add(new Recycler_item("store","menu",3000,7000,"중대",R.drawable.cardbasic));
        items.add(new Recycler_item("store","menu",3000,7000,"중대",R.drawable.cardbasic));
        items.add(new Recycler_item("store","menu",3000,7000,"중대",R.drawable.cardbasic));



        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(),items,R.layout.activity_manage));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}