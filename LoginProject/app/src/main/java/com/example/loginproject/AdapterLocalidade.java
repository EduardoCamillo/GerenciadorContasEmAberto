package com.example.loginproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterLocalidade extends RecyclerView.Adapter<AdapterLocalidade.MyViewHolder> {

    private List<Localidade> localidadeList;
    private OnClick onClick;


    public AdapterLocalidade(List<Localidade> localidadeList, OnClick onClick) {
        this.localidadeList = localidadeList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //passa o layout que será utilizado para o adapter
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_localidades,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //passa os itens (informações) que serão listadas

        Localidade localidade = localidadeList.get(position);

        holder.nome_localidade.setText(localidade.getNome_localidade());

        holder.itemView.setOnClickListener(v -> {onClick.OnClickListener(localidade);});
    }

    @Override
    public int getItemCount() {
        //retornar a quantidade de localidades que temos na lista
        return localidadeList.size();
    }

    public void notifyItemRangeRemoved(int position) {
    }

    public interface OnClick{
        void OnClickListener(Localidade localidade);
    }



    static class MyViewHolder extends RecyclerView.ViewHolder{
        //declarar os componentes que irão conter no layout (neste caso é apenas um TextView)

        TextView nome_localidade;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                nome_localidade = itemView.findViewById(R.id.nome_localidade);
            }
        }
}