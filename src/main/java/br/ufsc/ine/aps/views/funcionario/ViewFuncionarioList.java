package br.ufsc.ine.aps.views.funcionario;

import br.ufsc.ine.aps.controllers.funcionario.ControllerFuncionario;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.views.pessoa.BotaoDeletar;
import br.ufsc.ine.aps.views.pessoa.BotaoEditar;
import br.ufsc.ine.aps.views.pessoa.ListPessoaView;
import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class ViewFuncionarioList implements Initializable, ListPessoaView {

    private ControllerFuncionario controllerFuncionario;

    @FXML
    private TableView<Cliente> tabelaFuncionarios;

    @FXML
    private TableColumn columnExcluir;

    @FXML
    private TableColumn colunaEditar;

    private List<Pessoa> funcionarios;
    private ObservableList funcionariosTabela;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerFuncionario = ControllerFuncionario.getInstance();
        this.geraDadosParaTabela();
        this.insereBotaoExcluir();
        this.insereBotaoEditar();
    }

    private void geraDadosParaTabela(){
        this.funcionarios = controllerFuncionario.buscaFuncionarios();
        tabelaFuncionarios.getItems().clear();
        funcionariosTabela = FXCollections.observableArrayList(funcionarios);
        tabelaFuncionarios.setItems(funcionariosTabela);
        tabelaFuncionarios.refresh();
    }

    private void insereBotaoExcluir(){
        columnExcluir.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        return new BotaoDeletar(ViewFuncionarioList.this);
                    }
                });
    }

    private void insereBotaoEditar(){
        colunaEditar.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        return new BotaoEditar(ViewFuncionarioList.this);
                    }
                });
    }


    @Override
    public void deletarPessoa(Pessoa pessoa) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Atenção");
        alert.setContentText("Realmente deseja excluir o funcionário " + pessoa.getNome()   + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                this.controllerFuncionario.deletarPessoa(pessoa);
                this.mensagem("Confirmação", "Confirmação", "Funcionário deletado com sucesso!", Alert.AlertType.CONFIRMATION);
                this.geraDadosParaTabela();
            } catch (Exception e) {
                this.mensagem("Alerta", "Alerta", "Falha ao deletar funcionário", Alert.AlertType.ERROR);
            }
        }
    }

    @Override
    public void abreTelaEdicao(Pessoa pessoa) {

    }

    public void mensagem(String title, String header, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);

        if (!title.isEmpty()) alert.setTitle(title);
        if (!header.isEmpty()) alert.setHeaderText(header);
        if (!message.isEmpty()) alert.setContentText(message);

        alert.showAndWait();
    }
}
