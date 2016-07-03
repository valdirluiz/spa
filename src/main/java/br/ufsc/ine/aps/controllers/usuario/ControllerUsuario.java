package br.ufsc.ine.aps.controllers.usuario;

import br.ufsc.ine.aps.models.Autenticavel;
import br.ufsc.ine.aps.models.Pessoa;


public class ControllerUsuario {

    private DaoUsuario daoUsuario;
    private static ControllerUsuario ourInstance = new ControllerUsuario();

    public static ControllerUsuario getInstance() {
        return ourInstance;
    }

    public ControllerUsuario(){
        this.daoUsuario = new DaoUsuario();
    }

    public Autenticavel findUsuarioByCpfAndPerfil(String cpf, String perfil){
        return this.daoUsuario.findByCPFAndFlCliente(cpf, perfil.equals("Cliente"));
    }

    public Pessoa findUsuarioById(Integer id){
        return daoUsuario.findPessoaById(id);
    }
}
