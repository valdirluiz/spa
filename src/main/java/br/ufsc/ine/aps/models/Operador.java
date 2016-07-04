package br.ufsc.ine.aps.models;

import br.ufsc.ine.aps.enuns.TipoUsuario;

public class Operador extends Pessoa {

    public Operador() {
        super.setTipoUsuario(TipoUsuario.OPERADOR_SUPORTE);
    }

    public Operador(Integer id, String nome, String cpf, String email, String telefone) {
        super.setId(id);
        super.setNome(nome);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setTelefone(telefone);
        super.setTipoUsuario(TipoUsuario.OPERADOR_SUPORTE);
    }

}
