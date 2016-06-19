package br.ufsc.ine.aps.controllers.cliente;

import br.ufsc.ine.aps.models.Cliente;

import java.util.List;

public class ControllerCliente {

    private ClienteDao dao = new ClienteDao();

    private static ControllerCliente instance = new ControllerCliente();

    private ControllerCliente() {}

    public static ControllerCliente getInstance() {
        return instance;
    }

    public boolean adicionar(String cpf, String nome, String email, String telefone) {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCliente(true);
        cliente.setSenha(nome);

        Cliente teste = dao.findByCPF(cpf);

        if ( dao.findByCPF(cpf) == null ) {
            return dao.cadastrar(cliente);
        }

        return false;
    }

    public boolean deletarCliente(Integer id){
        try {
            this.dao.deletarPessoa(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public List<Cliente> findAll(){
        return this.dao.findClientes();
    }

}
