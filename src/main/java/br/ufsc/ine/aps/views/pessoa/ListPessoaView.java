package br.ufsc.ine.aps.views.pessoa;

import br.ufsc.ine.aps.models.Pessoa;

public interface ListPessoaView {

    public void deletarPessoa(Pessoa pessoa);
    public void abreTelaEdicao(Pessoa pessoa);
}
