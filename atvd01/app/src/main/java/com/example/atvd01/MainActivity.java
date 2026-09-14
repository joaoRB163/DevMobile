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

        Button refreshB = findViewById(R.id.refreshButton);

        TextInputEditText tInput = findViewById(R.id.editText);

        Button explicitIntentButton = findViewById(R.id.ExplicitIntentButton);


        Random r = new Random();

        refreshB.setOnClickListener(v -> {
            tInput.setText(frases[r.nextInt(frases.length)]);
        });


        // Exemplo Intent Explicita
        explicitIntentButton.setOnClickListener(v -> {
            // Cria uma intent apontando diretamente para outra activity
            Intent intent = new Intent(
                    MainActivity.this,
                    ExemploActivityExplicita.class
            );
            //Envia uma mensagem para outra Activity de Exemplo
            intent.putExtra(
                    "mensagem",
                    "Esta mensagem foi enviada pela MainActivity!"
            );
            //Abre a Activity escolhida
            startActivity(intent);
        });

//        Botão de compartilhar
        shareB.setOnClickListener(v -> {
            /*Preparação da mensagem personalizada*/
            String mensagem = tInput.getText().toString() + "\n\n\nMensagem enviada do appzin maldito";

            /*Chamada da função para preparação do gif*/
            Uri gifUri = prepararGif();

            /*Preparando a intent para abrir um app externo*/
            Intent intent = new Intent(Intent.ACTION_SEND);

            /*Tipo de mensagem que vai ser enviada na intent*/
            intent.setType("image/gif");

            /*Dados que a intent vai enviar*/
            intent.putExtra(
                    Intent.EXTRA_TEXT,
                    mensagem
            );

            /*Dados que a intent vai enviar*/
            intent.putExtra(
                    Intent.EXTRA_STREAM,
                    gifUri
            );

            /*Permissão para outros apps lerem os arquivos que vão ser enviados*/
            intent.addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            );

            /*Abre a tela de compartilhamento*/
            startActivity(
                    Intent.createChooser(
                            intent,
                            "Compartilhar"
                    )
            );
        });
    }

    /*Função para poder transformar o gif num arquivo que a intent possa enviar junto*/
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