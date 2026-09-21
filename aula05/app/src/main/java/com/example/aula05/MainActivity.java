package com.example.aula05;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String[] nomes = new String[]{"Helena", "Lívia", "Rômulo", "Pedro", "Jão", "Aureo"};

    ListView lv;

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

        lv = findViewById(R.id.listview);

        // Criação do adaptador (quem vai construir a informação)
        // ctrl + q mostra as possibilidades de preenchimento
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, //contexto
                android.R.layout.item_lista, // layout pronto do SDK. CTRL + Botão esquerdo abre o layout
                android.R.id.text1, // elemento que vai receber a informação
                nomes // array com elementos (dados)
                );*/

        // adapter com layout personalizado
        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_lista,
                R.id.edNome,
                nomes
        );*/

        // adapter personalizado
        NomesAdapter adapter = new NomesAdapter(
                this,
                R.layout.item_lista,
                R.id.edNome,
                nomes
        );

        // colocando o adaptador na listview
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this, nomes[i], Toast.LENGTH_LONG).show();
        });
    }
}