package com.barcrawlr.barcrawlr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import link.fls.swipestack.SwipeStack;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private SwipeStack cardStack;
    private CardAdapter cardAdapter;
    private ArrayList<CardInfo> cdInfo;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardStack = (SwipeStack) findViewById(R.id.container);

        setCardStackAdapter();
        currentPosition = 0;

        cardStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                currentPosition = position + 1;
            }

            @Override
            public void onViewSwipedToRight(int position) {
                currentPosition = position + 1;
            }

            @Override
            public void onStackEmpty() {

            }
        });

    }

    private void setCardStackAdapter() {
        cdInfo = new ArrayList<>();

        cdInfo.add(new CardInfo(R.drawable.ic_launcher_background,"Bar 1","Distance Away"));
        cdInfo.add(new CardInfo(R.drawable.ic_launcher_background,"Bar 2","Distance Away"));
        cdInfo.add(new CardInfo(R.drawable.ic_launcher_background,"Bar 3","Distance Away"));

        cardAdapter = new CardAdapter(this,cdInfo);
        cardStack.setAdapter(cardAdapter);
    }
}
