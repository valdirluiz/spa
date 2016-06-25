package br.ufsc.ine.aps.models;

/**
 * Created by Valdir Luiz on 21/06/2016.
 */
public class Operador extends Pessoa {

    private Gerente gerente;

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Gerente getGerente() {
        return gerente;
    }
}
