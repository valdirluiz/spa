package br.ufsc.ine.aps.controllers.cliente;

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

        boolean error = false;
        if ( dao.buscarCPF(cpf) == null ) {
            if (dao.cadastrar(cliente)) {
                view.mensagem("Cadastro de Cliente", "", "Cliente cadastrado com sucesso!", Alert.AlertType.CONFIRMATION);
                return;
            } else {
                error = true;
            }
        } else {
            error = true;
        }

        if (error) {
            view.mensagem("Cadastro de Cliente", "", "Ocorreu um erro ao salvar o cliente, entre em contato com o suporte.", Alert.AlertType.ERROR);
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
