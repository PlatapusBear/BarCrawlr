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
<<<<<<< HEAD
                Bundle bundle = new Bundle();
                bundle.putString("BAR_NAME", bar.getName());
                bundle.putString("BAR_LOCATION", bar.getLocation());
                bundle.putString("BAR_PRICE", bar.getPrice());
                bundle.putString("BAR_SHORT", bar.getShortDescription());
                bundle.putString("BAR_LONG", bar.getLongDescription());
                bundle.putInt("drawableID", bar.getDrawableId());
                bundle.putString("ADDRESS", bar.getAddress());

                //Add the bundle to the intent
                intent.putExtras(bundle);
=======
                intent.putExtra("BarName", bar.getName());
                intent.putExtra("BARINFO",(Serializable)bar);

>>>>>>> 0b94b4196b457a1d79d14c8775964e78a8fb000d
                startActivity(intent);

                //intent.putExtra("BarName", bar.getName());
                //intent.putExtra("BARINFO",(Serializable)bar);
                //intent.putExtra("BARINFO",(Serializable)bar);
                //startActivity(intent);
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





    private String distanceTo(Location user, Location bar, Double lat, Double longi){
        Location bars = new Location(bar);
        bars.setLatitude(lat);
        bars.setLongitude(longi);
        Double dist = user.distanceTo(bars)/ 1000 * 0.621371;
        String distanceCut = new DecimalFormat("#.00").format(dist);
        return "Distance to Bar: Approximately " + distanceCut + " Miles";
    }


    private void setCardStackAdapter() {
        bars = new ArrayList<>();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {


            location1 = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

        }


<<<<<<< HEAD
        CardInfo bar1 = new CardInfo(R.drawable.ingersolltap,"Ingersoll Tap",distance(location1.getLatitude(), location1.getLongitude(), 41.5860, -93.6558),"- $", "Grab your friends for a fun-filled night at Ingersoll Tap! Stop by the pub and try the large selection of wells, domestics, and wines while snacking on the complementary popcorn. Located in the heart of Ingersoll, Ingersoll Tap is open from 2:00 PM-2:00 AM. Be on the lookout for trivia nights!", "The perfect place to share a craft beer with some friends.", "Address: 2837 Ingersoll Ave, Des Moines, IA 50312");
        bars.add(bar1);
        CardInfo bar2 = new CardInfo(R.drawable.junipermoon,"Juniper Moon",distance(location1.getLatitude(), location1.getLongitude(), 41.574349, -93.610430),"- $$", "Juniper Moon is the place to go when looking for a classy night out. Spend the night in a Great Gatsby-esque environment with drinks to choose form such as Moscow Mules, Dai Dai My Darling, and Manhattans. This intimate cocktail lounge is the perfect place to get romantic with a significant other or to meet new friends over a bottle of wine. Juniper Moon is open from 4:00 PM-2:00 AM.", "Des Moines' premier cocktail bar", "Address: 2005 Ingersoll Ave, Des Moines, IA 50309");
        bars.add(bar2);
        CardInfo bar3 = new CardInfo(R.drawable.starbar,"Star Bar",distance(location1.getLatitude(), location1.getLongitude(), 41.585998, -93.655039),"- $$", "Looking to share some dinner and drinks over the sound of live music? If so, Star Bar has got you covered. This relaxed bar is a go-to for any after work drinks or a night on the town with friends. Serving everything from brunch and burgers to martinis and beer--Star Bar has something for everyone and every occasion. Star Bar is open from 11:00 AM-2:00 AM.", "A casual establishment serving all from brunch until bar close", "Address: 2811 Ingersoll Ave, Des Moines, IA 50312");
        bars.add(bar3);
        CardInfo bar4 = new CardInfo(R.drawable.zimms,"Zimm's Food and Spirits",distance(location1.getLatitude(), location1.getLongitude(), 41.585880, -93.660528),"- $", "Zimm's Food and Spirits is where you meet up with buddies after work and end up staying all night talking, drinking, and watching whatever game is on TV. Serving lunch and dinner in addition to drinks, this bar is also great for a fun family night out! Zimm's Food and Spirits is open 11:00 AM-2:00 AM.", "Family-friendly environment for lunch, dinner, and drinks", "Address: 3124 Ingersoll Ave, Des Moines, IA 50312");
        bars.add(bar4);
        CardInfo bar5 = new CardInfo(R.drawable.wellmanspub,"Wellman's Pub",distance(location1.getLatitude(), location1.getLongitude(), 41.584682, -93.610114),"- $", "By day, Wellman's Pub is the sports bar you want to spend every game day at. As the day goes on, the pub seamlessly transitions into the center of nightlife on Ingersoll. From breakfast and coffee to dinner and cocktails, Wellman's has it all! Wellman's Pub is open 11:00 AM-2:00 AM.", "The place to be for everything from family dinner a night out with friends", "Address: 2920 Ingersoll Ave, Des Moines, IA 50312");
=======

        Location ingersollLocation1 = new Location("");
        Location juniperMoon1 = new Location("");
        Location starBar = new Location("");
        Location zimms = new Location("");
        Location wellmans = new Location("");


        CardInfo bar1 = new CardInfo(R.drawable.ingersolltap,"Ingersoll Tap",distanceTo(location1, ingersollLocation1,41.5860 , -93.6558),"$");
        bars.add(bar1);
        CardInfo bar2 = new CardInfo(R.drawable.junipermoon,"Juniper Moon",distanceTo(location1, juniperMoon1, 41.58599330000001, -93.64405720000002),"$$");
        bars.add(bar2);
        CardInfo bar3 = new CardInfo(R.drawable.starbar,"Star Bar",distanceTo(location1, starBar, 41.585998, -93.655039),"$$");
        bars.add(bar3);
        bars.add(bar4);
        CardInfo bar5 = new CardInfo(R.drawable.wellmanspub,"Wellman's Pub",distanceTo(location1, wellmans, 41.5857186, -93.6575507),"$");
>>>>>>> 0b94b4196b457a1d79d14c8775964e78a8fb000d
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