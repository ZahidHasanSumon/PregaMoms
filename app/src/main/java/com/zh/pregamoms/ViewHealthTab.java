package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.zh.pregamoms.R.layout;

public class ViewHealthTab extends AppCompatActivity {
    private TextView text;
    private String data = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_view_health_tab);
        text = findViewById(R.id.one);

        Intent intent = getIntent();
        data = intent.getExtras().getString("YourValueKey");
        data.replace("\n", "\n");
        text.setText(data);
    }
}