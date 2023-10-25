package com.example.loginproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginproject.database.LocalidadeDAO;
import com.example.loginproject.database.model.Localidade;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

public class MainActivity extends AppCompatActivity implements AdapterLocalidade.OnClick {

    private AdapterLocalidade adapterLocalidade;
    private AdapterLocalidade adapterLocalidade2;
    private LinearLayout media_list;

    private SwipeableRecyclerView rvLocalidades;
    private SQLiteDatabase db;
    private LocalidadeDAO localidadeDAO;

    ActivityResultLauncher<Intent> someActivityResultLauncher;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localidadeDAO = new LocalidadeDAO(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Localidades");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);

        rvLocalidades = findViewById(R.id.rvLocalidades);
        media_list = findViewById(R.id.media_list);

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        updateList();
                    }
                });


        //configRecyclerViewSwipe();

        configMediaList();

    }

    private void configMediaList(){
        RecyclerView rv = new RecyclerView(this);
        RecyclerView.LayoutParams params = new
                RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        );
        rv.setLayoutParams(params);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapterLocalidade2 = new AdapterLocalidade(localidadeDAO.getListLocalidade(), this);
        rv.setAdapter(adapterLocalidade2);
        rv.setLayoutManager(layoutManager);
        rv.setVisibility(View.VISIBLE);

        media_list.addView(rv);
    }

    private void configRecyclerViewSwipe(){
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idMenu = item.getItemId();

        if(idMenu == R.id.menu_add){
            startActivityForResult();
        }

        return true;
    }

    public void startActivityForResult() {
        someActivityResultLauncher.launch(new Intent(this, FormLocalidadeActivity.class));
    }

    @Override
    public void onDeleteItem(int id) {
        localidadeDAO.apagarLocalidade(id);
        updateList();
    }

    @Override
    public void onAddItem(int id) {

    }

    public void updateList() {
        adapterLocalidade2.setLocalidadeList(localidadeDAO.getListLocalidade());
        adapterLocalidade2.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(Localidade localidade) {
        //Intent intent = new Intent(this, FormLocalidadeActivity.class);

        Toast.makeText(getApplicationContext(), "Selecionado: " + localidade.getNome_localidade(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ClientesAdapter.class);
        intent.putExtra("localidade_id", localidade.getId());
        startActivity(intent);
        //intent.putExtra("localidade", localidade);
        //startActivity(intent);

    }
}