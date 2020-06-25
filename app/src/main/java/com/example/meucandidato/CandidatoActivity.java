package com.example.meucandidato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class CandidatoActivity extends AppCompatActivity {
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatoBr = new SimpleDateFormat("dd-MM-yyyy");
    private int NOTIFICATION_ID = 001;
    private final String CHANNEL_ID = "channel";
    private CandidatoDAO dao;
    private Button btnCancel;
    private Button btnCad;
    private Button btnExcluir;
    private EditText editName;
    private EditText editSobrenome;
    private EditText editNomeLogin;
    private EditText editDataNasc;
    private EditText editEmail;
    private EditText editNumPart;
    private EditText editNumCand;
    private EditText editSenha;
    private EditText editConfSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato);
        dao = new CandidatoDAO(this);
        editNomeLogin = (EditText) findViewById(R.id.editNomeLogin);
        editName = (EditText) findViewById(R.id.editName);
        editSobrenome = (EditText) findViewById(R.id.editSobrenome);
        editDataNasc = (EditText) findViewById(R.id.editDataNasc);
        editDataNasc.addTextChangedListener(Mask.insert("##/##/####", editDataNasc));
        editEmail = (EditText) findViewById(R.id.editEmail);
        editNumPart = (EditText) findViewById(R.id.editNumPart);
        editNumCand = (EditText) findViewById(R.id.editNumCand);
        editSenha = (EditText) findViewById(R.id.editSenha);
        editConfSenha = (EditText) findViewById(R.id.editConfSenha);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCad = (Button) findViewById(R.id.btnCad);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        if (getIntent().getSerializableExtra("Candidato") != null) {
            Candidato cand = (Candidato) getIntent().getSerializableExtra("Candidato");

            editName.setText(cand.getNome());

            editSobrenome.setText(cand.getSobrenome());

            editDataNasc.setText(cand.getDataNascimentoText());

            editNomeLogin.setVisibility(View.INVISIBLE);

            editEmail.setText(cand.getEmail());

            editNumPart.setText(String.valueOf(cand.getNumPartido()));
            editNumCand.setText(String.valueOf(cand.getNumCampanha()));
            btnExcluir.setVisibility(View.VISIBLE);
            btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Candidato cand = (Candidato) getIntent().getSerializableExtra("Candidato");
                    long ret = dao.deleteCandidato(cand);
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
                if ((editSenha != null && editConfSenha != null && (String.valueOf(editSenha.getText()).equals(String.valueOf(editConfSenha.getText()))))
                        || (getIntent().getSerializableExtra("Candidato") != null)) {
                    if (!String.valueOf(editName.getText()).equals("")) {
                        if (!String.valueOf(editSobrenome.getText()).equals("")) {
                            if (!editNomeLogin.getText().toString().equals("") || getIntent().getSerializableExtra("Candidato") != null) {
                                if (!String.valueOf(editDataNasc.getText()).equals("")) {
                                    if (!String.valueOf(editEmail.getText()).equals("")) {
                                        if (!String.valueOf(editNumPart.getText()).equals("")) {
                                            if (!String.valueOf(editNumCand.getText()).equals("")) {
                                                if ((!String.valueOf(editSenha.getText()).equals("") && !String.valueOf(editConfSenha.getText()).equals(""))
                                                        || (getIntent().getSerializableExtra("Candidato") != null)) {
                                                    Candidato candidato = new Candidato(0, null, null);
                                                    candidato.setNome(editName.getText().toString());
                                                    candidato.setSobrenome(editSobrenome.getText().toString());
                                                    candidato.setDataNascimentoText(editDataNasc.getText().toString());
                                                    candidato.setNomeLogin(editNomeLogin.getText().toString());
                                                    candidato.setEmail(editEmail.getText().toString());
                                                    candidato.setNumPartido(Integer.parseInt(editNumPart.getText().toString()));
                                                    candidato.setNumCampanha(Integer.parseInt(editNumCand.getText().toString()));
                                                    candidato.setSenha(editSenha.getText().toString());
                                                    if (getIntent().getSerializableExtra("Candidato") != null) {
                                                        Candidato cand = (Candidato) getIntent().getSerializableExtra("Candidato");
                                                        candidato.setId(cand.getId());
                                                        long ret = dao.updateCandidato(candidato);
                                                        if (ret != 0) {
                                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                                            intent.putExtra("Candidato", candidato);
                                                            startActivity(intent);
                                                            Toast.makeText(getApplicationContext(), "Cadastro atualizado com sucesso ", Toast.LENGTH_SHORT).show();

                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "Não foi possivél atualizar este cadastro", Toast.LENGTH_SHORT).show();
                                                        }

                                                    } else {

                                                        long ret = dao.inserirCandidato(candidato);
                                                        if (ret != 0) {
                                                            String title = "Meu Candidato";
                                                            String text = " Sejá muito bem vindo ao Meu Candidato," +
                                                                    " saiba que você acabou de dar um passo a frente" +
                                                                    " de seus concorrentes que ainda não utilizam este App"
                                                                    + " Aproveite este tempo de vantagem a vá personalizando" +
                                                                    " seu perfil destacando seus valores e suas propostas" +
                                                                    " ou comece a deslizar a vontade para"
                                                                    + " ver seus companheiros de campanha";
                                                            String activity = "HomeActivity";
                                                            enviaNotificacao(title, text, activity);
                                                            candidato.setId((int) ret);
                                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                                            intent.putExtra("Candidato", candidato);
                                                            startActivity(intent);
                                                            Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();

                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "Não foi possivél finalizar este cadastro", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }


                                                } else {
                                                    Toast.makeText(getApplicationContext(), " Você não informou sua senha ", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(getApplicationContext(), " Você não informou o seu numero de candidato ", Toast.LENGTH_SHORT).show();
                                            }

                                        } else {
                                            Toast.makeText(getApplicationContext(), " Você não informou o numero do seu partido ", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), " Você não informou seu e-mail ", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), " Você não informou sua data de nascimento ", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), " Você não informou seu nome para login", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), " Você não informou seu sobrenome ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), " Você não informou seu nome ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), " Suas senhas não coincidem ", Toast.LENGTH_SHORT).show();
                }


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getSerializableExtra("Candidato") != null) {
                    Candidato cand = (Candidato) getIntent().getSerializableExtra("Candidato");
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("Candidato", cand);
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
//    public void createNotificationChannel(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
//                    "Channel 1", NotificationManager.IMPORTANCE_HIGH);
//            channel.setDescription("This is channel 1");
//
//            NotificationManager manager = getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//
//
//        }
//    }
//    private static PendingIntent getPendingIntent(Context context, Intent intent, int id,String activity){
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        switch (activity){
//            case "HomeActivity":
//
//                stackBuilder.addNextIntentWithParentStack(new Intent(context,HomeActivity.class ));
//                break;
//            case "MainActivity":
//                stackBuilder.addNextIntentWithParentStack(new Intent(context, MainActivity.class));
//                break;
//        }
//
//        stackBuilder.addNextIntent(intent);
//        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
//        return p;
//    }
}
