package com.barcrawlr.barcrawlr;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


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

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Bar> bars = realm.where(Bar.class).findAll();
        barList = (RecyclerView)view.findViewById(R.id.bar_list);


        layoutManager = new LinearLayoutManager(getContext());
        barList.setLayoutManager(layoutManager);

        barAdapter = new BarAdapter(getContext(), bars);
        barList.setAdapter(barAdapter);

        return view;
    }

}
