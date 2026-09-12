package com.example.atvd01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    String[] frases = {
            "O pão olhou para a geladeira e decidiu virar astronauta.",
            "Nunca confie em uma cadeira que conhece seu nome.",
            "Hoje o Wi-Fi acordou com vontade de plantar batatas.",
            "Se a lua piscar três vezes, provavelmente esqueceu a senha.",
            "Meu chinelo pediu férias e foi morar dentro do micro-ondas.",
            "O elevador sabe demais, mas prefere não comentar.",
            "Uma capivara invisível acabou de aprovar este código.",
            "Às 14:37, todos os garfos pensam brevemente em fugir.",
            "O tomate não respondeu porque estava em uma reunião importante.",
            "Se nada fizer sentido, coloque um chapéu no problema e continue."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button shareB = findViewById(R.id.ShareButton);

        ImageButton refreshB = findViewById(R.id.refreshButton);
        ImageButton clearB = findViewById(R.id.clearButton);

        TextInputEditText tInput = findViewById(R.id.editText);


        Random r = new Random();

        refreshB.setOnClickListener(v -> {
            tInput.setText(frases[r.nextInt(frases.length)]);
        });

        clearB.setOnClickListener(v -> {
            tInput.setText("");
        });

        shareB.setOnClickListener(v -> {
            String mensagem = tInput.getText().toString() + "\n\n\nMensagem enviada do appzin maldito";

            Uri gifUri = prepararGif();

            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setType("image/gif");

            intent.putExtra(
                    Intent.EXTRA_TEXT,
                    mensagem
            );

            intent.putExtra(
                    Intent.EXTRA_STREAM,
                    gifUri
            );

            intent.addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            );

            startActivity(
                    Intent.createChooser(
                            intent,
                            "Compartilhar"
                    )
            );
        });
    }

    private Uri prepararGif() {

        try {

            InputStream inputStream =
                    getResources().openRawResource(R.raw.yeah);

            File arquivoGif =
                    new File(getCacheDir(), "yeah.gif");

            FileOutputStream outputStream =
                    new FileOutputStream(arquivoGif);

            byte[] buffer = new byte[1024];
            int tamanho;

            while ((tamanho = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, tamanho);
            }

            inputStream.close();
            outputStream.close();

            return FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".fileprovider",
                    arquivoGif
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}