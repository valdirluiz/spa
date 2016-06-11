package br.ufsc.ine.aps.models;


import java.util.List;

public abstract class Pessoa implements Autenticavel{

    private Integer id;
    private String cpf;
    private String senha;
    private String nome;
    private String telefone;
    private String email;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public List<Notificacao> getNotificacoes() {
        return null;
    }

    @Override
    public String getNome() {
        return nome;
    }
}
