package com.barcrawlr.barcrawlr;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FragmentInfo extends Fragment {
    View view;
    private Bundle bundle;
    private TextView barName;
    private TextView barLocation;
    private TextView barShort;
    private TextView barLong;
    private TextView barPrice;

    public FragmentInfo(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.info_fragment,container,false);
        barName = (TextView) view.findViewById(R.id.name);//Find textview Id
        barLocation = (TextView) view.findViewById(R.id.location);//Find textview Id
        barShort = (TextView) view.findViewById(R.id.bars_short_desc);//Find textview Id
        barLong = (TextView) view.findViewById(R.id.bars_long_desc);//Find textview Id
        barPrice = (TextView) view.findViewById(R.id.prices);//Find textview Id

        bundle = this.getArguments();
        //Get Argument that passed from activity in key value
        String bars_name = bundle.getString("barName");
        String bars_short = bundle.getString("shortDesc");
        String bars_long = bundle.getString("longDesc");
        String bars_location = bundle.getString("locationString");
        String bars_price = bundle.getString("priceString");

        barName.setText(bars_name);//set string over textview
        barLocation.setText(bars_location);
        barShort.setText(bars_short);
        barLong.setText(bars_long);
        barPrice.setText(bars_price);

        return view;
    }


}
