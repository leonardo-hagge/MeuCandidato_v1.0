package com.example.meucandidato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EleitorDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;
    private String TABLE_NAME_ELEITOR =  "ELEITOR";

    public EleitorDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
   }
   public long inserirEleitor(Eleitor eleitor){
       ContentValues values = new ContentValues();
       values.put("NOME",eleitor.getNome());
       values.put("SOBRENOME" , eleitor.getSobrenome());
       values.put("DATA_NASCIMENTO_TEXT", String.valueOf(eleitor.getDataNascText()));
       values.put("EMAIL", eleitor.getEmail());
       values.put("SENHA", eleitor.getSenha());
       values.put("NOME_LOGIN",eleitor.getNomeLogin());
       return banco.insert("ELEITOR",null,values);
    }
    public long updateEleitor(Eleitor eleit){
        ContentValues values = new ContentValues();
        values.put("NOME",eleit.getNome());
        values.put("SOBRENOME" , eleit.getSobrenome());
        values.put("DATA_NASCIMENTO_TEXT", String.valueOf(eleit.getDataNascText()));
        values.put("EMAIL", eleit.getEmail());
        if(eleit.getSenha() != null && !eleit.getSenha().equals("")){
            values.put("SENHA", eleit.getSenha());
        }
        return banco.update(TABLE_NAME_ELEITOR, values,"ID = ?", new  String[] {String.valueOf(eleit.getId())});
    }

    public long deleteEleitor(Eleitor eleit){
        return banco.delete(TABLE_NAME_ELEITOR,"ID = ?",new String[]{String.valueOf(eleit.getId())});
    }



}
