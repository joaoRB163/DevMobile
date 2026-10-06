package com.example.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    TextView tv;

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

        // criação do banco
        database = openOrCreateDatabase("DB", MODE_PRIVATE, null);

        // criação de uma tabela
        database.execSQL("CREATE TABLE IF NOT EXISTS eventos (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "value1 REAL(5,2)," +
                "value2 REAL(5,2)," +
                "value3 REAL(5,2))");

        // vai guaradar os valores
        ContentValues contentValues = new ContentValues();

        // inserindo os valores que deverão ser guardados
        contentValues.put("value1", 1);
        contentValues.put("value2", 2);
        contentValues.put("value3", 3);

        // Log só para mostrar que inseriu no banco
        Log.v("evento", "Inserido: " + contentValues);

        // inserindo no banco de fato
        database.insert("eventos", null, contentValues);

        tv = findViewById(R.id.tv);
        Float values[] = new Float[3];
        values = getAllEvents().get(0).getValues();
        tv.setText(values[0].toString());
    }

    // função para buscar os dados
    public ArrayList<Eventos> getAllEvents(){
        // para guardar os eventos
        ArrayList<Eventos> result = new ArrayList<>();

        // cursor para receber os dados
        Cursor cursor = database.rawQuery("SELECT * FROM eventos LIMIT ?", new String[]{"1000"});

        // como o cursor para no útlimo registro lido, move ele para o começo
        cursor.moveToFirst();

        // ler o cursor
        // enquanto o próximo não é o último
        while (!cursor.isAfterLast()){

            // salvar os dados do cursor
            result.add(
                    new Eventos(
                        cursor.getInt(0),
                        new Float[]{
                            cursor.getFloat(1),
                            cursor.getFloat(2),
                            cursor.getFloat(3)
                        }
                    )
            );
        }

        return result;
    }

}