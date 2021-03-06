package com.barcrawlr.barcrawlr;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter{
    int mNumOfTabs;

    public TabPagerAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                BarListFragment tab1 = new BarListFragment();
                return tab1;
            case 1:
                AchievementListFragment tab2 = new AchievementListFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return mNumOfTabs;
    }
}
