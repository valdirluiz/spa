package br.ufsc.ine.aps.controllers.notificacao;

import br.ufsc.ine.aps.models.Autenticavel;
import br.ufsc.ine.aps.models.Interacao;
import br.ufsc.ine.aps.models.Protocolo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Valdir Luiz on 25/06/2016.
 */
public class ControllerNotificacao {
    private static ControllerNotificacao ourInstance = new ControllerNotificacao();

    public static ControllerNotificacao getInstance() {
        return ourInstance;
    }

    private ControllerNotificacao() {
    }

    public List<Autenticavel> defineUsuarios(Protocolo protocolo, Autenticavel usuarioLogado){
        List<Autenticavel> usuarios = new ArrayList<>();
        usuarios.add(protocolo.getOperador());
        usuarios.add(protocolo.getCliente());
        usuarios.add(protocolo.getAtendente());
        return usuarios.stream()
                        .filter(u -> u.getId() != usuarioLogado.getId())
                        .collect(Collectors.toList());

    }

    public void geraNotificacao(Interacao interacao, Autenticavel usuarioLogado){
        //:TODO implementar
    }

}
