package br.ufsc.ine.aps.models;


import br.ufsc.ine.aps.enuns.Area;
import br.ufsc.ine.aps.enuns.Categoria;
import br.ufsc.ine.aps.enuns.Status;

import java.util.Calendar;
import java.util.Date;

public class Protocolo {

    private Integer id;
    private Calendar dataCriacao;
    private Calendar dataFimExecucao;
    private Calendar dataInicioExecucao;
    private String feedback;
    private String identificador;
    private String mensagemLivre;
    private String resposta;
    private Status status;
    private Categoria categoria;
    private Area area;
    private Cliente cliente;
    private Atendente atendente;
    private Pessoa responsavel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Calendar getDataFimExecucao() {
        return dataFimExecucao;
    }

    public void setDataFimExecucao(Calendar dataFimExecucao) {
        this.dataFimExecucao = dataFimExecucao;
    }

    public Calendar getDataInicioExecucao() {
        return dataInicioExecucao;
    }

    public void setDataInicioExecucao(Calendar dataInicioExecucao) {
        this.dataInicioExecucao = dataInicioExecucao;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getMensagemLivre() {
        return mensagemLivre;
    }

    public void setMensagemLivre(String mensagemLivre) {
        this.mensagemLivre = mensagemLivre;
    }


    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    public String getStatusDescricao(){
        return status != null ? status.getDescricao() : "";
    }

    public String getClienteNome(){
        return cliente != null ? cliente.getNome() : "";
    }

    public String getResponsavelNome(){
        return responsavel != null ? responsavel.getNome() : "";
    }

    public String getDataCriacaoFormat(){
        if(dataCriacao != null){
            return dataCriacao.get(Calendar.MONTH) + "/" +
                    dataCriacao.get(Calendar.DAY_OF_MONTH) + "/" +
                    dataCriacao.get(Calendar.YEAR);
        }
        return "";
    }
}
