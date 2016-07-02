package br.ufsc.ine.aps.controllers.notificacao;

import br.ufsc.ine.aps.models.Autenticavel;
import br.ufsc.ine.aps.models.Interacao;
import br.ufsc.ine.aps.models.Notificacao;
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
        if(protocolo.getResponsavel()!=null){
            usuarios.add(protocolo.getResponsavel());
        }
        usuarios.add(protocolo.getCliente());
        return usuarios.stream()
                        .filter(u -> !u.getId().equals(usuarioLogado.getId()))
                        .collect(Collectors.toList());

    }

    public void geraNotificacao(Interacao interacao, Autenticavel usuarioNotificado){
        try {
            Notificacao notificaao = new Notificacao();
            notificaao.setUsuario(usuarioNotificado);
            notificaao.setVisualizado(false);
            notificaao.setInteracao(interacao);
            this.daoNotificacao.save(notificaao);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Notificacao> findByUsuario(Autenticavel usuario){
        return this.daoNotificacao.findByUsuario(usuario.getId());
    }

}
