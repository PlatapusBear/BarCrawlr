package com.barcrawlr.barcrawlr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        return view;
        //MapButton.setOnClickListener(new View.OnClickListener()) {
        //@Override
        //public void onClick(View view) {
        //  Intent intent = new Intent(view.getContext(), BarMaps.class);
        //intent.putExtra("Bar", "location");
        //startActivity(intent);
        //  };
    }
}
