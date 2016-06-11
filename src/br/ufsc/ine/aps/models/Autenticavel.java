package br.ufsc.ine.aps.models;


import java.util.List;

public interface Autenticavel {

    List<Notificacao> getNotificacoes();
    Integer getId();
    String getNome();

}
