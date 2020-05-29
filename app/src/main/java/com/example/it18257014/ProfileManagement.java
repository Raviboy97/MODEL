package com.example.it18257014;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it18257014.Database.DBHelper;

public class ProfileManagement extends AppCompatActivity {

    EditText username, dob, password;
    Button updateprofile,add;
    RadioButton male, female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.et3username);
        dob = findViewById(R.id.et3dob);
        password = findViewById(R.id.et3password);
        updateprofile = findViewById(R.id.btnupdate);
        add = findViewById(R.id.btnadd);
        male = findViewById(R.id.rbtn2male);
        female = findViewById(R.id.rbtn2female);

        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                long newID = dbHelper.addInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);

                //Toast message
                Toast.makeText(ProfileManagement.this, "User Added. User ID:" +newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(false);


            }
        });
    }
}
