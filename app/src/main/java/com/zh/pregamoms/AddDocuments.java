package com.zh.pregamoms;

import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.zh.pregamoms.R.layout.activity_add_documents;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddDocuments extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;
    private TextView hback;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button save;
    private String dType = "";
    private static final int REQUEST_IMAGE_CAPTURE = 111;
    private Bitmap imageBitmap;
    private EditText titleD;
    Date c = Calendar.getInstance().getTime();

    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
    String newDateStr  = df.format(c);

    //String newDateStr = postFormater.format(dateObj);

    public AddDocuments() throws ParseException {
        //Toast.makeText(getApplicationContext(), "Something went wrong!!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_add_documents);
        radioGroup = findViewById(R.id.radioGroup);
        hback = findViewById(R.id.hback);
        save = findViewById(R.id.save);
        imageView = findViewById(R.id.imageView);
        titleD = findViewById(R.id.titleD);



        hback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                dType = radioButton.getText().toString();
                encodeBitmapAndSaveToFirebase(imageBitmap);



            }
        });

       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onLaunchCamera();

           }
       });

    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AddDocuments.this.RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(AddDocuments.this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
           // imageView.setImageBitmap();
        }
    }

    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
//        DatabaseReference ref = FirebaseDatabase.getInstance()
//                .getReference("Users")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child(mRestaurant.getPushId())
//                .child("imageUrl");
        String title = titleD.getText().toString().trim();
        Documents documents = new Documents(title, newDateStr, imageEncoded);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Documents").child(dType).push();
        ref.setValue(documents).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(AddDocuments.this, "Added Successfully!", Toast.LENGTH_LONG).show();
                    //startActivity(new Intent(SignUpSecondPage.this, MainActivity.class));
                    //finish();
                } else {
                    //display a failure message
                    Toast.makeText(AddDocuments.this,"Please try again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}