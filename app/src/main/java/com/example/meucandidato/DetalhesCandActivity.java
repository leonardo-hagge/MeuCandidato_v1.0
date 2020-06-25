package com.example.meucandidato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class DetalhesCandActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_cand);
//        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

//para Toolbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater menuInflater  = getMenuInflater();
//        getMenuInflater().inflate(R.menu.main,menu);
//        return true;
//    }

    //Para o ActionBar
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch(item.getItemId()){
//            case R.id.home:
//                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//                startActivity(intent);
//                return true;
//
////            case:
////                break;
////            case:
////                break;
////            case:
////                break;
//        }
//
//        return true;
//    }

//para Toolbar
//    public boolean onOptionsItemSelected(MenuItem item){
//        String msg="";
//        switch(item.getItemId()){
//            case R.id.home_tob:
//                msg="Meu Cnadidato";
//                break;
//
//            case R.id.home:
//                msg="Home";
//                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);
//                break;
////            case:
////                break;
////            case:
////                break;
//        }
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        return true;
//    }
}
