package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterLocalidade.OnClick {

    private AdapterLocalidade adapterLocalidade;
    private  List<Localidade> localidadeList = new ArrayList<>();
    private SwipeableRecyclerView rvLocalidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLocalidades = findViewById(R.id.rvLocalidades);

        carregaLista();

        configRecyclerView();

    }

    private void configRecyclerView(){
        rvLocalidades.setLayoutManager(new LinearLayoutManager(this));
        rvLocalidades.setHasFixedSize(true);

        //declarando adapter
        adapterLocalidade = new AdapterLocalidade(localidadeList, this);
        rvLocalidades.setAdapter(adapterLocalidade);

        rvLocalidades.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) {

            }
        });
    }

    private void carregaLista(){
        Localidade localidade1 = new Localidade();
        localidade1.setNome_localidade("DROGASIL");

        Localidade localidade2 = new Localidade();
        localidade2.setNome_localidade("DROGA RAIA");

        Localidade localidade3 = new Localidade();
        localidade3.setNome_localidade("DROGARIA SAOPAULO");

        Localidade localidade4 = new Localidade();
        localidade4.setNome_localidade("FARMAIS");

        Localidade localidade5 = new Localidade();
        localidade5.setNome_localidade("PAGUE MENOS");

        localidadeList.add(localidade1);
        localidadeList.add(localidade2);
        localidadeList.add(localidade3);
        localidadeList.add(localidade4);
        localidadeList.add(localidade5);
    }

    @Override
    public void OnClickListener(Localidade localidade) {
        Toast.makeText(this, localidade.getNome_localidade() + "Opa", Toast.LENGTH_SHORT).show();
    }
}