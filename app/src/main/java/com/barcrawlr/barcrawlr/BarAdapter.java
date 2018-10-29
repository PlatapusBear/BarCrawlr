package com.barcrawlr.barcrawlr;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.RealmResults;

public class BarAdapter extends  RecyclerView.Adapter<BarAdapter.BarViewHolder>{
    private Context context;
    private RealmResults<Bar> bars;

    public BarAdapter(Context context, RealmResults<Bar> dataset){
        this.context = context;
        this.bars = dataset;
    }

    public static class BarViewHolder extends RecyclerView.ViewHolder{
        public TextView nameView;
        public ImageView attendView;

        public BarViewHolder(View v) {
            super(v);
            nameView = v.findViewById(R.id.name_view);
            attendView = v.findViewById(R.id.attended);
        }
    }

    @Override
    public int getItemCount(){
        return bars.size();
    }

    @Override
    public BarAdapter.BarViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.bar_cell, parent, false);
        BarViewHolder vh = new BarViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BarViewHolder holder, int position){
        holder.nameView.setText(bars.get(position).getBarName());
        if(bars.get(position).haveAttended()){
            Bitmap achieved = BitmapFactory.decodeResource(context.getResources(), R.drawable.checkbox);
            holder.attendView.setImageBitmap(achieved);
        }
        else{
            Bitmap notAchieved = BitmapFactory.decodeResource(context.getResources(), R.drawable.emptybox);
            holder.attendView.setImageBitmap(notAchieved);
        }
    }
}
