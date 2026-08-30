package com.example.aula03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random r = new Random();

    int max = 0, min = 0;

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

        Button b = findViewById(R.id.button);
        TextView t = findViewById(R.id.textView);
        EditText edMax = findViewById(R.id.edMax);
        EditText edMin = findViewById(R.id.edMin);


        b.setOnClickListener(view -> {

            if(edMax.getText().toString().isEmpty()){
                edMax.setError("Insira o maior valor aqui, macaco");
                t.setText("PARA DE SER IDIOTA E COMPLETA ESSA PORRA COM UM NÚMERO");
                return;
            }

            if(edMin.getText().toString().isEmpty()){
                edMin.setError("Insira o maior valor aqui, macaco");
                t.setText("PARA DE SER IDIOTA E COMPLETA ESSA PORRA COM UM NÚMERO");
                return;
            }

            max = Integer.parseInt(edMax.getText().toString());
            min = Integer.parseInt(edMin.getText().toString());

            if(min > max || min == max){
                Toast.makeText(this, "PARA DE SER BURRO", Toast.LENGTH_SHORT).show();
                t.setText("PARA DE SER BURRO E COLOCA O MAIOR NA ESQUERDA\nToma uma dica aqui, seu asno");
                edMin.setError("MIN");
                edMax.setError("MAX");
                edMin.setHint("MIN");
                edMax.setHint("MAX");
                return;
            }



            int tot = r.nextInt(min, max);

            t.setText(Integer.toString(tot));
        });
    }
}