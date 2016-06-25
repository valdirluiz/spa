package br.ufsc.ine.aps.controllers.cliente;

import br.com.caelum.stella.validation.CPFValidator;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.views.cliente.ViewCliente;
import br.ufsc.ine.aps.views.cliente.ViewClienteList;
import javafx.scene.control.Alert;

import java.util.List;

public class ControllerCliente {

    private ClienteDao dao = new ClienteDao();
    private ViewCliente view;
    private ViewClienteList viewList;

    private static ControllerCliente instance = new ControllerCliente();

    private ControllerCliente() {}

    public static ControllerCliente getInstance() {
        return instance;
    }

    public ViewCliente getView() {
        return view;
    }

    public void setView(ViewCliente view) {
        this.view = view;
    }

    public ViewClienteList getViewList() {
        return viewList;
    }

    public void setViewList(ViewClienteList viewList) {
        this.viewList = viewList;
    }

    public void adicionar(String cpf, String nome, String email, String telefone) {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCliente(true);
        cliente.setSenha(nome);

        if (validaAtributos(cliente)) {
            if (validaCPF(cliente.getCpf())) {
                if (clienteExiste(cliente.getCpf())) {
                    view.mensagem("Cadastro de Cliente", "", "Este CPF já esta cadastrado.", Alert.AlertType.ERROR);
                } else {
                    if (dao.salvar(cliente)) {
                        view.mensagem("Cadastro de Cliente", "", "Cliente cadastrado com sucesso!", Alert.AlertType.NONE);
                    } else {
                        view.mensagem("Cadastro de Cliente", "", "Ocorreu um erro ao salvar o cliente, entre em contato com o suporte.", Alert.AlertType.ERROR);
                    }
                }
            } else {
                view.mensagem("Cadastro de Cliente", "", "CPF inválido.", Alert.AlertType.ERROR);
            }
        } else {
            view.mensagem("Cadastro de Cliente", "", "Todos campos são obrigatórios.", Alert.AlertType.ERROR);
        }
    }

    public void atualizar(String id, String cpf, String nome, String email, String telefone) {
        CPFValidator cpfValidator = new CPFValidator();

        if (id.isEmpty()) {
            view.mensagem("Cadastro de Cliente", "", "Ocorreu um problema na identificação do Id do cliente, favor tentar novamente.", Alert.AlertType.ERROR);
        } else {
            Cliente cliente = dao.buscarId(Integer.parseInt(id));
            if (cliente != null) {
                cliente.setCpf(cpf);
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setTelefone(telefone);

                if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
                    view.mensagem("Cadastro de Cliente", "", "Todos campos são obrigatórios.", Alert.AlertType.ERROR);
                } else if (!cpfValidator.invalidMessagesFor(cpf).isEmpty()) {
                    view.mensagem("Cadastro de Cliente", "", "CPF inválido.", Alert.AlertType.ERROR);
                } else {
                    if (dao.salvar(cliente)) {
                        view.mensagem("Cadastro de Cliente", "", "Cliente atualizado com sucesso!", Alert.AlertType.CONFIRMATION);
                        return;
                    } else {
                        view.mensagem("Cadastro de Cliente", "", "Ocorreu um erro ao atualizar o cliente, entre em contato com o suporte.", Alert.AlertType.ERROR);
                    }
                }
            } else {
                view.mensagem("Cadastro de Cliente", "", "Cliente não encontrado.", Alert.AlertType.ERROR);
            }
        }
    }

    public void deletar(Integer id){
        try {
            dao.delete(id);
            viewList.mensagem("", "", "Teste", Alert.AlertType.INFORMATION);
        } catch (Exception e){
            e.printStackTrace();
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
}
