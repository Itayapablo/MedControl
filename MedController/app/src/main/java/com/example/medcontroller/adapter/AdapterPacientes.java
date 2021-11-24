package com.example.medcontroller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medcontroller.R;
import com.example.medcontroller.model.Paciente;

import java.util.List;

public class AdapterPacientes extends RecyclerView.Adapter<AdapterPacientes.MyViewHolder>{

    List<Paciente> pacientes;
    Context context;

    public AdapterPacientes(List<Paciente> pacientes, Context context) {
        this.pacientes = pacientes;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPacientes.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_paciente, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPacientes.MyViewHolder holder, int position) {
        Paciente paciente = pacientes.get(position);

        holder.nome.setText("Nome:" + paciente.getNome());
        holder.cpf.setText("CPF:" + String.valueOf(paciente.getCpf()));
        holder.rg.setText("Rg:" + String.valueOf(paciente.getRg()));
        holder.data.setText( "Aniversário:" + paciente.getNascimento());
        holder.cidade.setText( "Cidade:" + paciente.getCidade());
    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome, cpf, rg, data, cidade;

        public MyViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textAdapterNomePac);
            cpf = itemView.findViewById(R.id.textAdapterCpf);
            rg = itemView.findViewById(R.id.textAdapterRg);
            data = itemView.findViewById(R.id.textAdapterData);
            cidade = itemView.findViewById(R.id.textAdapterCategoria2);
        }
    }
}
