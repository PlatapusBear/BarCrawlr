package com.barcrawlr.barcrawlr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarListFragment extends Fragment {

    private RecyclerView barList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter barAdapter;

    public BarListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_list, container, false);

        ArrayList<Bar> bars = new ArrayList<Bar>();
        barList = (RecyclerView)view.findViewById(R.id.bar_list);

        Bar bar1 = new Bar();
        bar1.setName("Tango");
        bar1.setAttended(true);

        Bar bar2 = new Bar();
        bar2.setName("Charlie");
        bar2.setAttended(false);

        Bar bar3 = new Bar();
        bar3.setName("Foxtrot");
        bar3.setAttended(false);

        bars.add(bar1);
        bars.add(bar2);
        bars.add(bar3);

        layoutManager = new LinearLayoutManager(getContext());
        barList.setLayoutManager(layoutManager);

        barAdapter = new BarAdapter(getContext(), bars);
        barList.setAdapter(barAdapter);

        return view;
    }

}
