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
import com.example.medcontroller.model.Prescricao;

import java.util.List;

public class AdapterHorario extends RecyclerView.Adapter<AdapterHorario.MyViewHolder>{

    List<Prescricao> horarios;
    Context context;

    public AdapterHorario(List<Prescricao> horarios, Context context) {
        this.horarios = horarios;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterHorario.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_horario, parent, false);
        return new AdapterHorario.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHorario.MyViewHolder holder, int position) {
        Prescricao prescricao = horarios.get(position);

        holder.paciente.setText("Paciente:" +prescricao.getPaciente());
        holder.medicamento.setText("Remédio:" +prescricao.getMedicamento());
        holder.dose.setText("Dose:" +prescricao.getDose());
        holder.horario.setText("Horario:" +prescricao.getHorario());
        holder.dias.setText("Dias:" +prescricao.getDias());
    }

    @Override
    public int getItemCount() {
        return horarios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView paciente, medicamento, dose, horario, dias;

        public MyViewHolder(View itemView) {
            super(itemView);
            paciente = itemView.findViewById(R.id.textAdapterPaciente);
            medicamento = itemView.findViewById(R.id.textAdapterMedicamento);
            dose = itemView.findViewById(R.id.textAdapterDose);
            horario = itemView.findViewById(R.id.textAdapterHorario);
            dias = itemView.findViewById(R.id.textAdapterDias);
        }
    }
}
