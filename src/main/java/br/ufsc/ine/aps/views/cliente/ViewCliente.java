package br.ufsc.ine.aps.views.cliente;

import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.models.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCliente implements Initializable{

    private ControllerCliente ctrl;
    private Cliente toEdit;

    @FXML
    private TextField id;

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
        this.ctrl.setView(this);
    }

    @FXML
    public void handleAdicionarCliente() {
        if(toEdit == null){
            this.ctrl.adicionar(cpf.getText(), nome.getText(), email.getText(), telefone.getText());
            resetForm();
        } else{
            this.ctrl.atualizar(id.getText(), cpf.getText(), nome.getText(), email.getText(), telefone.getText());
        }


    }

    public void mensagem(String title, String header, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);

        if (!title.isEmpty()) alert.setTitle(title);
        if (!header.isEmpty()) alert.setHeaderText(header);
        if (!message.isEmpty()) alert.setContentText(message);

        alert.showAndWait();
    }

    public void resetForm() {
        this.cpf.clear();
        this.nome.clear();
        this.email.clear();
        this.telefone.clear();
    }

    public void setToEdit(Cliente toEdit) {
        this.toEdit = toEdit;
        if (this.toEdit != null) {
            this.id.setText(toEdit.getId().toString());
            this.nome.setText(toEdit.getNome());
            this.cpf.setText(toEdit.getCpf());
            this.email.setText(toEdit.getEmail());
            this.telefone.setText(toEdit.getTelefone());
            this.nome.requestFocus();
        }
    }
}
