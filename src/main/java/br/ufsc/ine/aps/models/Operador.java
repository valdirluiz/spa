package br.ufsc.ine.aps.models;

public class Operador extends Pessoa {

    private Gerente gerente;

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Gerente getGerente() {
        return gerente;
    }
}
