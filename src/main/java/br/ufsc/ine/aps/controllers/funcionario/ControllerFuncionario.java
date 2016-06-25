package br.ufsc.ine.aps.controllers.funcionario;

import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.exceptions.CpfJaCadastrado;
import br.ufsc.ine.aps.models.Atendente;

import br.ufsc.ine.aps.models.Operador;
import br.ufsc.ine.aps.models.Pessoa;

import java.util.ArrayList;
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
        if (existeFuncionarioComCpf(cpf)){
            throw new CpfJaCadastrado();
        }
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
            pessoa.setCliente(false);
            this.funcionarioDao.save(pessoa);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<Pessoa> buscaFuncionarios(){
        List<Integer> tipos = new ArrayList<>();
        tipos.add(TipoUsuario.ATENDENTE.getId());
        tipos.add(TipoUsuario.OPERADOR_SUPORTE.getId());
        return this.funcionarioDao.findByTipos(tipos);
    }

    public void deletarPessoa(Pessoa pessoa) throws Exception {
        this.funcionarioDao.delete(pessoa.getId());
    }

    public void atualizar(Integer id, String cpf, String nome, String email, String telefone, TipoUsuario tipo) throws Exception {
        Pessoa pessoa = null;
        if(tipo.equals(TipoUsuario.ATENDENTE)){
            pessoa = new Atendente();
        } else{
            pessoa = new Operador();
        }
        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setCpf(cpf);
        pessoa.setEmail(email);
        pessoa.setTelefone(telefone);
        pessoa.setTipoUsuario(tipo);
        this.funcionarioDao.update(pessoa);
    }


    public boolean existeFuncionarioComCpf(String cpf){
        boolean atentende = this.funcionarioDao.existeCpf(cpf, TipoUsuario.ATENDENTE);
        boolean operador = this.funcionarioDao.existeCpf(cpf, TipoUsuario.OPERADOR_SUPORTE);
        if(atentende || operador){
            return true;
        } else{
            return false;
        }
    }

}




