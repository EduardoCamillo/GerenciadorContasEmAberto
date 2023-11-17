package com.example.loginproject;

import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginproject.database.ClienteDAO;
import com.example.loginproject.database.DBHelper;
import com.example.loginproject.database.LocalidadeDAO;
import com.example.loginproject.database.model.Cliente;
import com.example.loginproject.database.model.Contas;
import com.example.loginproject.database.model.Localidade;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements AdapterClickListener {

    private final int TYPE_CLIENTE = 0;
    private final int TYPE_LOCALIDADE = 1;
    private final int TYPE_CONTAS = 2;

    private AdapterLocalidade adapterLocalidade;
    private AdapterLocalidade adapterLocalidade2;
    private AdapterConta adapterConta;
    private ClientesAdapter adapterCliente;
    private FrameLayout fragment_container;
    public Toolbar toolbar;
    private SwipeableRecyclerView rvLocalidades;
    private SQLiteDatabase db;
    private LocalidadeDAO localidadeDAO;
    private ClienteDAO clienteDAO1;
    private DBHelper dbHelper;
    private ClientesFragments clientesFragments1;
    private LinkedList<Fragment> fragments;
    private String currentLocalidade;

    ActivityResultLauncher<Intent> someActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientesFragments1 = new ClientesFragments();
        db = openOrCreateDatabase("DB_APP",MODE_PRIVATE, null);
        localidadeDAO = new LocalidadeDAO(this);

        fragments = new LinkedList<>();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Localidades");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);

        fragment_container = findViewById(R.id.fragment_container);


        //Configure result launcher to update media list when add new Localidade
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

    private void configMediaList() {
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

        fragment_container.addView(rv);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

        if(fragment instanceof ClientesFragments){
            updateToolbar(TYPE_CLIENTE);
        }
        if(fragment instanceof ContasFragment){
            updateToolbar(TYPE_CONTAS);
        }

    }

    public void updateToolbar(int type){

        if(type == TYPE_CLIENTE){
            toolbar.setTitle("Clientes" + (currentLocalidade != null ? " da " + currentLocalidade : ""));
            return;
        }

        if(type == TYPE_LOCALIDADE){
            toolbar.setTitle("Localidades");
            return;
        }
        if(type == TYPE_CONTAS){
            toolbar.setTitle("Contas");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idMenu = item.getItemId();

        if (idMenu == R.id.menu_add) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

            if (currentFragment instanceof ClientesFragments) {
                ClientesFragments clientesFragments = (ClientesFragments) currentFragment;

                Intent intent = new Intent(this, FormClienteActivity.class);
                intent.putExtra("localidadeId", clientesFragments.getLocalidadeId());
                someActivityResultLauncher.launch(intent);
            }else if(currentFragment instanceof ContasFragment){
                ContasFragment contasFragment = (ContasFragment) currentFragment;

                Intent intent = new Intent(this, FormContaActivity.class);
                intent.putExtra("clienteId",contasFragment.getClienteId());
                someActivityResultLauncher.launch(intent);
            }
            else {
                someActivityResultLauncher.launch(new Intent(this, FormLocalidadeActivity.class));
            }

            return true;

        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(fragments.size() > 0){
            Fragment fragment  = fragments.peekLast();

            if(fragment instanceof ClientesFragments){
                fragments.removeLast();
                updateToolbar(TYPE_LOCALIDADE);
            }

            if(fragment instanceof ContasFragment){
                fragments.removeLast();
                updateToolbar(TYPE_CLIENTE);
            }
        }
    }

    @Override
    public void onItemClicked(Object object) {
        if(object instanceof Localidade){
            Localidade localidade = (Localidade) object;

            //Intent intent = new Intent(this, FormLocalidadeActivity.class);

            Toast.makeText(getApplicationContext(), "Selecionado: " + localidade.getNome_localidade(),Toast.LENGTH_SHORT).show();
            // Cria um objeto do fragmento para exibir os clientes da localidade
            ClientesFragments clientesFragments  = new ClientesFragments();
            //passando o id da localidade como argumento
            Bundle args = new Bundle();
            args.putInt("localidade_id", localidade.getId());
            clientesFragments.setArguments(args);
            clientesFragments.setAdapterClickListener(this);

            args.putString("nome_localidade", localidade.getNome_localidade());
            currentLocalidade = localidade.getNome_localidade();



            //substituindo o fragmento atual pelo fragmento de clientes
            addFragment(clientesFragments);

            if(object instanceof Cliente){
                // Executa ação do click de cliente
                Cliente cliente = (Cliente) object;

                //Intent intent = new Intent(this, FormLocalidadeActivity.class);

                Toast.makeText(getApplicationContext(), "Selecionado: " + cliente.getNome_cliente(),Toast.LENGTH_SHORT).show();
                // Cria um objeto do fragmento para exibir as contas do cliente
                ContasFragment contasFragment  = new ContasFragment();
                //passando o id da localidade como argumento
                 args = new Bundle();
                args.putInt("cliente_id", cliente.getId());
                contasFragment.setArguments(args);
                contasFragment.setAdapterClickListener(this);



                //substituindo o fragmento atual pelo fragmento de contas
                addFragment(contasFragment);

                //intent.putExtra("localidade", localidade);
                //startActivity(intent);
            }
            //intent.putExtra("localidade", localidade);
            //startActivity(intent);
            return;
        }

        if(object instanceof Cliente){
            // Executa ação do click de cliente
            Cliente cliente = (Cliente) object;

            //Intent intent = new Intent(this, FormLocalidadeActivity.class);

            Toast.makeText(getApplicationContext(), "Selecionado: " + cliente.getNome_cliente(),Toast.LENGTH_SHORT).show();
            // Cria um objeto do fragmento para exibir as contas do cliente
            ContasFragment contasFragment  = new ContasFragment();
            //passando o id da localidade como argumento
            Bundle args = new Bundle();
            args.putInt("cliente_id", cliente.getId());
            contasFragment.setArguments(args);
            contasFragment.setAdapterClickListener(this);



            //substituindo o fragmento atual pelo fragmento de contas
            addFragment(contasFragment);

            //intent.putExtra("localidade", localidade);
            //startActivity(intent);
        }

    }

    public void updateList() {
        adapterLocalidade2.setLocalidadeList(localidadeDAO.getListLocalidade());
        adapterLocalidade2.notifyDataSetChanged();
    }
}