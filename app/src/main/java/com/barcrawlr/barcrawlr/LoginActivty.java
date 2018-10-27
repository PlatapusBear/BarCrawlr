package com.barcrawlr.barcrawlr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivty extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView newUser;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        newUser = (TextView) findViewById(R.id.newUser);
        loginButton = (Button) findViewById(R.id.login_button);

        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Achievement> achievements = realm.where(Achievement.class).findAll();

        if(achievements.size()==0){
            populateAchievements();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateAccount.class);
                startActivity(intent);
            }
        });
    }

    public void populateAchievements(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Achievement achievement1 = new Achievement();
                Achievement achievement2 = new Achievement();
                Achievement achievement3 = new Achievement();
                Achievement achievement4 = new Achievement();
                Achievement achievement5 = new Achievement();
                Achievement achievement6 = new Achievement();

                Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.incompleteachievement);
                Bitmap image3 = BitmapFactory.decodeResource(getResources(), R.drawable.mysteryachievement);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] incomplete = stream.toByteArray();

                image3.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] mystery = stream.toByteArray();

                achievement1.setName("Choosy");
                achievement1.setImage(incomplete);
                achievement1.setDescription("Earn this by being selective and swipe left \nfive times in one day");
                achievement1.setAchieved(false);

                achievement2.setName("Country Award");
                achievement2.setImage(incomplete);
                achievement2.setDescription("Earn this achievement after visiting three \ndifferent country bars");
                achievement2.setAchieved(false);

                achievement3.setName("Dive In");
                achievement3.setImage(incomplete);
                achievement3.setDescription("Visit three dive bars");
                achievement3.setAchieved(false);

                achievement4.setName("First Bar");
                achievement4.setImage(incomplete);
                achievement4.setDescription("Congratulations, you visited your first bar \nusing Bar Crawl!");
                achievement4.setAchieved(false);

                achievement5.setName("Returner");
                achievement5.setImage(incomplete);
                achievement5.setDescription("Visit the same bar three times");
                achievement5.setAchieved(false);

                achievement6.setName("???");
                achievement6.setImage(mystery);
                achievement6.setDescription("Keeps swiping to unlock mystery achievements!");
                achievement6.setAchieved(false);

                realm.copyToRealmOrUpdate(achievement1);
                realm.copyToRealmOrUpdate(achievement2);
                realm.copyToRealmOrUpdate(achievement3);
                realm.copyToRealmOrUpdate(achievement4);
                realm.copyToRealmOrUpdate(achievement5);
                realm.copyToRealmOrUpdate(achievement6);
            }
        });
    }
}
