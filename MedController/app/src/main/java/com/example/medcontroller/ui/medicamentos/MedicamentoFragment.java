package com.example.medcontroller.ui.medicamentos;

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
import com.example.medcontroller.adapter.AdapterMedicamentos;
import com.example.medcontroller.adapter.AdapterPacientes;
import com.example.medcontroller.config.ConfigFirebase;
import com.example.medcontroller.model.Medicamento;
import com.example.medcontroller.model.Paciente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedicamentoFragment extends Fragment {

    private RecyclerView medicamentosView;
    private AdapterMedicamentos adapterMedicamentos;
    private List<Medicamento> medicamentos = new ArrayList<>();

    private ValueEventListener valueEventListener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_medicamentos, container, false);

        medicamentosView = view.findViewById(R.id.mediView);

        adapterMedicamentos = new AdapterMedicamentos(medicamentos, view.getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        medicamentosView.setLayoutManager(layoutManager);
        medicamentosView.setHasFixedSize(true);
        medicamentosView.setAdapter(adapterMedicamentos);

        return view;
    }

    public void onStart() {
        super.onStart();
        recuperarMedicamentos();
    }

    public void recuperarMedicamentos()
    {
        DatabaseReference reference = ConfigFirebase.getReference();
        DatabaseReference paciRef = reference.child("Medicamentos");
        valueEventListener = paciRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                medicamentos.clear();
                for (DataSnapshot dados : snapshot.getChildren())
                {
                    Medicamento medi = dados.getValue(Medicamento.class);
                    medi.setId(dados.getKey());
                    medicamentos.add(medi);
                }
                adapterMedicamentos.notifyDataSetChanged();
                Log.d("STRING", String.valueOf(medicamentos.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}