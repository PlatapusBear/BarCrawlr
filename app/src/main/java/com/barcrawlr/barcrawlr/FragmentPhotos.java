package com.barcrawlr.barcrawlr;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentPhotos extends Fragment {
    View view;
    private ImageView image;
    private Bundle bundle;

    public FragmentPhotos() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.photo_fragment, container, false);
        image = (ImageView) view.findViewById(R.id.imageVC);//Find textview Id

        bundle = this.getArguments();
        //Get Argument that passed from activity in key value
        int image_num = bundle.getInt("ImageNum");

        image.setImageResource(image_num);
        return view;
    }
}