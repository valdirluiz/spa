package br.ufsc.ine.aps.controllers.usuario;

import br.ufsc.ine.aps.models.Autenticavel;


public class ControllerUsuario {

    private DaoUsuario daoUsuario;


    public ControllerUsuario(){
        this.daoUsuario = new DaoUsuario();
    }

    public Autenticavel findUsuarioByCpfAndPerfil(String cpf, String perfil){
        return this.daoUsuario.findByCPF(cpf, perfil.equals("Cliente"));
    }
}
