package com.example.administrator.androidprogramming_project_smartmenu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Recycler_item item = items.get(position);
        //Drawable drawable=context.getResources().getDrawable(item.getGallery());
        holder.store.setText(item.getStore());
        holder.menu.setText(item.getMenu());
        holder.lower.setText(item.getLower()+"");
        holder.higher.setText(item.getHigher()+"");
        holder.location.setText(item.getLocation());
        holder.gallery.setBackgroundResource(R.drawable.cardbasic);

        holder.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DialogBox.class);

                v.getContext().startActivity(intent);
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
        ImageView gallery;

        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            store = (TextView) itemView.findViewById(R.id.item_store);
            menu = (TextView) itemView.findViewById(R.id.item_menu);
            lower = (TextView) itemView.findViewById(R.id.item_lower);
            higher = (TextView) itemView.findViewById(R.id.item_higher);
            location = (TextView) itemView.findViewById(R.id.item_location);
            gallery = (ImageView)itemView.findViewById(R.id.gallery);

            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}