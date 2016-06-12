package br.ufsc.ine.aps.controllers.login;


import br.ufsc.ine.aps.controllers.usuario.UsuarioController;
import br.ufsc.ine.aps.models.Autenticavel;
import br.ufsc.ine.aps.models.Pessoa;

import java.util.Optional;

public class Autenticador {

    private UsuarioController usuarioController;
    private Autenticavel usuarioLogado;

    private static Autenticador ourInstance = new Autenticador();

    public static Autenticador getInstance() {
        return ourInstance;
    }


    private Autenticador(){
        this.usuarioController = new UsuarioController();
    }

    public Optional<String> efetuarLogin(String cpf, String senha){
        Autenticavel usuario = usuarioController.findUsuario(cpf);
        Optional<String> notificacao = Optional.empty();
        if(usuario!=null){
            if(!this.validaSenha(senha, usuario)){
                notificacao = Optional.of("Senha invalida");
            } else{
                this.autenticaUsuario(usuario);
            }
        } else{
            notificacao = Optional.of("Usuário inválido");
        }
        return notificacao;
    }

    private void autenticaUsuario(Autenticavel usuario) {
        this.usuarioLogado = usuario;
    }

    private boolean validaSenha(String senha, Autenticavel usuario){
        return senha.equals(usuario.getSenha());
    }

    public Autenticavel getUsuarioLogado() {
        return usuarioLogado;
    }
}
