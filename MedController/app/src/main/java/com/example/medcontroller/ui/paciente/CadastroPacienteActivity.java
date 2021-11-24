package com.example.medcontroller.ui.paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medcontroller.R;
import com.example.medcontroller.config.ConfigFirebase;
import com.example.medcontroller.model.Medicamento;
import com.example.medcontroller.model.Paciente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

public class CadastroPacienteActivity extends AppCompatActivity {

    private EditText nome, cpf, rg, nascimento, cidade;
    private FloatingActionButton btPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_paciente);

        nome = findViewById(R.id.nomePAC);
        cpf = findViewById(R.id.cpfPAC);
        rg = findViewById(R.id.rgPAC);
        nascimento = findViewById(R.id.dataPAC);
        cidade = findViewById(R.id.cidadePAC);
        btPaciente = findViewById(R.id.btPaciente);

        btPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarPaciente();
            }
        });
    }

    private void SalvarPaciente()
    {
        if (ValidarCampos())
        {
            Paciente paciente = new Paciente();
            int cpfDigitado = Integer.parseInt(cpf.getText().toString());
            int rgDigitado = Integer.parseInt(rg.getText().toString());

            paciente.setNome(nome.getText().toString());
            paciente.setCpf(cpfDigitado);
            paciente.setRg(rgDigitado);
            paciente.setNascimento(nascimento.getText().toString());
            paciente.setCidade(cidade.getText().toString());

            DatabaseReference firebase = ConfigFirebase.getReference();
            firebase.child("Pacientes").push().setValue(paciente) ;

            Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    public Boolean ValidarCampos()
    {
        String nomeDigitado = nome.getText().toString();
        String cpfDigitada = cpf.getText().toString();
        String rgDigitada = rg.getText().toString();
        String dataDigitada = nascimento.getText().toString();
        String cidadeDigitade = cidade.getText().toString();

        if (!nomeDigitado.isEmpty())
        {
            if (!dataDigitada.isEmpty())
            {
                if (!cpfDigitada.isEmpty())
                {
                    if (!cidadeDigitade.isEmpty())
                    {
                        if(!rgDigitada.isEmpty())
                        {
                            return true;
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Preencha o rg do paciente!", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Preencha a cidade do paciente!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Preencha o cpf do paciente!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Preencha a data de nascimento do paciente!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Preencha o nome do paciente!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}