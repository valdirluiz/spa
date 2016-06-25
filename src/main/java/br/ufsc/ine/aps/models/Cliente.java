package br.ufsc.ine.aps.models;

import br.ufsc.ine.aps.enuns.TipoUsuario;
import java.util.Date;

public class Cliente extends Pessoa {

    private Date dataDeCadastro;

    public Cliente() {
        this.setDataDeCadastro(new Date());
        this.setTipoUsuario(TipoUsuario.CLIENTE);
    }

    public Cliente(String cpf, String senha, String email, String telefone, String nome) {
        this.setCpf(cpf);
        this.setSenha(senha);
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setDataDeCadastro(new Date());
        this.setTipoUsuario(TipoUsuario.CLIENTE);
        this.setCliente(true);
    }

    public Cliente(int id, String cpf, String senha, String nome, String telefone, String email) {
        this.setId(id);
        this.setCpf(cpf);
        this.setSenha(senha);
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setDataDeCadastro(new Date());
        this.setTipoUsuario(TipoUsuario.CLIENTE);
    }

    public Cliente(int id, String cpf, String senha, String nome) {
        this.setId(id);
        this.setCpf(cpf);
        this.setSenha(senha);
        this.setNome(nome);
        this.setDataDeCadastro(new Date());
        this.setTipoUsuario(TipoUsuario.CLIENTE);
    }

    public Date getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Date dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

}
