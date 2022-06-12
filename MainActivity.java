package com.example.dailynews;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_id);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
            startActivity(intent);
        });
    }



}
