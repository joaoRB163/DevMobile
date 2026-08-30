package com.example.aula04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Button b = findViewById(R.id.butao2);

        b.setOnClickListener(view -> {
            Intent intencao = new Intent(this, MainActivity.class);
            startActivity(intencao);
        });
    }
}
