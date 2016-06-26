package br.ufsc.ine.aps.models;


import br.ufsc.ine.aps.enuns.Area;
import br.ufsc.ine.aps.enuns.Categoria;
import br.ufsc.ine.aps.enuns.Status;

import java.util.Calendar;

public class Protocolo {

    private Integer id;
    private Calendar dataCriacao;
    private Calendar dataFimExecucao;
    private Calendar dataInicioExecucao;
    private String feedback;
    private String identificador;
    private String mensagemLivre;
    private String movivoCancelamento;
    private String resposta;
    private Status status;
    private Categoria categoria;
    private Area area;
    private Cliente cliente;
    private Atendente atendente;
    private Operador operador;

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

    public String getMovivoCancelamento() {
        return movivoCancelamento;
    }

    public void setMovivoCancelamento(String movivoCancelamento) {
        this.movivoCancelamento = movivoCancelamento;
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

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Protocolo protocolo = (Protocolo) o;

        if (id != null ? !id.equals(protocolo.id) : protocolo.id != null) return false;
        if (dataCriacao != null ? !dataCriacao.equals(protocolo.dataCriacao) : protocolo.dataCriacao != null)
            return false;
        if (dataFimExecucao != null ? !dataFimExecucao.equals(protocolo.dataFimExecucao) : protocolo.dataFimExecucao != null)
            return false;
        if (dataInicioExecucao != null ? !dataInicioExecucao.equals(protocolo.dataInicioExecucao) : protocolo.dataInicioExecucao != null)
            return false;
        if (feedback != null ? !feedback.equals(protocolo.feedback) : protocolo.feedback != null) return false;
        if (identificador != null ? !identificador.equals(protocolo.identificador) : protocolo.identificador != null)
            return false;
        if (mensagemLivre != null ? !mensagemLivre.equals(protocolo.mensagemLivre) : protocolo.mensagemLivre != null)
            return false;
        if (movivoCancelamento != null ? !movivoCancelamento.equals(protocolo.movivoCancelamento) : protocolo.movivoCancelamento != null)
            return false;
        if (resposta != null ? !resposta.equals(protocolo.resposta) : protocolo.resposta != null) return false;
        if (status != protocolo.status) return false;
        if (categoria != protocolo.categoria) return false;
        if (area != protocolo.area) return false;
        if (cliente != null ? !cliente.equals(protocolo.cliente) : protocolo.cliente != null) return false;
        if (atendente != null ? !atendente.equals(protocolo.atendente) : protocolo.atendente != null) return false;
        return operador != null ? operador.equals(protocolo.operador) : protocolo.operador == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dataCriacao != null ? dataCriacao.hashCode() : 0);
        result = 31 * result + (dataFimExecucao != null ? dataFimExecucao.hashCode() : 0);
        result = 31 * result + (dataInicioExecucao != null ? dataInicioExecucao.hashCode() : 0);
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        result = 31 * result + (identificador != null ? identificador.hashCode() : 0);
        result = 31 * result + (mensagemLivre != null ? mensagemLivre.hashCode() : 0);
        result = 31 * result + (movivoCancelamento != null ? movivoCancelamento.hashCode() : 0);
        result = 31 * result + (resposta != null ? resposta.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (cliente != null ? cliente.hashCode() : 0);
        result = 31 * result + (atendente != null ? atendente.hashCode() : 0);
        result = 31 * result + (operador != null ? operador.hashCode() : 0);
        return result;
    }
}
