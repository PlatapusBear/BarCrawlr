package com.barcrawlr.barcrawlr;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import link.fls.swipestack.SwipeStack;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private SwipeStack cardStack;
    private CardAdapter cardAdapter;
    private Double Latitude;
    private Double Longitude;

    private Location location1;
    private LocationManager locationManager;

    private ArrayList<CardInfo> cdInfo;
    private FusedLocationProviderClient mFusedLocationClient;

    private int currentPosition;
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
                location = location1;
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

        // lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);





        cardStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {

                if (currentPosition == (cdInfo.size() - 1)) {
                    cardStack.resetStack();
                    currentPosition = 0;
                }
                currentPosition = position + 1;
            }

            @Override
            public void onViewSwipedToRight(int position) {

                //Bundle extras = new Bundle();
                //Intent intent = new Intent(MainActivity.this,xxx.class);
                //extras.putString("NAME",cardAdapter.getItem(position).getName());
                //extras.putString("LOCATION",cardAdapter.getItem(position).getLocation());
                //intent.putExtras(extras);
                //startActivity(intent);
                if (currentPosition == (cdInfo.size() - 1)) {
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

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::	This function converts decimal degrees to radians						 :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::	This function converts radians to decimal degrees						 :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private void setCardStackAdapter() {
        cdInfo = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {

            Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            location1 = location;
        }
        cdInfo.add(new CardInfo(R.drawable.ingersoll_tap, "Ingersoll Tap", distance(location1.getLatitude(), location1.getLongitude(), 41.5860, -93.6558)));
        cdInfo.add(new CardInfo(R.drawable.ic_launcher_background, "Juniper Moon", "Distance Away"));
        cdInfo.add(new CardInfo(R.drawable.ic_launcher_background, "Star Bar", "Distance Away"));
        cdInfo.add(new CardInfo(R.drawable.ic_launcher_background, "Zimm's Food and Spirits", "Distance Away"));
        cdInfo.add(new CardInfo(R.drawable.ic_launcher_background, "Wellman's Pub", "Distance Away"));

        cardAdapter = new CardAdapter(this, cdInfo);
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

    //@Override
   /** public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                    locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1000 * 60, 2, (LocationListener) this);
                    Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                    location1 = location;
                }
            }
        }
    }**/
}
