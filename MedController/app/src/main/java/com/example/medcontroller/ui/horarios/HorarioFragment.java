package com.example.medcontroller.ui.horarios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medcontroller.R;
import com.example.medcontroller.adapter.AdapterHorario;
import com.example.medcontroller.adapter.AdapterPacientes;
import com.example.medcontroller.config.ConfigFirebase;
import com.example.medcontroller.model.Paciente;
import com.example.medcontroller.model.Prescricao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HorarioFragment extends Fragment {

    private RecyclerView horarioView;
    private AdapterHorario adapterHorario;
    private List<Prescricao> horarios = new ArrayList<>();

    private ValueEventListener valueEventListener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horario, container, false);

        horarioView = view.findViewById(R.id.horarioView);

        adapterHorario = new AdapterHorario(horarios, view.getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        horarioView.setLayoutManager(layoutManager);
        horarioView.setHasFixedSize(true);
        horarioView.setAdapter(adapterHorario);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        recuperarHorarios();
    }

    public void recuperarHorarios()
    {
        DatabaseReference reference = ConfigFirebase.getReference();
        DatabaseReference paciRef = reference.child("Horarios");
        valueEventListener = paciRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                horarios.clear();
                for (DataSnapshot dados : snapshot.getChildren())
                {
                    Prescricao pres = dados.getValue(Prescricao.class);
                    pres.setId(dados.getKey());
                    horarios.add(pres);
                }
                adapterHorario.notifyDataSetChanged();
                Log.d("STRING", String.valueOf(horarios.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}