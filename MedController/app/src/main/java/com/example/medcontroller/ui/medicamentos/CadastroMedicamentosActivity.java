package com.example.medcontroller.ui.medicamentos;

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

public class CadastroMedicamentosActivity extends AppCompatActivity {

    private EditText nome, tipo, tamanho, categoria, validade;
    private FloatingActionButton btMed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_medicamentos);

        nome = findViewById(R.id.nomeMed);
        tipo = findViewById(R.id.tipoMed);
        tamanho = findViewById(R.id.tamanhoMed);
        categoria = findViewById(R.id.categMed);
        validade = findViewById(R.id.validadeMed);
        btMed = findViewById(R.id.btMedicamento);

        btMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarMedicamentos();
            }
        });
    }

    private void SalvarMedicamentos()
    {
        if (ValidarCampos())
        {
            Medicamento medicamento = new Medicamento();
            int tamanhoDigitado = Integer.parseInt(tamanho.getText().toString());

            medicamento.setNome(nome.getText().toString());
            medicamento.setTipo(tipo.getText().toString());
            medicamento.setTamanho(tamanhoDigitado);
            medicamento.setCategoria(categoria.getText().toString());
            medicamento.setValidade(validade.getText().toString());

            DatabaseReference firebase = ConfigFirebase.getReference();
            firebase.child("Medicamentos").push().setValue(medicamento) ;

            Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    public Boolean ValidarCampos()
    {
        String nomeDigitado = nome.getText().toString();
        String tipoDigitada = tipo.getText().toString();
        String tamanhoDigitada = tamanho.getText().toString();
        String categoriaDigitada = categoria.getText().toString();
        String validadeDigitade = validade.getText().toString();

        if (!nomeDigitado.isEmpty())
        {
            if (!tipoDigitada.isEmpty())
            {
                if (!tamanhoDigitada.isEmpty())
                {
                    if (!categoriaDigitada.isEmpty())
                    {
                        if(!validadeDigitade.isEmpty())
                        {
                            return true;
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Preencha a validade do Medicamento!", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Preencha a categoria do Medicamento!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Preencha o tamanho do Medicamento!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Preencha o tipo do Medicamento!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Preencha o nome do Medicamento!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}