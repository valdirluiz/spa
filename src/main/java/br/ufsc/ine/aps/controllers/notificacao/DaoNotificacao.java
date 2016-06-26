package br.ufsc.ine.aps.controllers.notificacao;

import br.ufsc.ine.aps.models.Notificaao;

/**
 * Created by Valdir Luiz on 26/06/2016.
 */
public class DaoNotificacao {
    private static DaoNotificacao ourInstance = new DaoNotificacao();

    public static DaoNotificacao getInstance() {
        return ourInstance;
    }

    private DaoNotificacao() {
    }

    public void save(Notificaao notificaao){
        //TODO: implementar
    }
}
