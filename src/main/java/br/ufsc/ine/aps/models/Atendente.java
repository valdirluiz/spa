package br.ufsc.ine.aps.models;


import br.ufsc.ine.aps.enuns.TipoUsuario;

public class Atendente extends Pessoa {

    public Atendente(){
        super.setTipoUsuario(TipoUsuario.ATENDENTE);
    }
}
