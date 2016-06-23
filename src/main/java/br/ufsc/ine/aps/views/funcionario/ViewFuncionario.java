package br.ufsc.ine.aps.views.funcionario;

import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.controllers.funcionario.ControllerFuncionario;
import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.views.pessoa.ViewPessoa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;

import java.net.URL;
import java.util.ResourceBundle;


public class ViewFuncionario extends ViewPessoa implements Initializable {

    @FXML
    private ComboBox<String> tipoFuncionario;

    private Pessoa toEdit;

    private ControllerFuncionario controllerFuncionario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controllerFuncionario = ControllerFuncionario.getInstance();
    }

    @FXML
    public void handleAdicionarFuncionario() {
        SingleSelectionModel<String> selectionModel = tipoFuncionario.getSelectionModel();
        TipoUsuario tipo = null;
        if(selectionModel.getSelectedItem().equals("Atendente")){
            tipo = TipoUsuario.ATENDENTE;
        } else{
            tipo = TipoUsuario.OPERADOR_SUPORTE;
        }

        try {
            if(toEdit==null) {
                this.controllerFuncionario.salvar(super.cpf.getText(), super.nome.getText(), super.email.getText(), super.telefone.getText(), tipo);
                super.mensagem("Cadastro de Funcionário", "", "Funcionário cadastrado com sucesso!", Alert.AlertType.CONFIRMATION);
            } else{
                this.controllerFuncionario.atualizar(toEdit.getId(), super.cpf.getText(), super.nome.getText(), super.email.getText(), super.telefone.getText(), tipo);
                super.mensagem("Cadastro de Funcionário", "", "Funcionário atualizado com sucesso!", Alert.AlertType.CONFIRMATION);
            }
        } catch (Exception e){
            super.mensagem("Cadastro de Funcionário", "", "Ocorreu um erro ao salvar o funcionário, entre em contato com o suporte.", Alert.AlertType.ERROR);
        }

    }

    public void setToEdit(Pessoa toEdit) {
        this.toEdit = toEdit;
        if (this.toEdit != null) {
            this.nome.setText(toEdit.getNome());
            this.cpf.setText(toEdit.getCpf());
            this.email.setText(toEdit.getEmail());
            this.tipoFuncionario.setValue(toEdit.getDescTipo());
            this.telefone.setText(toEdit.getTelefone());
            this.nome.requestFocus();
        }
    }
}
