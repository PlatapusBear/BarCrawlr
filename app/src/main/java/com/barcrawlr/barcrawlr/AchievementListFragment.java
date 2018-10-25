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
        Achievement achievement2 = new Achievement();
        Achievement achievement3 = new Achievement();
        Achievement achievement4 = new Achievement();
        Achievement achievement5 = new Achievement();
        Achievement achievement6 = new Achievement();

        Bitmap image1 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.incompleteachievement);
        Bitmap image2 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.achievementcompleted);
        Bitmap image3 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.mysteryachievement);

        achievement1.setName("Choosy");
        achievement1.setImage(image1);
        achievement1.setDescription("Earn this by being selective and swipe left \nfive times in one day");

        achievement2.setName("Country Award");
        achievement2.setImage(image1);
        achievement2.setDescription("Earn this achievement after visiting three \ndifferent country bars");

        achievement3.setName("Dive In");
        achievement3.setImage(image1);
        achievement3.setDescription("Visit three dive bars");

        achievement4.setName("First Bar");
        achievement4.setImage(image1);
        achievement4.setDescription("Congratulations, you visited your first bar \nusing Bar Crawl!");

        achievement5.setName("Returner");
        achievement5.setImage(image1);
        achievement5.setDescription("Visit the same bar three times");

        achievement6.setName("???");
        achievement6.setImage(image3);
        achievement6.setDescription("Keeps swiping to unlock mystery achievements!");

        achievements.add(achievement1);
        achievements.add(achievement2);
        achievements.add(achievement3);
        achievements.add(achievement4);
        achievements.add(achievement5);
        achievements.add(achievement6);

        layoutManager = new LinearLayoutManager(getContext());
        achievementList.setLayoutManager(layoutManager);

        achievementAdapter = new AchievmentAdapter(getContext(), achievements);
        achievementList.setAdapter(achievementAdapter);

        return view;
    }

}
