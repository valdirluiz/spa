package br.ufsc.ine.aps.controllers.notificacao;

import br.ufsc.ine.aps.models.Autenticavel;
import br.ufsc.ine.aps.models.Interacao;
import br.ufsc.ine.aps.models.Notificaao;
import br.ufsc.ine.aps.models.Protocolo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerNotificacao {


    private DaoNotificacao daoNotificacao;

    private static ControllerNotificacao ourInstance = new ControllerNotificacao();

    public static ControllerNotificacao getInstance() {
        return ourInstance;
    }

    private ControllerNotificacao() {
        this.daoNotificacao = DaoNotificacao.getInstance();
    }

    public List<Autenticavel> defineUsuarios(Protocolo protocolo, Autenticavel usuarioLogado){
        List<Autenticavel> usuarios = new ArrayList<>();
        usuarios.add(protocolo.getOperador());
        usuarios.add(protocolo.getCliente());
        usuarios.add(protocolo.getAtendente());
        return usuarios.stream()
                        .filter(u -> !u.getId().equals(usuarioLogado.getId()))
                        .collect(Collectors.toList());

    }

    public void geraNotificacao(Interacao interacao, Autenticavel usuarioNotificado){
        Notificaao notificaao = new Notificaao();
        notificaao.setUsuario(usuarioNotificado);
        notificaao.setVisualizado(false);
        notificaao.setInteracao(interacao);
        this.daoNotificacao.save(notificaao);
    }

}
