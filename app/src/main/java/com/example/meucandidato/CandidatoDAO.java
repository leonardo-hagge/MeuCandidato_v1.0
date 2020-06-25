package com.example.meucandidato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;

public class CandidatoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;
    private String TABLE_NAME_CANDIDATO = "CANDIDATO";

    public CandidatoDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }
    public long inserirCandidato(Candidato candidato){
        ContentValues values = new ContentValues();
        values.put("NOME",candidato.getNome());
        values.put("SOBRENOME" , candidato.getSobrenome());
        values.put("DATA_NASCIMENTO_TEXT", String.valueOf(candidato.getDataNascimentoText()));
        values.put("EMAIL", candidato.getEmail());
        values.put("SENHA", candidato.getSenha());
        values.put("NUM_PARTIDO",candidato.getNumPartido());
        values.put("NUM_CANDIDATO",candidato.getNumCampanha());
        values.put("NOME_LOGIN",candidato.getNomeLogin());
        return banco.insert("CANDIDATO",null,values);
    }

    public long updateCandidato(Candidato candidato){
        ContentValues values = new ContentValues();
        values.put("NOME",candidato.getNome());
        values.put("SOBRENOME" , candidato.getSobrenome());
        values.put("DATA_NASCIMENTO_TEXT", String.valueOf(candidato.getDataNascimentoText()));
        values.put("EMAIL", candidato.getEmail());
        values.put("NUM_PARTIDO",candidato.getNumPartido());
        values.put("NUM_CANDIDATO",candidato.getNumCampanha());
        if(candidato.getSenha() != null && !candidato.getSenha().equals("")){
        values.put("SENHA", candidato.getSenha());
        }
        return banco.update(TABLE_NAME_CANDIDATO, values,"ID = ?", new  String[] {String.valueOf(candidato.getId())});
    }

    public long deleteCandidato(Candidato cand){
    return banco.delete(TABLE_NAME_CANDIDATO,"ID = ?",new String[]{String.valueOf(cand.getId())});
    }
}
