package com.example.medcontroller.model;

public class Prescricao {
    public String id;
    public String paciente;
    public String medicamento;
    public String dose;
    public String horario;
    public String dias;

    public Prescricao() {

    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }
}
