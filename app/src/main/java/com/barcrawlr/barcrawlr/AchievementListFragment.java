package com.barcrawlr.barcrawlr;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class AchievementListFragment extends Fragment {
    private RecyclerView achievementList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter achievementAdapter;

    public AchievementListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_achievement_list, container, false);

        ArrayList<Achievement> achievements = new ArrayList<Achievement>();
        achievementList = (RecyclerView) view.findViewById(R.id.achievement_list);

        Achievement achievement1 = new Achievement();
        Bitmap image = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.achievement);
        achievement1.setName("First Achievement");
        achievement1.setImage(image);

        achievements.add(achievement1);

        layoutManager = new LinearLayoutManager(getContext());
        achievementList.setLayoutManager(layoutManager);

        achievementAdapter = new AchievmentAdapter(getContext(), achievements);
        achievementList.setAdapter(achievementAdapter);

        return view;
    }

}
