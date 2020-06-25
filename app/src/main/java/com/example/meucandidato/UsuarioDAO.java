package com.example.meucandidato;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsuarioDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;
    private String[] Columns_table_eleitor =
            {" ID "
                    , " NOME "
                    , " SOBRENOME "
                    , " NOME_LOGIN "
                    , " DATA_NASCIMENTO_TEXT "
                     , " EMAIL "
                    , " SENHA "};

    private String table_name_eleitor = "ELEITOR";

    private String[] Columns_table_candidato =
            {"ID"
                    , " NOME "
                    , " SOBRENOME "
                    , " NOME_LOGIN "
                    , " DATA_NASCIMENTO_TEXT "
                    , " EMAIL "
                    , " NUM_PARTIDO "
                    , " NUM_CANDIDATO "
                    , " SENHA "};
    private String table_name_candidato = "CANDIDATO";


    public UsuarioDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }


    public Eleitor isEleitor(Usuario u) {

        Eleitor elei = new Eleitor(0, null, null);
        String query = "SELECT ";
        for (int i = 0; i < Columns_table_eleitor.length; i++) {
            query += Columns_table_eleitor[i] + ((i + 1) == Columns_table_eleitor.length ? " " : ",");
        }
        query += "FROM " + table_name_eleitor + " WHERE SENHA = '" + u.getSenha() + "' AND NOME_LOGIN = '" + u.getNomeLogin() + "'";
        Cursor cursor = banco.rawQuery(query, null);

            if (cursor.moveToNext()) {
                elei = new Eleitor(cursor.getInt(0), cursor.getString(3), cursor.getString(6));
                elei.setNome(cursor.getString(1));
                elei.setSobrenome(cursor.getString(2));
                elei.setDataNascText(cursor.getString(4));
                elei.setEmail(cursor.getString(5));

            } else {
                return null;
            }
        return elei;
    }

    public Candidato isCandidato(Usuario u) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Candidato cand = new Candidato(0, null, null);
        String query = "SELECT ";
        for (int i = 0; i < Columns_table_candidato.length; i++) {
            query += Columns_table_candidato[i] + ((i + 1) == Columns_table_candidato.length ? " " : ",");
        }
        query += " FROM " + table_name_candidato + " WHERE SENHA = '" + u.getSenha() + "' AND NOME_LOGIN = '" + u.getNomeLogin() + "'";
        Cursor cursor = banco.rawQuery(query, null);
        if (cursor.moveToNext()) {
            cand = new Candidato(cursor.getInt(0), cursor.getString(3), cursor.getString(8));
            cand.setNome(cursor.getString(1));
            cand.setSobrenome(cursor.getString(2));
            cand.setDataNascimentoText(cursor.getString(4));
            cand.setEmail(cursor.getString(5));
            cand.setNumPartido(cursor.getInt(6));
            cand.setNumCampanha(cursor.getInt(7));
        } else {
            return null;
        }
        return cand;
    }


}
