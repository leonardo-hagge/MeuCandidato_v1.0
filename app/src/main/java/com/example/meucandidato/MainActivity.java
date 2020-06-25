package com.example.meucandidato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    EditText nomeLogin,passwordLogin;
    Button btn;
    Intent intent;
    TextView txtNewCand;
    TextView txtNewEleit;
    private UsuarioDAO uDAO;

    ImageView imgView;

    public String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uDAO = new UsuarioDAO(this);
        nomeLogin = (EditText) findViewById(R.id.nameUser);
        passwordLogin = (EditText) findViewById(R.id.passUser);
        txtNewCand = (TextView) findViewById(R.id.txtNewCand);
        txtNewEleit = (TextView) findViewById(R.id.txtNewEleit);
        txtNewCand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),  CandidatoActivity.class);
                startActivity(intent);
            }
        });
        txtNewEleit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EleitorActivity.class);
                startActivity(intent);
            }
        });
        btn = (Button) findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(passwordLogin.getEditableText().toString().equals("") && nomeLogin.getEditableText().toString().equals("") ){
                    Toast.makeText(MainActivity.this,"Você não informou os campos necessarios " ,Toast.LENGTH_SHORT).show();
                } else if(passwordLogin.getEditableText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Você não informou sua senha " + nomeLogin.getEditableText().toString(),Toast.LENGTH_SHORT).show();
                }else if(nomeLogin.getEditableText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Você não informou seu nome de usuario " ,Toast.LENGTH_SHORT).show();
                }else{
                      Usuario u = new Usuario(0,null,null);
                      u.setNomeLogin(nomeLogin.getEditableText().toString());
                      u.setSenha(passwordLogin.getEditableText().toString());
                      Candidato cand = uDAO.isCandidato(u);
                      if(cand != null){
                       Toast.makeText(MainActivity.this,"Você efetuou o login corretamente  " + nomeLogin.getEditableText().toString() ,Toast.LENGTH_SHORT).show();
                       intent = new Intent(MainActivity.this, HomeActivity.class);
                       intent.putExtra("Candidato",cand);
                       startActivity(intent);
                      }else{
                          Eleitor elei = uDAO.isEleitor(u);
                          if(elei != null){
                              Toast.makeText(MainActivity.this,"Você efetuou o login corretamente  " + nomeLogin.getEditableText().toString() ,Toast.LENGTH_SHORT).show();
                              intent = new Intent(MainActivity.this, HomeActivity.class);
                              intent.putExtra("Eleitor",elei);
                              startActivity(intent);

                          }
                          Toast.makeText(MainActivity.this,"Usuario ou senha invalida ",Toast.LENGTH_SHORT).show();
                           }

//                    setContentView(R.layout.home);
//                    TextView  textView = (TextView) findViewById(R.id.usuario);
//                    textView.setText(nomeUsuario);
                }

            }
        });




//        eText = (EditText)findViewById(R.id.nameUser);
//        String usuario = eText.getEditableText().toString();

    }


//public void buttonLogin(View V){
//    eText = (EditText)findViewById(R.id.nameUser);
//    String usuario = eText.getEditableText().toString();
//
//    eText = (EditText)findViewById(R.id.passUser);
//    String passUser = eText.getEditableText().toString();
//
//
//
//
//
//}

//public void exibeImagem(View v){
//    imgView = (ImageView) v;
//    Drawable drawable  = imgView.getDrawable();
//    ImageView imgView2 = (ImageView) findViewById(R.id.imageCenter);
//    Drawable drawable2 = imgView2.getDrawable();
//     imgView2.setImageDrawable(drawable);
//    imgView.setImageDrawable(drawable2);
//
//}
//
//public void exibeAllInform(View v){
//    setContentView(R.layout.detalhes_candidato);
//    TextView  textView = (TextView) findViewById(R.id.detNameUser);
//    textView.setText(nomeUsuario);
//
//}





}
