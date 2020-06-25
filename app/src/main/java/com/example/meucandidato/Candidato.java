package com.example.meucandidato;

import java.io.Serializable;
import java.util.Date;

public class Candidato extends Usuario implements Serializable {

    public Candidato(int id, String nomeLogin, String senha) {
        super(id, nomeLogin, senha);
    }

    private int id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String dataNascimentoText;
    private String email;
    private int numPartido;
    private int numCampanha;
    private String senha;

    public String getDataNascimentoText() {
        return dataNascimentoText;
    }

    public void setDataNascimentoText(String dataNascimentoText) {
        this.dataNascimentoText = dataNascimentoText;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumPartido() {
        return numPartido;
    }

    public void setNumPartido(int numPartido) {
        this.numPartido = numPartido;
    }

    public int getNumCampanha() {
        return numCampanha;
    }

    public void setNumCampanha(int numCampanha) {
        this.numCampanha = numCampanha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
