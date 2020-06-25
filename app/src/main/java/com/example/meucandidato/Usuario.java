package com.example.meucandidato;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {
    public int id;
    public String nomeLogin;
    public String senha;

    public Usuario(int id, String nomeLogin, String senha) {

        setId(id);
        setNomeLogin(nomeLogin);
        setSenha(senha);
    }
    public String getNomeLogin() {
        return nomeLogin;
    }

    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
