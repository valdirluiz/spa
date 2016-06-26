package br.ufsc.ine.aps.controllers.interacao;

import br.ufsc.ine.aps.models.Interacao;

/**
 * Created by Valdir Luiz on 25/06/2016.
 */
public class DaoInteracao {
    private static DaoInteracao ourInstance = new DaoInteracao();

    public static DaoInteracao getInstance() {
        return ourInstance;
    }

    private DaoInteracao() {
    }
    
    public Interacao save(Interacao interacao){
        //// TODO: 25/06/2016 implementar
        return interacao;
    }
}
