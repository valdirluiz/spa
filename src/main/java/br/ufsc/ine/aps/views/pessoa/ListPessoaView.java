package br.ufsc.ine.aps.views.pessoa;

import br.ufsc.ine.aps.models.Pessoa;

/**
 * Created by Valdir Luiz on 22/06/2016.
 */
public interface ListPessoaView {

    public void deletarPessoa(Pessoa pessoa);
    public void abreTelaEdicao(Pessoa pessoa);
}
