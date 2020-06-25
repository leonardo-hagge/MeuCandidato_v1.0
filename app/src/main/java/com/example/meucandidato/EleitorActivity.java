package com.example.meucandidato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EleitorActivity extends AppCompatActivity {
    private EleitorDAO dao;
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    private int NOTIFICATION_ID = 001;
    private final String CHANNEL_ID = "channel";
    private Button btnCancel;
    private Button btnCad;
    private Button btnExcluir;
    private EditText editNome;
    private EditText editSobrenome;
    private EditText editNomeLogin;
    private EditText editDataNasc;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editConfSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleitor);
        dao = new EleitorDAO(this);
        editNome = (EditText) findViewById(R.id.editNome);
        editSobrenome = (EditText) findViewById(R.id.editSobrenome);
        editNomeLogin = (EditText) findViewById(R.id.editNomeLogin);
        editDataNasc = (EditText) findViewById(R.id.editDataNasc);
        editDataNasc.addTextChangedListener(Mask.insert("##/##/####", editDataNasc));
        editEmail = (EditText) findViewById(R.id.editEmail);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        editConfSenha = (EditText) findViewById(R.id.editConfSenha);
        btnCad = (Button) findViewById(R.id.btnCad);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        if (getIntent().getSerializableExtra("Eleitor") != null) {
            Eleitor eleit = (Eleitor) getIntent().getSerializableExtra("Eleitor");

            editNome.setText(eleit.getNome());

            editSobrenome.setText(eleit.getSobrenome());

            editDataNasc.setText(eleit.getDataNascText());

            editNomeLogin.setVisibility(View.INVISIBLE);

            editEmail.setText(eleit.getEmail());


            btnExcluir.setVisibility(View.VISIBLE);
            btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Eleitor eleit = (Eleitor) getIntent().getSerializableExtra("Eleitor");
                    long ret = dao.deleteEleitor(eleit);
                    if (ret != 0) {
                        String title = "Meu Candidato";
                        String text = " Você acaba de excluir sua conta" +
                                " esperamos que tenha gostado do nosso app assim como esperamos que volte logo \n" +
                                ":-(";
                        String activity = "MainActivity";
                        enviaNotificacao(title, text, activity);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Não foi possivel excluir sua conta" + String.valueOf(ret), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }




        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if((editSenha != null && editConfSenha != null && (String.valueOf(editSenha.getText()).equals(String.valueOf(editConfSenha.getText()))))
                ||  getIntent().getSerializableExtra("Eleitor") != null) {
                  if(!String.valueOf(editNome.getText()).equals("")){
                      if(!String.valueOf(editSobrenome.getText()).equals("")){
                          if(!editNomeLogin.getText().toString().equals("") || getIntent().getSerializableExtra("Eleitor") != null){
                          if(!String.valueOf(editDataNasc.getText()).equals("")){
                              if(!String.valueOf(editEmail.getText()).equals("")){
                                  if((!String.valueOf(editSenha.getText()).equals("") && !String.valueOf(editConfSenha.getText()).equals(""))
                                  || getIntent().getSerializableExtra("Eleitor") != null){
                                      Eleitor eleitor = new Eleitor(0,null,null);
                                      eleitor.setNome(editNome.getText().toString());
                                      eleitor.setSobrenome(editSobrenome.getText().toString());
                                      eleitor.setDataNascText(editDataNasc.getText().toString());
                                      eleitor.setEmail(editEmail.getText().toString());
                                      eleitor.setSenha(editSenha.getText().toString());
                                      eleitor.setNomeLogin(editNomeLogin.getText().toString());

                                      if(getIntent().getSerializableExtra("Eleitor") != null){
                                          Eleitor eleit = (Eleitor) getIntent().getSerializableExtra("Eleitor");
                                          eleitor.setId(eleit.getId());
                                          long ret = dao.updateEleitor(eleitor);
                                          if(ret != 0){
                                              Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                              intent.putExtra("Eleitor",eleitor);
                                              startActivity(intent);

                                          }else{
                                              Toast.makeText(getApplicationContext(),"Não foi possivel atualizar este cadastro ",Toast.LENGTH_SHORT).show();
                                          }
                                      }else{
                                          long ret = dao.inserirEleitor(eleitor);
                                          if(ret != 0){
                                              String title= "Meu Candidato";
                                              String text = " Seja muito bem vindo ao Meu Candidato, " +
                                                      " espero que saiba que acabou de dar um grande passo como eleitor consciente" +
                                                      " pois com este app conseguirá conheçer melhor seus candidatos";
                                              String activity = "HomeActivity";
                                              enviaNotificacao(title,text,activity);
                                              eleitor.setId((int)ret);
                                              Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                              intent.putExtra("Eleitor",eleitor);
                                              startActivity(intent);

                                          }else{
                                              Toast.makeText(getApplicationContext(),"Não foi possivél finalizar este cadastro",Toast.LENGTH_SHORT).show();
                                          }
                                      }
                                    }else{
                                      Toast.makeText(getApplicationContext()," Você não informou sua senha ",Toast.LENGTH_SHORT).show();
                                  }

                              }else{
                                  Toast.makeText(getApplicationContext()," Você não informou seu e-mail ",Toast.LENGTH_SHORT).show();
                              }

                          }else{
                              Toast.makeText(getApplicationContext()," Você não informou sua data de nascimento ",Toast.LENGTH_SHORT).show();
                          }
                          }else{
                              Toast.makeText(getApplicationContext()," Você não informou seu nome para login ",Toast.LENGTH_SHORT).show();
                          }

                      }else{
                          Toast.makeText(getApplicationContext()," Você não informou seu sobrenome ",Toast.LENGTH_SHORT).show();
                      }
                  }else{
                      Toast.makeText(getApplicationContext()," Você não informou seu nome ",Toast.LENGTH_SHORT).show();
                  }
              }else{
                  Toast.makeText(getApplicationContext()," Suas senhas não coincidem ",Toast.LENGTH_SHORT).show();
              }
            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getSerializableExtra("Eleitor") != null) {
                    Eleitor eleit = (Eleitor) getIntent().getSerializableExtra("Eleitor");
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("Eleitor", eleit);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
    public void enviaNotificacao(String title, String text, String activity) {

        Intent intent = new Intent(this, HomeActivity.class);
//        PendingIntent p = getPendingIntent(this,intent,1,activity);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_add_to_photos_black_24dp)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .setAutoCancel(true).build();
//                .setContentIntent(p).build();


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }
}
