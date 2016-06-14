package br.ufsc.ine.aps.views.cliente;

import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public void handleSalvarClienteButtonAction() {
        ctrl.test(cpf.getText(), nome.getText());
    }

}
