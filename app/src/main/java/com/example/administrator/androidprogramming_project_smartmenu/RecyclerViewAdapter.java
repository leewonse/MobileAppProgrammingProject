package com.example.administrator.androidprogramming_project_smartmenu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015-11-17.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Activity activity;
    Context context;
    ArrayList<Recycler_item> items;
    int item_layout;
    public RecyclerViewAdapter(Context context, ArrayList<Recycler_item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        final Recycler_item item = items.get(position);
        final int real_position = items.get(position).getId();
        final int p_position= position;

        holder.store.setText(item.getStore());
        holder.menu.setText(item.getMenu());
        holder.lower.setText(item.getLower());
        holder.higher.setText(item.getHigher());
        holder.location.setText(item.getLocation());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyDBManager storedbmanager = new moneyDBManager(v.getContext(), "store.db", null, 1);
                SQLiteDatabase storedb = storedbmanager.getReadableDatabase();
                storedbmanager.delete("delete from STORE_LIST where _id = " + real_position + ";");

                Log.d("Item_position", real_position + "");
                Log.d("item _id", p_position+"");

                MenuManage MMActivity = (MenuManage)MenuManage.MMActivity;
                Intent intent = new Intent (context, MenuManage.class);
                v.getContext().startActivity(intent);
                MMActivity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView store;
        TextView menu;
        TextView lower;
        TextView higher;
        TextView location;
        Button delete;

        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            store = (TextView) itemView.findViewById(R.id.item_store);
            menu = (TextView) itemView.findViewById(R.id.item_menu);
            lower = (TextView) itemView.findViewById(R.id.item_lower);
            higher = (TextView) itemView.findViewById(R.id.item_higher);
            location = (TextView) itemView.findViewById(R.id.item_location);
            delete = (Button)itemView.findViewById(R.id.deletebutton);

            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}