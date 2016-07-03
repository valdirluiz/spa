package br.ufsc.ine.aps.controllers.cliente;

import br.com.caelum.stella.validation.CPFValidator;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.views.cliente.ViewCliente;
import br.ufsc.ine.aps.views.cliente.ViewClienteList;
import javafx.scene.control.Alert;

import java.util.List;

public class ControllerCliente {

    private DaoCliente dao = new DaoCliente();
   // private ViewCliente view;
    private ViewClienteList viewList;

    private static ControllerCliente instance = new ControllerCliente();

    private ControllerCliente() {}

    public static ControllerCliente getInstance() {
        return instance;
    }



    public void setView(ViewCliente view) {
        //this.view = view;
    }

    public ViewClienteList getViewList() {
        return viewList;
    }

    public void setViewList(ViewClienteList viewList) {
        this.viewList = viewList;
    }

    public String adicionar(String cpf, String senha, String nome, String email, String telefone) {
        Cliente cliente = new Cliente(cpf, senha, email, telefone, nome);

        if (validaAtributos(cliente)) {
            if (validaCPF(cliente.getCpf())) {
                if (clienteExiste(cliente.getCpf())) {
                    return "Este CPF já esta cadastrado.";
                } else {
                    if (dao.salvar(cliente)) {
                        return null;
                    } else {
                        return "Ocorreu um erro ao salvar o cliente, entre em contato com o suporte.";
                    }
                }
            } else {
                return "CPF inválido.";
            }
        } else {
            return "Todos campos são obrigatórios.";
        }

    }

    public String atualizar(String id, String cpf, String nome, String email, String telefone) {
        CPFValidator cpfValidator = new CPFValidator();

        if (id.isEmpty()) {
            return "Ocorreu um problema na identificação do Id do cliente, favor tentar novamente.";
        } else {
            Cliente cliente = findById(Integer.valueOf(id));
            if (cliente != null) {
                cliente.setCpf(cpf);
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setTelefone(telefone);

                if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
                    return "Todos campos são obrigatórios.";
                } else if (!cpfValidator.invalidMessagesFor(cpf).isEmpty()) {
                    return "CPF inválido.";
                } else {
                    if (dao.salvar(cliente)) {
                        return null;
                    } else {
                        return "Ocorreu um erro ao atualizar o cliente, entre em contato com o suporte.";
                    }
                }
            } else {
                return "Cliente não encontrado.";
            }
        }
    }

    public void deletar(Integer id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    public List<Cliente> buscarTodos(){
        return this.dao.buscar();
    }

    public boolean validaAtributos(Cliente cliente) {
        return (!cliente.getCpf().isEmpty() && !cliente.getNome().isEmpty() && !cliente.getEmail().isEmpty() && !cliente.getTelefone().isEmpty());
    }

    public boolean validaCPF(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        return cpfValidator.invalidMessagesFor(cpf).isEmpty();
    }

    public boolean clienteExiste(String cpf) {
        return (dao.buscarCPF(cpf) != null);
    }

    public Cliente findByCpf(String cpf){
        return dao.buscarCPF(cpf);
    }

    public Cliente findById(Integer id){
        return dao.buscarId(id);
    }
}
