package com.barcrawlr.barcrawlr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import io.realm.Realm;
import link.fls.swipestack.SwipeStack;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {

    private SwipeStack cardStack;
    private CardAdapter cardAdapter;
    private Location location1;
    private LocationManager locationManager;
    private ArrayList<CardInfo> bars;
    private int currentPosition;
    private LocationListener locationListener;
    static public final int LOCATION_REQUEST_CODE = 99;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardStack = (SwipeStack) findViewById(R.id.container);
        currentPosition = 0;

        locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },LOCATION_REQUEST_CODE);

        }
        setCardStackAdapter();


      final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                location1 = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

        };


        cardStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {

                if (currentPosition == (bars.size() - 1)) {
                    cardStack.resetStack();
                    currentPosition = 0;
                }
                currentPosition = position + 1;
            }

            private Realm realm = Realm.getDefaultInstance();

            @Override
            public void onViewSwipedToRight(int position) {

                final Achievement firstBar = realm.where(Achievement.class).equalTo("name", "First Bar").findFirst();
                if(!firstBar.getAchieved()) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            firstBar.setAchieved(true);
                            Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.achievementcompleted);

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            image2.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] complete = stream.toByteArray();
                            firstBar.setImage(complete);
                        }
                    });
                    Toast.makeText(MainActivity.this, "You\'ve achieved: First Bar", Toast.LENGTH_LONG).show();
                }

                CardInfo bar = (CardInfo)bars.get(position);
                final Bar selectedBar = realm.where(Bar.class).equalTo("barName", bar.getName()).findFirst();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        selectedBar.setAttended(true);
                    }
                });

                Intent intent = new Intent(MainActivity.this,BarInfoPage.class);
                intent.putExtra("BarName", bar.getName());
                intent.putExtra("BARINFO",(Serializable)bar);
              //  intent.putExtra("BARINFO",(Serializable)bar);
                startActivity(intent);
                if (currentPosition == (bars.size() - 1)) {
                    cardStack.resetStack();
                    currentPosition = 0;
                }
                currentPosition = position + 1;
            }

            @Override
            public void onStackEmpty() {

            }
        });

    }

    private String distance(Double lat1, Double lon1, Double lat2, Double lon2) {
        Double theta = lon1 - lon2;
        Double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        String distanceCut = new DecimalFormat("#.00").format(dist);
        
        return "Distance to Bar " + distanceCut + " Miles";
    }


    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private void setCardStackAdapter() {
        bars = new ArrayList<>();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {


            location1 = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

        }

        /*CardInfo bar1 = new CardInfo(R.drawable.ingersolltap,"Ingersoll Tap","Distance to Bar 0.83 Miles","$");
        bars.add(bar1);
        CardInfo bar2 = new CardInfo(R.drawable.junipermoon,"Juniper Moon","Distance to Bar 0.56 Miles","$$");
        bars.add(bar2);*/

        CardInfo bar1 = new CardInfo(R.drawable.ingersolltap,"Ingersoll Tap",distance(location1.getLatitude(), location1.getLongitude(), 41.5860, -93.6558),"$");
        bars.add(bar1);
        CardInfo bar2 = new CardInfo(R.drawable.junipermoon,"Juniper Moon",distance(location1.getLatitude(), location1.getLongitude(), 41.574349, -93.610430),"$$");
        bars.add(bar2);
        CardInfo bar3 = new CardInfo(R.drawable.starbar,"Star Bar",distance(location1.getLatitude(), location1.getLongitude(), 41.585998, -93.655039),"$$");
        bars.add(bar3);
        CardInfo bar4 = new CardInfo(R.drawable.zimms,"Zimm's Food and Spirits",distance(location1.getLatitude(), location1.getLongitude(), 41.585880, -93.660528),"$");
        bars.add(bar4);
        CardInfo bar5 = new CardInfo(R.drawable.wellmanspub,"Wellman's Pub",distance(location1.getLatitude(), location1.getLongitude(), 41.584682, -93.610114),"$");
        bars.add(bar5);

       Collections.sort(bars, new Comparator<CardInfo>() {
           @Override
           public int compare(CardInfo o1, CardInfo o2) {
               return o1.getLocation().compareTo(o2.getLocation());
           }
       });

        cardAdapter = new CardAdapter(this, bars);
        cardStack.setAdapter(cardAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barlistbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.barlist) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    }