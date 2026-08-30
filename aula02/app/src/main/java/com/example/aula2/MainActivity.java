package com.example.aula2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button butao = findViewById(R.id.button);
        Button reset = findViewById(R.id.button2);
        TextView t = findViewById(R.id.textView);

        Random random = new Random();

        butao.setOnClickListener(view -> {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            findViewById(R.id.main).setBackgroundColor(Color.rgb(r, g, b));
            i++;
            t.setText("Hello World!\n" + "\t\t\t\t" + Integer.toString(i));
        });

        reset.setOnClickListener(view -> {
            findViewById(R.id.main).setBackgroundColor(Color.rgb(255, 255, 255));
            i = 0;
            t.setText("Hello World!");
        });

    }
}