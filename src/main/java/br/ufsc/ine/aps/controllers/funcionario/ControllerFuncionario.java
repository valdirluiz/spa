package br.ufsc.ine.aps.controllers.funcionario;

import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.Atendente;

import br.ufsc.ine.aps.models.Operador;
import br.ufsc.ine.aps.models.Pessoa;

import java.util.List;

public class ControllerFuncionario {

    private static ControllerFuncionario ourInstance = new ControllerFuncionario();

    public static ControllerFuncionario getInstance() {
        return ourInstance;
    }

    private FuncionarioDao funcionarioDao;

    private ControllerFuncionario() {
        this.funcionarioDao = FuncionarioDao.getInstance();
    }

    public void salvar(String cpf, String nome, String email, String telefone, TipoUsuario tipo) throws Exception {
        try {
            Pessoa pessoa = null;

            if (tipo.equals(TipoUsuario.ATENDENTE)) {
                pessoa = new Atendente();
            } else {
                pessoa = new Operador();
            }

            pessoa.setCpf(cpf);
            pessoa.setEmail(email);
            pessoa.setNome(nome);
            pessoa.setTelefone(telefone);
            pessoa.setTipoUsuario(tipo);
            this.funcionarioDao.cadastrar(pessoa);

        }catch (Exception e){
            throw new Exception(e);
        }

    }

    public List<Pessoa> buscaFuncionarios(){
        return this.funcionarioDao.findAll();
    }
}




