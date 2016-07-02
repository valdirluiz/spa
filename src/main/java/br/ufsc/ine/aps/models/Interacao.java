package br.ufsc.ine.aps.models;

import br.ufsc.ine.aps.enuns.TipoInteracao;

import java.util.Date;


public class Interacao {

    private Integer id;
    private Date data = new Date();
    private String mensagem;
    private Autenticavel usuario;
    private TipoInteracao tipo;
    private Protocolo protocolo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Autenticavel getUsuario() {
        return usuario;
    }

    public void setUsuario(Autenticavel usuario) {
        this.usuario = usuario;
    }

    public TipoInteracao getTipo() {
        return tipo;
    }

    public void setTipo(TipoInteracao tipo) {
        this.tipo = tipo;
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }


}
