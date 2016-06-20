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
        CPFValidator cpfValidator = new CPFValidator();
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCliente(true);
        cliente.setSenha(nome);

        if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            view.mensagem("Cadastro de Cliente", "", "Todos campos são obrigatórios.", Alert.AlertType.ERROR);
        } else if (!cpfValidator.invalidMessagesFor(cpf).isEmpty()) {
            view.mensagem("Cadastro de Cliente", "", "CPF inválido.", Alert.AlertType.ERROR);
        } else {
            if ( dao.buscarCPF(cpf) == null ) {
                if (dao.cadastrar(cliente)) {
                    view.mensagem("Cadastro de Cliente", "", "Cliente cadastrado com sucesso!", Alert.AlertType.CONFIRMATION);
                    return;
                } else {
                    view.mensagem("Cadastro de Cliente", "", "Ocorreu um erro ao salvar o cliente, entre em contato com o suporte.", Alert.AlertType.ERROR);
                }
            } else {
                view.mensagem("Cadastro de Cliente", "", "Este CPF já esta cadastrado.", Alert.AlertType.ERROR);
            }
        }
    }

    public void deletar(Integer id){
        try {
            dao.deletarPessoa(id);
            viewList.mensagem("", "", "Teste", Alert.AlertType.INFORMATION);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Cliente> buscarTodos(){
        return this.dao.buscar();
    }

}
