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

    private DaoFuncionario daoFuncionario;

    private ControllerFuncionario() {
        this.daoFuncionario = DaoFuncionario.getInstance();
    }

    public void salvar(String cpf, String senha, String nome, String email, String telefone, TipoUsuario tipo) throws Exception {
        if (this.daoFuncionario.existeCpf(cpf, tipo)){
            throw new CpfJaCadastrado();
        }
        try {
            if (tipo.equals(TipoUsuario.ATENDENTE)) {
                Atendente atendente = buildAtendente(cpf, senha, nome, email, telefone, tipo);
                this.daoFuncionario.save(atendente);
            } else {
                Operador operador = this.buildOperador(cpf, senha, nome, email, telefone, tipo);
                this.daoFuncionario.save(operador);
            }
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    private Atendente buildAtendente(String cpf, String senha, String nome, String email, String telefone, TipoUsuario tipo) {
        Atendente pessoa = new Atendente();
        pessoa.setCpf(cpf);
        pessoa.setEmail(email);
        pessoa.setNome(nome);
        pessoa.setSenha(senha);
        pessoa.setTelefone(telefone);
        pessoa.setTipoUsuario(tipo);
        pessoa.setCliente(false);
        return pessoa;
    }

    private Operador buildOperador(String cpf, String senha, String nome, String email, String telefone, TipoUsuario tipo) {
        Operador pessoa = new Operador();
        pessoa.setCpf(cpf);
        pessoa.setEmail(email);
        pessoa.setNome(nome);
        pessoa.setSenha(senha);
        pessoa.setTelefone(telefone);
        pessoa.setTipoUsuario(tipo);
        pessoa.setCliente(false);
        return pessoa;
    }

    public List<Pessoa> buscaFuncionarios(){
        List<Integer> tipos = new ArrayList<>();
        tipos.add(TipoUsuario.ATENDENTE.getId());
        tipos.add(TipoUsuario.OPERADOR_SUPORTE.getId());
        return this.daoFuncionario.findByTipos(tipos);
    }

    public void deletarPessoa(Pessoa pessoa) throws Exception {
        this.daoFuncionario.delete(pessoa.getId());
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
        this.daoFuncionario.update(pessoa);
    }






}




