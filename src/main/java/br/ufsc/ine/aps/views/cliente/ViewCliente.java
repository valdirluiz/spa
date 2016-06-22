package br.ufsc.ine.aps.views.cliente;

import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.views.pessoa.ViewPessoa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;

public class ViewCliente extends ViewPessoa implements Initializable{

    private ControllerCliente ctrl;

    private Cliente toEdit;


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
            this.ctrl.atualizar(toEdit.getId().toString(), cpf.getText(), nome.getText(), email.getText(), telefone.getText());
        }


    }

    public void setToEdit(Cliente toEdit) {
        this.toEdit = toEdit;
        if (this.toEdit != null) {
            this.nome.setText(toEdit.getNome());
            this.cpf.setText(toEdit.getCpf());
            this.email.setText(toEdit.getEmail());
            this.telefone.setText(toEdit.getTelefone());
            this.nome.requestFocus();
        }
    }
}
