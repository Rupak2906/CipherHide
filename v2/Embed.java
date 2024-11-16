package com.example.v2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

public class Embed extends AppCompatActivity {
    int a;
    int ans;
    int b;
    Button btnembed;
    Button btnencrypt;
    Button btnload;
    Button btnsave;
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
    int lettercount;
    Bitmap myBitmap;
    Bitmap myBitmap2;
    int p;
    int p2;
    String plaintext;
    int r;
    TextView tv8;
    TextView tvencrpted;
    int w;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_embed);
        this.iv = (ImageView) findViewById(R.id.imageView);
        this.et = (EditText) findViewById(R.id.etplaintext);
        this.btnload = (Button) findViewById(R.id.btnload);
        this.btnencrypt = (Button) findViewById(R.id.btnencrypt);
        this.btnembed = (Button) findViewById(R.id.btnembed);
        this.btnsave = (Button) findViewById(R.id.btnsave);
        this.tvencrpted = (TextView) findViewById(R.id.tvencrpyted);
        this.tv8 = (TextView) findViewById(R.id.textView8);
        this.btnembed.setEnabled(false);
        this.btnsave.setEnabled(false);
        this.btnload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Embed.this.iv.setVisibility(0);
                Embed.this.tv8.setVisibility(8);
                Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.setAction("android.intent.action.GET_CONTENT");
                Embed.this.startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                Embed.this.btnembed.setEnabled(true);
            }
        });
        this.btnencrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Embed embed = Embed.this;
                embed.ciphertext = BuildConfig.FLAVOR;
                embed.plaintext = embed.et.getText().toString();
                Embed embed2 = Embed.this;
                embed2.lettercount = embed2.plaintext.length();
                Embed embed3 = Embed.this;
                embed3.cipher = new int[embed3.lettercount];
                int i = 0;
                Embed.this.i = 0;
                while (Embed.this.i < Embed.this.lettercount) {
                    int[] iArr = Embed.this.cipher;
                    int i2 = Embed.this.i;
                    Embed embed4 = Embed.this;
                    iArr[i2] = embed4.getcipher(embed4.plaintext.charAt(Embed.this.i));
                    Embed.this.i++;
                }
                Embed embed5 = Embed.this;
                while (true) {
                    embed5.i = i;
                    if (Embed.this.i < Embed.this.lettercount) {
                        Embed.this.ciphertext = Embed.this.ciphertext + Embed.this.cipher[Embed.this.i];
                        embed5 = Embed.this;
                        i = embed5.i + 1;
                    } else {
                        Embed.this.tvencrpted.setText(Embed.this.ciphertext);
                        return;
                    }
                }
            }
        });
        this.btnembed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Embed.this.h = Embed.this.myBitmap.getHeight();
                    Embed.this.w = Embed.this.myBitmap.getWidth();
                    Embed.this.k = 0;
                    Embed.this.i = 0;
                    while (Embed.this.i < Embed.this.w) {
                        Embed.this.j = 0;
                        while (Embed.this.j < Embed.this.h) {
                            Embed.this.p = Embed.this.myBitmap.getPixel(Embed.this.i, Embed.this.j);
                            Embed.this.a = (Embed.this.p >> 24) & 255;
                            Embed.this.r = (Embed.this.p >> 16) & 255;
                            Embed.this.g = (Embed.this.p >> 8) & 255;
                            Embed.this.b = Embed.this.p & 255;
                            if (Embed.this.k < Embed.this.lettercount) {
                                Embed.this.r = (Embed.this.r & 252) + ((Embed.this.cipher[Embed.this.k] & 48) >> 4);
                                Embed.this.g = (Embed.this.g & 252) + ((Embed.this.cipher[Embed.this.k] & 12) >> 2);
                                Embed.this.b = (Embed.this.b & 252) + ((Embed.this.cipher[Embed.this.k] & 3) >> 0);
                                Embed.this.k++;
                            } else if (Embed.this.k == Embed.this.lettercount) {
                                Embed.this.r = (Embed.this.r & 252) + 3;
                                Embed.this.g = (Embed.this.g & 252) + 3;
                                Embed.this.b = (Embed.this.b & 252) + 3;
                                Embed.this.k++;
                            }
                            Embed.this.p2 = (Embed.this.a << 24) | (Embed.this.r << 16) | (Embed.this.g << 8) | Embed.this.b;
                            Embed.this.myBitmap2.setPixel(Embed.this.i, Embed.this.j, Embed.this.p2);
                            Embed.this.j++;
                        }
                        Embed.this.i++;
                    }
                    Toast.makeText(Embed.this.getBaseContext(), "Original Author Information Successfully Embedded!", 1).show();
                    Embed.this.btnsave.setEnabled(true);
                } catch (Exception e) {
                    Toast.makeText(Embed.this.getBaseContext(), "Error occurred while Embedding the Original Author Information! \n" + e, 1).show();
                }
            }
        });
        this.btnsave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Embed.this.bytes = new ByteArrayOutputStream();
                    Embed.this.myBitmap2.compress(Bitmap.CompressFormat.PNG, 100, Embed.this.bytes);
                    Embed.this.f = new File(Environment.getExternalStorageDirectory(), "Target.png");
                    Embed.this.f.createNewFile();
                    Embed.this.fo = new FileOutputStream(Embed.this.f);
                    Embed.this.fo.write(Embed.this.bytes.toByteArray());
                    Embed.this.fo.close();
                    Toast.makeText(Embed.this.getBaseContext(), "Image saved in the name of 'Target.PNG' in internal memory Successfully!\n This saved image can be used for preparing E-Book!", 1).show();
                } catch (Exception e) {
                    Context baseContext = Embed.this.getBaseContext();
                    Toast.makeText(baseContext, "Error!" + e, 1).show();
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
                this.myBitmap2 = this.myBitmap.copy(Bitmap.Config.ARGB_8888, true);
                this.iv.setImageBitmap(this.myBitmap);
                Toast.makeText(getBaseContext(), "Image to be included in the E-Book is Successfully Loaded!", 1).show();
            } catch (Exception e) {
                Context baseContext = getBaseContext();
                Toast.makeText(baseContext, "Error:" + e, 1).show();
            }
        }
    }

    public int getcipher(char c) {
        if (c == 'e') {
            return 1;
        }
        if (c == 't') {
            return 2;
        }
        if (c == 'a') {
            return 3;
        }
        if (c == 'o') {
            return 4;
        }
        if (c == 'i') {
            return 5;
        }
        if (c == 'n') {
            return 6;
        }
        if (c == 's') {
            return 7;
        }
        if (c == 'h') {
            return 8;
        }
        if (c == 'r') {
            return 9;
        }
        if (c == 'd') {
            return 10;
        }
        if (c == 'l') {
            return 11;
        }
        if (c == 'c') {
            return 12;
        }
        if (c == 'u') {
            return 13;
        }
        if (c == 'm') {
            return 14;
        }
        if (c == 'w') {
            return 15;
        }
        if (c == 'f') {
            return 16;
        }
        if (c == 'g') {
            return 17;
        }
        if (c == 'y' || c == 'Y') {
            return 18;
        }
        if (c == 'p') {
            return 19;
        }
        if (c == 'b') {
            return 20;
        }
        if (c == 'v') {
            return 21;
        }
        if (c == 'k') {
            return 22;
        }
        if (c == 'j') {
            return 23;
        }
        if (c == 'x') {
            return 24;
        }
        if (c == 'q') {
            return 25;
        }
        if (c == 'z' || c == 'Z') {
            return 26;
        }
        if (c == '0') {
            return 27;
        }
        if (c == '1') {
            return 28;
        }
        if (c == '2') {
            return 29;
        }
        if (c == '3') {
            return 30;
        }
        if (c == '4') {
            return 31;
        }
        if (c == '5') {
            return 32;
        }
        if (c == '6') {
            return 33;
        }
        if (c == '7') {
            return 34;
        }
        if (c == '8') {
            return 35;
        }
        if (c == '9') {
            return 36;
        }
        if (c == '.') {
            return 37;
        }
        if (c == ' ') {
            return 38;
        }
        if (c == 'A') {
            return 39;
        }
        if (c == 'B') {
            return 40;
        }
        if (c == 'C') {
            return 41;
        }
        if (c == 'D') {
            return 42;
        }
        if (c == 'E') {
            return 43;
        }
        if (c == 'F') {
            return 44;
        }
        if (c == 'G') {
            return 45;
        }
        if (c == 'H') {
            return 46;
        }
        if (c == 'I') {
            return 47;
        }
        if (c == 'J') {
            return 48;
        }
        if (c == 'K') {
            return 49;
        }
        if (c == 'L') {
            return 50;
        }
        if (c == 'M') {
            return 51;
        }
        if (c == 'N') {
            return 52;
        }
        if (c == 'O') {
            return 53;
        }
        if (c == 'P') {
            return 54;
        }
        if (c == 'Q') {
            return 55;
        }
        if (c == 'R') {
            return 56;
        }
        if (c == 'S') {
            return 57;
        }
        if (c == 'T') {
            return 58;
        }
        if (c == 'U') {
            return 59;
        }
        if (c == 'V') {
            return 60;
        }
        if (c == 'W') {
            return 61;
        }
        if (c == 'X') {
            return 62;
        }
        if (c == ',') {
            return 0;
        }
        return 38;
    }
}
