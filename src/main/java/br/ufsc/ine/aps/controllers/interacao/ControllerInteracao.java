package br.ufsc.ine.aps.controllers.interacao;


import br.ufsc.ine.aps.controllers.login.Autenticador;
import br.ufsc.ine.aps.enuns.TipoInteracao;
import br.ufsc.ine.aps.models.Protocolo;

public class ControllerInteracao {
    private static ControllerInteracao ourInstance = new ControllerInteracao();

    public static ControllerInteracao getInstance() {
        return ourInstance;
    }


    private Autenticador autenticador;

    private ControllerInteracao() {
        this.autenticador = Autenticador.getInstance();
    }

    public void addInteracao(Protocolo protocolo, TipoInteracao tipoInteracao){
        
    }
}
