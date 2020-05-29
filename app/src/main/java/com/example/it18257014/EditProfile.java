package com.example.it18257014;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it18257014.Database.DBHelper;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText username, dob, password;
    Button search,edit,delete;
    RadioButton male, female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.et4username);
        dob = findViewById(R.id.et4dob);
        password = findViewById(R.id.et4password);
        search = findViewById(R.id.btnsearch);
        edit = findViewById(R.id.btnedit);
        delete = findViewById(R.id.btndelete);
        male = findViewById(R.id.rbtn3male);
        female = findViewById(R.id.rbtn3female);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                List user = dbHelper.readAllInfo(username.getText().toString());

               if(user.isEmpty()){
                   Toast.makeText(EditProfile.this, "No User", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(EditProfile.this, "User Found! User: "+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                   username.setText(user.get(0).toString());
                   dob.setText(user.get(1).toString());
                   password.setText(user.get(2).toString());
                   if(user.get(3).toString().equals("Male")){
                       male.setChecked(true);
                   }
                   else {
                       female.setChecked(true);
                   }
               }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }

                DBHelper dbHelper = new DBHelper(getApplicationContext());

                Boolean status = dbHelper.updateInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);
                if(status){
                    Toast.makeText(EditProfile.this, "User Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditProfile.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.deleteInfo(username.getText().toString());

                Toast.makeText(EditProfile.this, "User Deleted", Toast.LENGTH_SHORT).show();

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(false);
                female.setChecked(false);
            }
        });
    }
}
