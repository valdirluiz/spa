package br.ufsc.ine.aps.controllers.notificacao;

import br.ufsc.ine.aps.models.Autenticavel;
import br.ufsc.ine.aps.models.Interacao;
import br.ufsc.ine.aps.models.Protocolo;

import java.util.List;

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
        //// TODO: 25/06/2016 implementar
        return null;
    }

    public void geraNotificacao(Interacao interacao, Autenticavel usuarioLogado){
        //:TODO implementar
    }

}
