package com.barcrawlr.barcrawlr;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class BarInfoPage extends AppCompatActivity {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    private TextView bar_name;
    private ImageView bar_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //CardInfo bar = (CardInfo) getIntent().getSerializableExtra("BARINFO");
        String barName = getIntent().getExtras().getString("BAR_NAME");
        String shortDesc = getIntent().getExtras().getString("BAR_SHORT");
        String longDesc = getIntent().getExtras().getString("BAR_LONG");
        String priceString = getIntent().getExtras().getString("BAR_PRICE");
        String locationString = getIntent().getExtras().getString("BAR_LOCATION");
        int ImageNum = getIntent().getExtras().getInt("drawableID");
        String addressString = getIntent().getExtras().getString("ADDRESS");

        Bundle info_data = new Bundle();//create bundle instance
        info_data.putString("barName", barName);//put string to pass with a key value
        info_data.putString("shortDesc", shortDesc);
        info_data.putString("longDesc", longDesc);
        info_data.putString("priceString", priceString);
        info_data.putString("locationString", locationString);
        info_data.putString("AddressString", addressString);
        Bundle photo_data = new Bundle();
        photo_data.putInt("ImageNum", ImageNum);


        setContentView(R.layout.activity_bar_info_page);
        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Adding Fragments

        FragmentInfo fragInfo = new FragmentInfo();
        FragmentPhotos fragPhotos = new FragmentPhotos();
        FragmentMaps fragMaps = new FragmentMaps();

        fragInfo.setArguments(info_data);
        fragPhotos.setArguments(photo_data);
        adapter.addFrag(fragInfo,"Info");
        adapter.addFrag(fragPhotos,"Photos");
        adapter.addFrag(fragMaps, "Map");

        bar_name = (TextView) findViewById(R.id.mainline_bar_name);//Find textview Id
        bar_name.setText(barName);

        bar_image = (ImageView) findViewById(R.id.main_image);
        bar_image.setImageResource(ImageNum);

        //adapter Setup
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barlistbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.barlist) {
            Intent intent = new Intent(BarInfoPage.this, ProfileActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
