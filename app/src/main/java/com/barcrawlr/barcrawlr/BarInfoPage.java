package com.barcrawlr.barcrawlr;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BarInfoPage extends AppCompatActivity {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CardInfo bar = (CardInfo) getIntent().getSerializableExtra("BARINFO");

        setContentView(R.layout.activity_bar_info_page);
        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Adding Fragments

        adapter.AddFragment(new FragmentInfo(),"Info");
        adapter.AddFragment(new FragmentPhotos(), "Images");
        adapter.AddFragment(new FragmentMaps(),"Map");
        //adapter Setup
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);



    }
}