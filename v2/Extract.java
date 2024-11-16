package com.example.v2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Extract extends AppCompatActivity {
    int a;
    int ans;
    int b;
    Button btnembed;
    Button btnload;
    ByteArrayOutputStream bytes;
    int[] cipher;
    String ciphertext = BuildConfig.FLAVOR;
    String curFileName;
    Uri currImageURI;
    EditText et;
    File f;
    FileOutputStream fo;
    int g;
    int h;
    int i;
    File imgFile;
    ImageView iv;
    int j;
    int k = 0;
    int lettercount = -1;
    Bitmap myBitmap;
    Bitmap myBitmap2;
    int p;
    int p2;
    String plaintext;
    int r;
    TextView tvinfo;
    int w;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_extract);
        this.iv = (ImageView) findViewById(R.id.imageView);
        this.et = (EditText) findViewById(R.id.etplaintext);
        this.btnload = (Button) findViewById(R.id.btnload);
        this.tvinfo = (TextView) findViewById(R.id.textView9);
        this.btnembed = (Button) findViewById(R.id.btnembed);
        this.btnembed.setEnabled(false);
        this.btnembed.setEnabled(false);
        this.btnload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Extract.this.tvinfo.setVisibility(8);
                Extract.this.iv.setVisibility(0);
                Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.setAction("android.intent.action.GET_CONTENT");
                Extract.this.startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                Extract.this.btnembed.setEnabled(true);
            }
        });
        this.btnembed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Extract.this.h = Extract.this.myBitmap.getHeight();
                    Extract.this.w = Extract.this.myBitmap.getWidth();
                    Extract.this.k = 0;
                    Extract.this.cipher = new int[(Extract.this.h * Extract.this.w)];
                    Extract.this.i = 0;
                    while (true) {
                        if (Extract.this.i >= Extract.this.w) {
                            break;
                        }
                        Extract.this.j = 0;
                        while (true) {
                            if (Extract.this.j >= Extract.this.h) {
                                break;
                            }
                            Extract.this.p = Extract.this.myBitmap.getPixel(Extract.this.i, Extract.this.j);
                            Extract.this.a = (Extract.this.p >> 24) & 255;
                            Extract.this.r = (Extract.this.p >> 16) & 255;
                            Extract.this.g = (Extract.this.p >> 8) & 255;
                            Extract.this.b = Extract.this.p & 255;
                            Extract.this.cipher[Extract.this.k] = ((Extract.this.r & 3) << 4) + ((Extract.this.g & 3) << 2) + (Extract.this.b & 3);
                            if (Extract.this.cipher[Extract.this.k] >= 63) {
                                Extract.this.lettercount = Extract.this.k;
                                break;
                            }
                            Extract.this.k++;
                            Extract.this.j++;
                        }
                        if (Extract.this.lettercount == Extract.this.k) {
                            break;
                        }
                        Extract.this.i++;
                    }
                    Extract.this.i = 0;
                    while (Extract.this.i < Extract.this.lettercount) {
                        Extract.this.et.setText(Extract.this.et.getText().toString() + Extract.this.getplain(Extract.this.cipher[Extract.this.i]));
                        Extract extract = Extract.this;
                        extract.i = extract.i + 1;
                    }
                    Toast.makeText(Extract.this.getBaseContext(), "Author information got extracted from the image from the fake author's book Successfully!", 1).show();
                } catch (Exception e) {
                    Toast.makeText(Extract.this.getBaseContext(), "Error while extracting the author information!" + e, 1).show();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == 1) {
            try {
                this.currImageURI = data.getData();
                this.imgFile = new File(data.getData().getPath().toString());
                this.myBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), this.currImageURI);
                this.iv.setImageBitmap(this.myBitmap);
                Toast.makeText(getBaseContext(), "Image from the fake author's book got loaded Successfully!", 1).show();
            } catch (Exception e) {
                Context baseContext = getBaseContext();
                Toast.makeText(baseContext, "Error while loading the image from the fake author's book!\n:" + e, 1).show();
            }
        }
    }

    public char getplain(int c) {
        if (c == 1) {
            return 'e';
        }
        if (c == 2) {
            return 't';
        }
        if (c == 3) {
            return 'a';
        }
        if (c == 4) {
            return 'o';
        }
        if (c == 5) {
            return 'i';
        }
        if (c == 6) {
            return 'n';
        }
        if (c == 7) {
            return 's';
        }
        if (c == 8) {
            return 'h';
        }
        if (c == 9) {
            return 'r';
        }
        if (c == 10) {
            return 'd';
        }
        if (c == 11) {
            return 'l';
        }
        if (c == 12) {
            return 'c';
        }
        if (c == 13) {
            return 'u';
        }
        if (c == 14) {
            return 'm';
        }
        if (c == 15) {
            return 'w';
        }
        if (c == 16) {
            return 'f';
        }
        if (c == 17) {
            return 'g';
        }
        if (c == 18) {
            return 'y';
        }
        if (c == 19) {
            return 'p';
        }
        if (c == 20) {
            return 'b';
        }
        if (c == 21) {
            return 'v';
        }
        if (c == 22) {
            return 'k';
        }
        if (c == 23) {
            return 'j';
        }
        if (c == 24) {
            return 'x';
        }
        if (c == 25) {
            return 'q';
        }
        if (c == 26) {
            return 'z';
        }
        if (c == 27) {
            return '0';
        }
        if (c == 28) {
            return '1';
        }
        if (c == 29) {
            return '2';
        }
        if (c == 30) {
            return '3';
        }
        if (c == 31) {
            return '4';
        }
        if (c == 32) {
            return '5';
        }
        if (c == 33) {
            return '6';
        }
        if (c == 34) {
            return '7';
        }
        if (c == 35) {
            return '8';
        }
        if (c == 36) {
            return '9';
        }
        if (c == 37) {
            return '.';
        }
        if (c == 38) {
            return ' ';
        }
        if (c == 39) {
            return 'A';
        }
        if (c == 40) {
            return 'B';
        }
        if (c == 41) {
            return 'C';
        }
        if (c == 42) {
            return 'D';
        }
        if (c == 43) {
            return 'E';
        }
        if (c == 44) {
            return 'F';
        }
        if (c == 45) {
            return 'G';
        }
        if (c == 46) {
            return 'H';
        }
        if (c == 47) {
            return 'I';
        }
        if (c == 48) {
            return 'J';
        }
        if (c == 49) {
            return 'K';
        }
        if (c == 50) {
            return 'L';
        }
        if (c == 51) {
            return 'M';
        }
        if (c == 52) {
            return 'N';
        }
        if (c == 53) {
            return 'O';
        }
        if (c == 54) {
            return 'P';
        }
        if (c == 55) {
            return 'Q';
        }
        if (c == 56) {
            return 'R';
        }
        if (c == 57) {
            return 'S';
        }
        if (c == 58) {
            return 'T';
        }
        if (c == 59) {
            return 'U';
        }
        if (c == 60) {
            return 'V';
        }
        if (c == 61) {
            return 'W';
        }
        if (c == 62) {
            return 'X';
        }
        if (c == 0) {
            return ',';
        }
        return ' ';
    }
}
