package br.ufsc.ine.aps.controllers.login;


import br.com.caelum.stella.validation.CPFValidator;
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
        Optional<String> notificacao = this.validaDados(cpf, senha);
        if(!notificacao.isPresent()){
            Autenticavel usuario = usuarioController.findUsuario(cpf);
            if(usuario!=null){
                if(!this.validaSenha(senha, usuario)){
                    notificacao = Optional.of("Senha invalida");
                } else{
                    this.autenticaUsuario(usuario);
                }
            } else{
                notificacao = Optional.of("Usuário inválido");
            }
        }
        return notificacao;
    }

    private Optional<String> validaDados(String cpf, String senha) {
        CPFValidator cpfValidator = new CPFValidator();
        Optional<String> notificacao = Optional.empty();
        if(cpf == null || cpf.isEmpty()){
            notificacao = Optional.of("CPF não preenchido!");
        } else if(!cpfValidator.invalidMessagesFor(cpf).isEmpty()){
            notificacao = Optional.of("CPF inválido!");
        } else if(senha == null || senha.isEmpty()) {
            notificacao = Optional.of("Senha não preenchida!");
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
