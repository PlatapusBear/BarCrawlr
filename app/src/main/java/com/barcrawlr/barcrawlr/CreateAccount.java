package com.barcrawlr.barcrawlr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText reenter;
    Button createAccount;
    CheckBox twentyOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        reenter = (EditText) findViewById(R.id.reenter);
        createAccount = (Button) findViewById(R.id.createAccount);
        twentyOne = (CheckBox) findViewById(R.id.twentyOne);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!twentyOne.isChecked()){
                    Toast.makeText(CreateAccount.this, "You must be 21 to use this app", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
