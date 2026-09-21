package com.example.aula05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NomesAdapter extends ArrayAdapter<String> {

    public NomesAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        convertView = inflater.inflate(R.layout.item_lista, null);
        String nome = getItem(position);
        TextView textN = convertView.findViewById(R.id.textN);
        EditText editNome = convertView.findViewById(R.id.edNome);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        textN.setText(Integer.toString(position + 1));
        editNome.setText(nome);
        return convertView;
    }
}
