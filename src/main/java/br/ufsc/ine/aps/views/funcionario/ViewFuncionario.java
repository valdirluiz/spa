package br.ufsc.ine.aps.views.funcionario;

import br.com.caelum.stella.validation.CPFValidator;
import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.controllers.funcionario.ControllerFuncionario;
import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.exceptions.CpfJaCadastrado;
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
        boolean dadosValidos = this.validaDados();
        if(dadosValidos){
            TipoUsuario tipo = defineTipo();
            try {
                if(toEdit==null) {
                    this.salva(tipo);
                } else{
                    this.atualiza(tipo);
                }
            } catch (CpfJaCadastrado e){
                super.mensagem("Cadastro de Funcionário", "", "CPF já cadastrado", Alert.AlertType.ERROR);
            } catch (Exception e){
                super.mensagem("Cadastro de Funcionário", "", "Ocorreu um erro ao salvar o funcionário, entre em contato com o suporte.", Alert.AlertType.ERROR);
            }
        }

    }

    private void atualiza(TipoUsuario tipo) throws Exception {
        this.controllerFuncionario.atualizar(toEdit.getId(), super.cpf.getText(), super.nome.getText(), super.email.getText(), super.telefone.getText(), tipo);
        super.mensagem("Cadastro de Funcionário", "", "Funcionário atualizado com sucesso!", Alert.AlertType.CONFIRMATION);
    }

    private void salva(TipoUsuario tipo) throws Exception {
        this.controllerFuncionario.salvar(super.cpf.getText(), super.nome.getText(), super.email.getText(), super.telefone.getText(), tipo);
        super.mensagem("Cadastro de Funcionário", "", "Funcionário cadastrado com sucesso!", Alert.AlertType.CONFIRMATION);
    }

    private TipoUsuario defineTipo() {
        SingleSelectionModel<String> selectionModel = tipoFuncionario.getSelectionModel();
        TipoUsuario tipo = null;
        if(selectionModel.getSelectedItem().equals("Atendente")){
            tipo = TipoUsuario.ATENDENTE;
        } else{
            tipo = TipoUsuario.OPERADOR_SUPORTE;
        }
        return tipo;
    }

    private boolean validaDados() {
        boolean validou = true;
        CPFValidator cpfValidator = new CPFValidator();
        if(this.nome.getText().isEmpty() || this.telefone.getId().isEmpty() || this.email.getText().isEmpty() || this.tipoFuncionario.getValue()==null || this.cpf.getText().isEmpty()) {
            mensagem("Cadastro de Cliente", "", "Todos os campos são obrigatórios.", Alert.AlertType.ERROR);
            validou = false;
        } else  if(!cpfValidator.invalidMessagesFor(this.cpf.getText()).isEmpty()){
            mensagem("Cadastro de Cliente", "", "CPF inválido.", Alert.AlertType.ERROR);
            validou = false;
        }
        return validou;
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
