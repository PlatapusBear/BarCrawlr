package com.barcrawlr.barcrawlr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.Serializable;
import java.util.Map;
public class FragmentMaps extends Fragment {
    View view;
    private Button MapButton;

    public FragmentMaps() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.maps_fragment, container, false);
        MapButton = (Button) view.findViewById(R.id.MapButton);

        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String bar= getActivity().getIntent().getExtras().getString("BAR_NAME");


                String[] parts = bar.split(" ");


                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=" + parts[0] + "+" + parts[1] + "+Des+Moines+Iowa"));
                startActivity(intent);
                /** Intent intent = new Intent(getActivity(), MapsActivity.class);

                 intent.putExtra("Maps",(Serializable)"Ingersol+Tap");
                 startActivity(intent);**/
            }
        });
        /** Bar bar = new Bar();

         Intent intent = new Intent(getContext(), MapsActivity.class);

         intent.putExtra("Maps",(Serializable)bar.getName());
         startActivity(intent);**/
        return view;


        //MapButton.setOnClickListener(new View.OnClickListener()) {
        //@Override
        //public void onClick(View view) {
        //  Intent intent = new Intent(view.getContext(), BarMaps.class);
        //intent.putExtra("Bar", "location");
        //startActivity(intent);
        //  };
    }

    public void onClickListener()

    {
        MapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bar bar = new Bar();

                Intent intent = new Intent(getContext(), MapsActivity.class);

                intent.putExtra("Maps",(Serializable)bar.getBarName());
                startActivity(intent);
            }
        });

    }
}
