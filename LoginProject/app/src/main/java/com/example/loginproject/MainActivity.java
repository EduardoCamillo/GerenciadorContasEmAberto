package com.example.loginproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterLocalidade.OnClick {

    private AdapterLocalidade adapterLocalidade;
    private SwipeableRecyclerView rvLocalidades;
    private SQLiteDatabase db;
    private LocalidadeDAO localidadeDAO;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localidadeDAO = new LocalidadeDAO(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Localidades");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);

        rvLocalidades = findViewById(R.id.rvLocalidades);


        configRecyclerView();

    }

    private void configRecyclerView(){
        rvLocalidades.setLayoutManager(new LinearLayoutManager(this));
        rvLocalidades.setHasFixedSize(true);

        //declarando adapter
        adapterLocalidade = new AdapterLocalidade(localidadeDAO.getListLocalidade(), this);
        rvLocalidades.setAdapter(adapterLocalidade);

        rvLocalidades.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {

            }

            @Override
            public void onSwipedRight(int position) {
                localidadeDAO.getListLocalidade().remove(position);
                //excluindo o item do adapter
                adapterLocalidade.notifyItemRemoved(position);
            }
        });
    }



    @Override
    public void OnClickListener(Localidade localidade) {
        //Intent intent = new Intent(this, FromLocalidadeActivity.class);
        Toast.makeText(getApplicationContext(), "Selecionado: " + localidade.getNome_localidade(),Toast.LENGTH_SHORT).show();
        //intent.putExtra("localidade", localidade);
        //startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idMenu = item.getItemId();

        if(idMenu == R.id.menu_add){
            startActivity(new Intent(this, FromLocalidadeActivity.class));
        }

        return true;
    }
}