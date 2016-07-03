package br.ufsc.ine.aps.models;


import br.ufsc.ine.aps.enuns.TipoUsuario;

public class Gerente extends Pessoa{

    public Gerente() {
        super.setTipoUsuario(TipoUsuario.GERENTE);
    }
}
