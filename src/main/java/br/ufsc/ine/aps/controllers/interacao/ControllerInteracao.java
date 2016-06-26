package br.ufsc.ine.aps.controllers.interacao;


import br.ufsc.ine.aps.controllers.login.Autenticador;
import br.ufsc.ine.aps.controllers.notificacao.ControllerNotificacao;
import br.ufsc.ine.aps.enuns.TipoInteracao;
import br.ufsc.ine.aps.models.Autenticavel;
import br.ufsc.ine.aps.models.Interacao;
import br.ufsc.ine.aps.models.Protocolo;

import java.util.List;

public class ControllerInteracao {

    private static ControllerInteracao ourInstance = new ControllerInteracao();

    public static ControllerInteracao getInstance() {
        return ourInstance;
    }

    private Autenticador autenticador;
    private DaoInteracao daoInteracao;
    private ControllerNotificacao controllerNotificacao;

    private ControllerInteracao() {
        this.autenticador = Autenticador.getInstance();
        this.daoInteracao = DaoInteracao.getInstance();
        this.controllerNotificacao = ControllerNotificacao.getInstance();
    }

    public void addInteracao(Protocolo protocolo, TipoInteracao tipoInteracao){
        Autenticavel usuarioLogado = autenticador.getUsuarioLogado();
        String mensagem = this.geraMensagem(usuarioLogado, tipoInteracao, protocolo);
        Interacao interacao = this.geraInteracao(usuarioLogado, mensagem, tipoInteracao);
        interacao = this.daoInteracao.save(interacao);
        List<Autenticavel> usuariosNotificados = this.controllerNotificacao.defineUsuarios(protocolo, usuarioLogado);
        for(Autenticavel usuario : usuariosNotificados){
            this.controllerNotificacao.geraNotificacao(interacao, usuario);
        }
    }

    private Interacao geraInteracao(Autenticavel usuarioLogado, String mensagem, TipoInteracao tipo) {
        Interacao interacao  = new Interacao();
        interacao.setUsuario(usuarioLogado);
        interacao.setMensagem(mensagem);
        interacao.setTipo(tipo);
        return interacao;
    }

    private String geraMensagem(Autenticavel usuarioLogado, TipoInteracao tipoInteracao, Protocolo protocolo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Usuário:");
        builder.append(" ");
        builder.append(usuarioLogado.getNome());
        builder.append(" ");
        builder.append(tipoInteracao.getDescricao());
        builder.append(" o protocolo ");
        builder.append(protocolo.getId());
        return builder.toString();
    }


}
