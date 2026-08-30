package com.example.aula_01;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    boolean darkmode = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.textView);
        Button b = findViewById(R.id.button);


        tv.setText("Catapimbas");
        b.setText("Carambolas");
        b.setTextSize(20);
        b.setOnClickListener(view -> {
            if(darkmode){
                findViewById(R.id.main).setBackgroundColor(Color.GRAY);
                tv.setTextColor(Color.WHITE);
                darkmode = false;
            } else {
                findViewById(R.id.main).setBackgroundColor(Color.WHITE);
                tv.setTextColor(Color.rgb(0, 0, 0));
                darkmode = true;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}