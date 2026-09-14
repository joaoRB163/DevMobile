package com.example.atvd01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExemploActivityExplicita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Define qual XML será usado como tela desta Activity
        setContentView(R.layout.activity_exemplo_explicita);

        // Liga o TextView do XML ao Java
        TextView mensagemRecebida = findViewById(R.id.mensagemRecebida);

        // Liga o botão de voltar do XML ao Java
        Button voltarButton = findViewById(R.id.voltarButton);

        // Recebe a mensagem enviada pela MainActivity usando a mesma chave "mensagem"
        String mensagem = getIntent().getStringExtra("mensagem");

        // Mostra a mensagem recebida na tela
        mensagemRecebida.setText(mensagem);

        // Ao clicar em voltar, fecha esta Activity e retorna para a anterior
        voltarButton.setOnClickListener(v -> {
            finish();
        });
    }
}