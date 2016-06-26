package br.ufsc.ine.aps.models;

public class Notificacoes {

    private Integer id;
    private boolean visualizado;
    private Autenticavel usuario;
    private Interacao interacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isVisualizado() {
        return visualizado;
    }

    public void setVisualizado(boolean visualizado) {
        this.visualizado = visualizado;
    }

    public Autenticavel getUsuario() {
        return usuario;
    }

    public void setUsuario(Autenticavel usuario) {
        this.usuario = usuario;
    }

    public Interacao getInteracao() {
        return interacao;
    }

    public void setInteracao(Interacao interacao) {
        this.interacao = interacao;
    }
}
