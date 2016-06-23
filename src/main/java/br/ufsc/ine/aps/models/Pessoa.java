package br.ufsc.ine.aps.models;


import br.ufsc.ine.aps.enuns.TipoUsuario;

import java.util.List;

public abstract class Pessoa implements Autenticavel{

    private Integer id = 0;
    private String cpf;
    private String senha;
    private String nome;
    private String telefone;
    private String email;
    private TipoUsuario tipoUsuario;
    private boolean isCliente;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    @Override
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public List<Notificacao> getNotificacoes() {
        return null;
    }

    public boolean isCliente() {
        return isCliente;
    }

    public void setCliente(boolean cliente) {
        isCliente = cliente;
    }

    public String getDescTipo(){
        return this.tipoUsuario.getDescricao();
    }
}
