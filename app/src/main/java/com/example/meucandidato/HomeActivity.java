package com.example.meucandidato;




import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        private int NOTIFICATION_ID = 001;
        private final String CHANNEL_ID = "channel";
        ImageView img, img2, img3, img4;
        TextView nomeCand, info, textNovidades;
        Toolbar toolbar;
        Bundle b;
        Candidato cand = new Candidato(0,null,null);
        private TextView descUsuario;
        private DrawerLayout drawerLayout;
        private ActionBarDrawerToggle mtoggle;
        private CandidatoDAO cDAO;
        private EleitorDAO eDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
        View headerView = nv.getHeaderView(0);
        descUsuario = (TextView)  headerView.findViewById(R.id.descUsuario);;

        mtoggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        if(getIntent().getSerializableExtra("Candidato") != null){
           Candidato cand = (Candidato) getIntent().getSerializableExtra("Candidato");
            descUsuario.setText("Candidato: " + cand.getNome() + " " + cand.getSobrenome() + " - " + cand.getEmail());
            descUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),CandidatoActivity.class);
                    Candidato cand = (Candidato) getIntent().getSerializableExtra("Candidato");
                    intent.putExtra("Candidato",cand);
                    startActivity(intent);
                }
            });
            if(savedInstanceState == null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        }else if(getIntent().getSerializableExtra("Eleitor") != null){
            Eleitor eleit = (Eleitor) getIntent().getSerializableExtra("Eleitor");
            descUsuario.setText("Eleitor: " + eleit.getNome() + " " + eleit.getSobrenome() + " - " + eleit.getEmail());
            descUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Eleitor eleit = (Eleitor) getIntent().getSerializableExtra("Eleitor");
                    Intent intent = new Intent(getApplicationContext(), EleitorActivity.class);
                    intent.putExtra("Eleitor",eleit);
                    startActivity(intent);
                }
            });
            if(savedInstanceState == null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }


        }else{
      if(getIntent().getExtras() != null ) {
            b = getIntent().getExtras();
            String fragment = b.getString("fragment");
                    switch(fragment){
                        case "NovidadesFragment()":
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, NovidadesFragment.newInstance(b.getString("txtView"))).commit();
                            break;
                    }
        }else if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }

        }
}



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_detCand:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeatalhesCandFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }





    public void exibeImagem(View v) {
            ImageView imgView = (ImageView) v;
            Drawable drawable = imgView.getDrawable();
            ImageView imgView2 = (ImageView) findViewById(R.id.imageCenter);
            Drawable drawable2 = imgView2.getDrawable();
            imgView2.setImageDrawable(drawable);
            imgView.setImageDrawable(drawable2);
        }

    public void exibeAllInform(View v){

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeatalhesCandFragment()).commit();
        enviaNotificacao();







//        Intent intent = new Intent(this,DetalhesCandActivity.class);
//        startActivity(intent);
//    setContentView(R.layout.detalhes_candidato);
//    TextView  textView = (TextView) findViewById(R.id.detNameUser);
//    textView.setText(nomeUsuario);

}

    public void enviaNotificacao(){
        String title = "Preucurando novidades";
        String text  = "Clique aqui para visualizar as novidades do app";
        Intent intent = new Intent(this,HomeActivity.class);
        Bundle b = new Bundle();
        b.putString("txtView","Desculpe nenhuma novidade por enquanto");
        b.putString("fragment","NovidadesFragment()");
        intent.putExtras(b);
        PendingIntent p = getPendingIntent(this,intent,1);
        Notification notification  = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_add_to_photos_black_24dp)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setContentIntent(p).build();



        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }
    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel 1", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("This is channel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);


        }
    }
    private static PendingIntent getPendingIntent(Context context, Intent intent, int id){
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(new Intent(context, MainActivity.class));
        stackBuilder.addNextIntent(intent);
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }



}



