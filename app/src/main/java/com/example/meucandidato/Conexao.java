package com.example.meucandidato;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {
    private static final String name = "MeuCandidato.db";
    private static final int version = 1;

    public Conexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE ELEITOR(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NOME VARHAR(40)," +
            "SOBRENOME VARCHAR(40)," +
            "NOME_LOGIN VARCHAR(60)," +
            "DATA_NASCIMENTO DATE," +
            "DATA_NASCIMENTO_TEXT VARCHAR(10)," +
            "EMAIL VARCHAR(40)," +
            "SENHA VARCHAR(20))");


        db.execSQL("CREATE TABLE CANDIDATO(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME VARHAR(40)," +
                "SOBRENOME VARCHAR(40)," +
                "NOME_LOGIN VARCHAR(60)," +
                "DATA_NASCIMENTO DATE," +
                "DATA_NASCIMENTO_TEXT VARCHAR(10)," +
                "EMAIL VARCHAR(40)," +
                "NUM_PARTIDO INTEGER," +
                "NUM_CANDIDATO INTEGER," +
                "SENHA VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
