package br.ufsc.ine.aps.controllers.usuario;

import br.ufsc.ine.aps.models.Autenticavel;


public class UsuarioController {

    private DaoUsuario daoUsuario;


    public UsuarioController(){
        this.daoUsuario = new DaoUsuario();
    }

    public Autenticavel findUsuario(String cpf){
        return this.daoUsuario.findByCPF(cpf);
    }
}
