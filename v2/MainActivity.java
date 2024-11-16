package com.example.v2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btncancel;
    Button btnewuser;
    Button btnlogin;
    EditText etpw;
    EditText etun;
    SQLiteDatabase mydatabase;
    String password;
    Cursor resultSet;
    String username;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView((int) R.layout.activity_main);
        this.etun = (EditText) findViewById(R.id.etname);
        this.etpw = (EditText) findViewById(R.id.editTextpw);
        this.btncancel = (Button) findViewById(R.id.buttoncancel);
        this.btnewuser = (Button) findViewById(R.id.buttonnewuser);
        this.btnlogin = (Button) findViewById(R.id.buttonlogin);
        this.btnlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.finish();
                MainActivity.this.startActivity(new Intent(MainActivity.this.getBaseContext(), Homescreen.class));
            }
        });
        this.btnewuser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.finish();
                MainActivity.this.startActivity(new Intent(MainActivity.this.getBaseContext(), Registration.class));
            }
        });
        this.btncancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });
    }
}
