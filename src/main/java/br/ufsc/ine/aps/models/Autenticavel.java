package br.ufsc.ine.aps.models;


import br.ufsc.ine.aps.enuns.TipoUsuario;

import java.util.List;

public interface Autenticavel {

    List<Notificacao> getNotificacoes();
    Integer getId();
    String getNome();
    TipoUsuario getTipoUsuario();
    String getSenha();

    void setSenha(String senha);
    void setId(Integer id);
    void setNome(String nome);
    void setTipoUsuario(TipoUsuario tipoUsuario);
}
