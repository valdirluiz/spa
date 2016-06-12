package br.ufsc.ine.aps.models;

import br.ufsc.ine.aps.enuns.TipoUsuario;

public class Cliente extends Pessoa {

    public Cliente() {
    }

    public Cliente(int id, String cpf, String senha, String nome, String telefone, String email) {
        this.setId(id);
        this.setCpf(cpf);
        this.setSenha(senha);
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setTipoUsuario(TipoUsuario.CLIENTE);
    }

    public Cliente(int id, String cpf, String senha, String nome) {
        this.setId(id);
        this.setCpf(cpf);
        this.setSenha(senha);
        this.setNome(nome);
        this.setTipoUsuario(TipoUsuario.CLIENTE);
    }

}
