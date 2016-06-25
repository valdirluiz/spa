package br.ufsc.ine.aps.views.cliente;

import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.views.pessoa.ViewPessoa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class ViewCliente extends ViewPessoa implements Initializable{

    private ControllerCliente ctrl;
    private Pessoa toEdit;

    @FXML
    private TextField id;
    @FXML
    private TextField cpf;
    @FXML
    private TextField nome;
    @FXML
    private PasswordField senha;
    @FXML
    private TextField email;
    @FXML
    private TextField telefone;

    public void initialize(URL location, ResourceBundle resources) {
        this.ctrl = ControllerCliente.getInstance();
        this.ctrl.setView(this);
    }

    @FXML
    public void salvarCliente() {
        String retorno = null;
        String[] tipoRetorno = {null, null};
        if(toEdit == null){
            tipoRetorno[0] = "Cadastro de Clientes";
            tipoRetorno[1] = "Cliente cadastrado com sucesso!";
            retorno = this.ctrl.adicionar(cpf.getText(), senha.getText(), nome.getText(), email.getText(), telefone.getText());
            if (retorno == null) resetForm();
        } else{
            tipoRetorno[0] = "Alteração de Clientes";
            tipoRetorno[1] = "Cliente alterado com sucesso!";
            retorno = this.ctrl.atualizar(toEdit.getId().toString(), cpf.getText(), nome.getText(), email.getText(), telefone.getText());
        }

        if (retorno == null) {
            this.mensagem(tipoRetorno[0], tipoRetorno[1]);
        } else {
            this.mensagem(tipoRetorno[0], "", retorno, Alert.AlertType.ERROR);
        }
    }

    public void setToEdit(Pessoa toEdit) {
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
