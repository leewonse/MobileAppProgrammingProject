package com.example.administrator.androidprogramming_project_smartmenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-11-17.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
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

        holder.store.setText(item.getStore());
        holder.menu.setText(item.getMenu());
        holder.lower.setText(item.getLower()+"");
        holder.higher.setText(item.getHigher()+"");
        holder.location.setText(item.getLocation());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getStore(), Toast.LENGTH_SHORT).show();
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

        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            store = (TextView) itemView.findViewById(R.id.item_store);
            menu = (TextView) itemView.findViewById(R.id.item_menu);
            lower = (TextView) itemView.findViewById(R.id.item_lower);
            higher = (TextView) itemView.findViewById(R.id.item_higher);
            location = (TextView) itemView.findViewById(R.id.item_location);

            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
