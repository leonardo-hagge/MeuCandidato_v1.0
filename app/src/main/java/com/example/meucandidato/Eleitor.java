package com.example.meucandidato;

import java.io.Serializable;
import java.util.Date;

public class Eleitor extends Usuario implements Serializable {

    public Eleitor(int id, String nomeLogin, String senha) {
        super(id, nomeLogin, senha);
    }


    private int id;
    private String nome;
    private String sobrenome;
    private Date dataNasc;
    private String dataNascText;
    private String email;

    public String getDataNascText() {
        return dataNascText;
    }

    public void setDataNascText(String dataNascText) {
        this.dataNascText = dataNascText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
