package br.ufsc.ine.aps.views.cliente;

import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCliente implements Initializable{

    private ControllerCliente ctrl;

    @FXML
    private TextField cpf;
    @FXML
    private TextField nome;
    @FXML
    private TextField email;
    @FXML
    private TextField telefone;

    public void initialize(URL location, ResourceBundle resources) {
        this.ctrl = ControllerCliente.getInstance();
    }

    @FXML
    public void handleAdicionarCliente() {
        boolean result = this.ctrl.adicionar(cpf.getText(), nome.getText(), email.getText(), telefone.getText());
        mensagemByResult(result);
        resetForm();
    }

    public void mensagemByResult(boolean result) {
        if (result) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Cliente");
            alert.setHeaderText("Cliente cadastrado com sucesso!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro de Cliente");
            alert.setHeaderText("Ocorreu um erro ao salvar o cliente, entre em contato com o suporte.");
            alert.showAndWait();
        }
    }

    public void resetForm() {
        this.cpf.clear();
        this.nome.clear();
        this.email.clear();
        this.telefone.clear();
    }

}
