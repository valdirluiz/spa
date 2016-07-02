package br.ufsc.ine.aps.models;


import java.text.SimpleDateFormat;

public class Notificacao {

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

    public String getStatus(){
        if(visualizado){
            return "Visualizada";
        } else{
            return "Não visualizada";
        }
    }

    public String getDia(){
        return new SimpleDateFormat("dd-MM-yyyy").format(this.interacao.getData());
    }

    public String getHora(){
        return new SimpleDateFormat("hh:mm:ss").format(this.interacao.getData());
    }

    public String getTipo(){
        return this.interacao.getTipo().getDescricao();
    }
}
