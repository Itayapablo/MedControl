package com.example.medcontroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medcontroller.R;
import com.example.medcontroller.model.Medicamento;
import com.example.medcontroller.model.Paciente;

import java.util.List;

public class AdapterMedicamentos extends RecyclerView.Adapter<AdapterMedicamentos.MyViewHolder>{

    List<Medicamento> medicamentos;
    Context context;

    public AdapterMedicamentos(List<Medicamento> medicamentos, Context context) {
        this.medicamentos = medicamentos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMedicamentos.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_medicamentos, parent, false);
        return new AdapterMedicamentos.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMedicamentos.MyViewHolder holder, int position) {
        Medicamento medicamento = medicamentos.get(position);

        holder.nome.setText("Nome:" +medicamento.getNome());
        holder.tipo.setText("Tipo:" +medicamento.getTipo());
        holder.tamanho.setText("Tamanho:" +String.valueOf(medicamento.getTamanho()));
        holder.validade.setText("Validade:" +medicamento.getValidade());
        holder.categoria.setText("Categoria:" +medicamento.getCategoria());
    }

    @Override
    public int getItemCount() {
        return medicamentos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome, tipo, tamanho, validade, categoria;

        public MyViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textAdapterNomeMed);
            tipo = itemView.findViewById(R.id.textAdapterTipo);
            tamanho = itemView.findViewById(R.id.textAdapterTipo);
            validade = itemView.findViewById(R.id.textAdapterValidade);
            categoria = itemView.findViewById(R.id.textAdapterCategoria);
        }
    }
}
