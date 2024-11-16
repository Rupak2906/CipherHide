package com.example.v2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
    Button cancel;
    String emailid;
    EditText etomail;
    EditText etomobile;
    EditText etoname;
    EditText etopwd;
    EditText etorepwd;
    EditText etousername;
    String mobileno;
    SQLiteDatabase mydatabase;
    String name;
    String password;
    String repassword;
    Cursor resultSet;
    Button submit;
    String username;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_registration);
        this.etoname = (EditText) findViewById(R.id.etname);
        this.etomobile = (EditText) findViewById(R.id.etmobile);
        this.etomail = (EditText) findViewById(R.id.etemail);
        this.etousername = (EditText) findViewById(R.id.etusername);
        this.etopwd = (EditText) findViewById(R.id.editTextpwd);
        this.etorepwd = (EditText) findViewById(R.id.editTextrepwd);
        this.cancel = (Button) findViewById(R.id.buttoncancel);
        this.submit = (Button) findViewById(R.id.buttonsubmit);
        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Registration.this.finish();
                Registration.this.startActivity(new Intent(Registration.this.getBaseContext(), MainActivity.class));
            }
        });
        this.submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    Registration.this.mydatabase = Registration.this.openOrCreateDatabase("users", 0, (SQLiteDatabase.CursorFactory) null);
                    Registration.this.mydatabase.execSQL("CREATE TABLE IF NOT EXISTS user(Name VARCHAR,Mobile VARCHAR, Email varhcar,Username VARCHAR,Password VARCHAR);");
                    Registration.this.name = Registration.this.etoname.getText().toString();
                    Registration.this.mobileno = Registration.this.etomobile.getText().toString();
                    Registration.this.emailid = Registration.this.etomail.getText().toString();
                    Registration.this.username = Registration.this.etousername.getText().toString();
                    Registration.this.password = Registration.this.etopwd.getText().toString();
                    Registration.this.repassword = Registration.this.etorepwd.getText().toString();
                    if (Registration.this.password.equals(Registration.this.repassword)) {
                        Registration registration = Registration.this;
                        SQLiteDatabase sQLiteDatabase = Registration.this.mydatabase;
                        registration.resultSet = sQLiteDatabase.rawQuery("Select * from user where Username='" + Registration.this.username + "'", (String[]) null);
                        if (Registration.this.resultSet.moveToFirst()) {
                            Toast.makeText(Registration.this.getBaseContext(), "User Name Already Exist!", 1).show();
                        } else {
                            SQLiteDatabase sQLiteDatabase2 = Registration.this.mydatabase;
                            sQLiteDatabase2.execSQL("INSERT INTO user VALUES('" + Registration.this.name + "','" + Registration.this.mobileno + "','" + Registration.this.emailid + "','" + Registration.this.username + "','" + Registration.this.password + "');");
                            Toast.makeText(Registration.this.getBaseContext(), "successfully created!", 1).show();
                        }
                        Registration.this.finish();
                        Registration.this.startActivity(new Intent(Registration.this.getBaseContext(), MainActivity.class));
                        return;
                    }
                    Toast.makeText(Registration.this.getBaseContext(), "Password Not Matches!", 1).show();
                } catch (Exception e) {
                    Context baseContext = Registration.this.getBaseContext();
                    Toast.makeText(baseContext, "Error:" + e, 1).show();
                }
            }
        });
    }
}
