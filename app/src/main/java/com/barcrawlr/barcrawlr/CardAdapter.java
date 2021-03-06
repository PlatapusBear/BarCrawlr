package com.barcrawlr.barcrawlr;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends BaseAdapter {

    private Activity activity;
    private List<CardInfo> cData;

    public CardAdapter(Activity activity, ArrayList<CardInfo> data) {
        this.cData = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return cData.size();
    }

    @Override
    public CardInfo getItem(int position) {
        return cData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.info_card, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
           //reuse view
            //getTag to access all individual view data
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        holder.location.setText(getItem(position).getLocation());
        holder.price.setText(getItem(position).getPrice());
        holder.barProfile.setImageBitmap(decodeBitmapResource(activity.getResources(),
                getItem(position).getDrawableId(),150,300));
        return convertView;
    }

    private Bitmap decodeBitmapResource(Resources resources, int drawableId, int widthReq, int heightReq) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        //allows caller to query the bitmap without having to allocate the memory for its pixels
        options.inJustDecodeBounds = true;
        //decode object resID to bitmap
        BitmapFactory.decodeResource(resources,drawableId,options);
        //scales down size of large image
        options.inSampleSize = calcInSampleSize(options, widthReq, heightReq);
        //save bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, drawableId, options);
    }

    public static int calcInSampleSize(BitmapFactory.Options options, int widthReq, int heighReq) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > heighReq || width > widthReq) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= heighReq
                    && (halfWidth / inSampleSize) >= widthReq) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private class ViewHolder{
        private ImageView barProfile;
        private TextView name;
        private TextView location;
        private TextView price;

        public ViewHolder(View view) {
            barProfile = (ImageView)view.findViewById(R.id.barprofile);
            name = (TextView)view.findViewById(R.id.name);
            location = (TextView)view.findViewById(R.id.location);
            price = (TextView)view.findViewById(R.id.price);
        }
    }

}
