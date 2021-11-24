package com.example.medcontroller.ui.horarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medcontroller.R;
import com.example.medcontroller.config.ConfigFirebase;
import com.example.medcontroller.model.Paciente;
import com.example.medcontroller.model.Prescricao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CadastroHorariosActivity extends AppCompatActivity {

    private EditText dose, horario, dias;
    private Spinner paciente, medicamento;
    private FloatingActionButton btPrescricao;
    DatabaseReference firebase = ConfigFirebase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_horarios);

        paciente = findViewById(R.id.pacientePRE);
        medicamento = findViewById(R.id.medicamentoPRE);
        dose = findViewById(R.id.dosePRE);
        horario = findViewById(R.id.horarioPRE);
        dias = findViewById(R.id.diasPre);
        btPrescricao = findViewById(R.id.btPrescricao);

        RecuperarPacientes();
        RecuperarMedicamentos();

        btPrescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarHorario();
            }
        });
    }

    private void SalvarHorario()
    {
        if (ValidarCampos())
        {
            Prescricao prescricao = new Prescricao();

            prescricao.setPaciente(paciente.getSelectedItem().toString());
            prescricao.setMedicamento(medicamento.getSelectedItem().toString());
            prescricao.setDose(dose.getText().toString());
            prescricao.setDias(dias.getText().toString());
            prescricao.setHorario(horario.getText().toString());

            DatabaseReference firebase = ConfigFirebase.getReference();
            firebase.child("Horarios").push().setValue(prescricao) ;

            Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    public Boolean ValidarCampos()
    {
        String doseDigitado = dose.getText().toString();
        String horarioDigitada = horario.getText().toString();
        String diasDigitada = dias.getText().toString();

        if (!diasDigitada.isEmpty())
        {
            if (!horarioDigitada.isEmpty())
            {
                if (!doseDigitado.isEmpty())
                {
                    return true;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Preencha a dose da prescrição!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Preencha o horario da prescrição!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Preencha o numero de prescrição!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void RecuperarPacientes() {
        firebase.child("Pacientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String nomePac = areaSnapshot.child("nome").getValue(String.class);

                    final String[] pacientes = {nomePac};
                    ArrayAdapter<String> pacienteAdapter = new ArrayAdapter<String>(CadastroHorariosActivity.this, android.R.layout.simple_spinner_item, pacientes);
                    pacienteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    paciente.setAdapter(pacienteAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void RecuperarMedicamentos() {
        firebase.child("Medicamentos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String nomeMed = areaSnapshot.child("nome").getValue(String.class);

                    final String[] medicamentos = {nomeMed};
                    ArrayAdapter<String> medicamentoAdapter = new ArrayAdapter<String>(CadastroHorariosActivity.this, android.R.layout.simple_spinner_item, medicamentos);
                    medicamentoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    medicamento.setAdapter(medicamentoAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}