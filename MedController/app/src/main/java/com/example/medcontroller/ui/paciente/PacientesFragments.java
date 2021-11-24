package com.example.medcontroller.ui.paciente;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medcontroller.R;
import com.example.medcontroller.adapter.AdapterPacientes;
import com.example.medcontroller.config.ConfigFirebase;
import com.example.medcontroller.model.Paciente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PacientesFragments extends Fragment {

    private RecyclerView pacientesView;
    private AdapterPacientes adapterPacientes;
    private List<Paciente> pacientes = new ArrayList<>();

    private ValueEventListener valueEventListener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pacientes, container, false);

        pacientesView = view.findViewById(R.id.pacientesView);

        adapterPacientes = new AdapterPacientes(pacientes, view.getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        pacientesView.setLayoutManager(layoutManager);
        pacientesView.setHasFixedSize(true);
        pacientesView.setAdapter(adapterPacientes);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        recuperarPacientes();
    }

    public void recuperarPacientes()
    {
        DatabaseReference reference = ConfigFirebase.getReference();
        DatabaseReference paciRef = reference.child("Pacientes");
        valueEventListener = paciRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pacientes.clear();
                for (DataSnapshot dados : snapshot.getChildren())
                {
                    Paciente paci = dados.getValue(Paciente.class);
                    paci.setId(dados.getKey());
                    pacientes.add(paci);
                }
                adapterPacientes.notifyDataSetChanged();
                Log.d("STRING", String.valueOf(pacientes.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}