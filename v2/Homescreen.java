package com.example.v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class Homescreen extends AppCompatActivity {
    ImageButton btnextracting;
    ImageButton btnhiding;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_homescreen);
        this.btnhiding = (ImageButton) findViewById(R.id.imageButtonhiding);
        this.btnextracting = (ImageButton) findViewById(R.id.imageButtonextracting);
        this.btnhiding.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Homescreen.this.startActivity(new Intent(Homescreen.this.getBaseContext(), Embed.class));
            }
        });
        this.btnextracting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Homescreen.this.startActivity(new Intent(Homescreen.this.getBaseContext(), Extract.class));
            }
        });
    }
}
