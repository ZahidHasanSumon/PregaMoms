package com.zh.pregamoms;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ViewImage extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        imageView = findViewById(R.id.image);

        Intent intent = getIntent();
        //Bundle extras = intent.getExtras();
        final String placeName=intent.getStringExtra("EXTRA_title");
        final String bitmap_string=intent.getStringExtra("EXTRA_photo");

        //final String bitmap_string = extras.getString("EXTRA_photo");
        //final String title_string = extras.getString("EXTRA_title");
       // final String password_string = extras.getString("EXTRA_Password");

        Bitmap image = null;
        try {
            image = decodeFromFirebaseBase64(bitmap_string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(image);
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
}