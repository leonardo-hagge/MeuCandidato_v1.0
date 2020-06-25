package com.example.meucandidato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NovidadesFragment extends Fragment {
    private static final String argsText = "txtView";
    TextView txtNovidades;
    String texto = " merda";

    public static NovidadesFragment newInstance(String texto) {
        NovidadesFragment nf = new NovidadesFragment();
        Bundle args = new Bundle();
        args.putString(argsText,texto);
        nf.setArguments(args);
        return nf;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_novidades,container,false);
            txtNovidades = (TextView) v.findViewById(R.id.txtNovidades);
        if(getArguments() != null){
            texto = getArguments().getString(argsText);
       }
            txtNovidades.setText(texto);
        return v;
    }
}
