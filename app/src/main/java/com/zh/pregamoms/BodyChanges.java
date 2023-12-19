package com.zh.pregamoms;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zh.pregamoms.classes.Info;

import org.jetbrains.annotations.NotNull;

public class BodyChanges extends AppCompatActivity {
    private Button weekButton;
    private LinearLayout linear;
    private Info info;
    private String val = " ";
    private TextView body, fetus, text11, text111;
    private ImageView fimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_changes);
        linear = findViewById(R.id.weelayout);
        info = new Info();
        body = findViewById(R.id.bchange);
        fetus = findViewById(R.id.fetusg);
        text11 = findViewById(R.id.text11);
        text111 = findViewById(R.id.text111);
        fimage = findViewById(R.id.fimage);


        for (int i = 1; i < 43; i++) {
            Button button = new Button(this);
            button.setId(i);
            //Typeface font = Typeface.createFromAsset(getAssets(), "poppins_regular.ttf");
            final int id = button.getId();
            button.setTypeface(ResourcesCompat.getFont(BodyChanges.this, R.font.poppins_regular));
            button.setText("Week"+id);


            button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            String text = button.getText().toString().trim();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("info").child("BodyChange");
            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("info").child("FetusGrowth").child(text);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BodyChanges.this, text, Toast.LENGTH_SHORT).show();
                    text11.setText("Body Changes in "+text);
                    text111.setText("Fetus Growth in "+text);

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            val = snapshot.child(text).getValue(String.class);
                            //String value = snapshot.getValue(getActivity());
                            //val = info.getWeek1();
                            body.setText(val);


                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                    ref1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            val = snapshot.child("Details").getValue(String.class);
                            String image = snapshot.child("ImageLink").getValue(String.class);
                            //String value = snapshot.getValue(getActivity());
                            //val = info.getWeek1();
                            fetus.setText(val);
                            Glide.with(BodyChanges.this)
                                    .load(image)
                                    .override(1000, 500)
                                    .into(fimage);


                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
            });
            if (linear != null) {
                linear.addView(button);
            }
        }
    }
}