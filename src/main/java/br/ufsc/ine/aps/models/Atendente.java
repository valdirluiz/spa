package br.ufsc.ine.aps.models;


import br.ufsc.ine.aps.enuns.TipoUsuario;

public class Atendente extends Pessoa {

    public Atendente(){
        super.setTipoUsuario(TipoUsuario.ATENDENTE);
    }

    public Atendente(Integer id, String nome, String cpf, String email, String telefone) {
        super.setId(id);
        super.setNome(nome);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setTelefone(telefone);
        super.setTipoUsuario(TipoUsuario.ATENDENTE);
    }
}
