package br.ufsc.ine.aps.views.funcionario;

import br.ufsc.ine.aps.controllers.funcionario.ControllerFuncionario;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.views.pessoa.BotaoDeletar;
import br.ufsc.ine.aps.views.pessoa.BotaoEditar;
import br.ufsc.ine.aps.views.pessoa.ViewPessoaList;
import br.ufsc.ine.aps.views.principal.ViewPrincipal;
import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class ViewFuncionarioList implements Initializable, ViewPessoaList {

    private ControllerFuncionario controllerFuncionario;

    private ViewPrincipal viewPrincipal;

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
                this.mensagem("Funcionário deletado com sucesso!");
                this.geraDadosParaTabela();
            } catch (Exception e) {
                this.mensagem("Alerta", "Alerta", "Falha ao deletar funcionário", Alert.AlertType.ERROR);
            }
        }
    }

    @Override
    public void abreTelaEdicao(Pessoa pessoa) {
        try{
            AnchorPane content = new AnchorPane();
            FXMLLoader loader = new FXMLLoader();
            Parent page =  loader.load(ViewFuncionario.class.getResourceAsStream("editar.fxml"));
            ViewFuncionario controller =  loader.getController();
            controller.setToEdit(pessoa);
            content.getChildren().setAll(page);
            this.viewPrincipal.atualizaConteudo(content);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void mensagem(String title, String header, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);

        if (!title.isEmpty()) alert.setTitle(title);
        if (!header.isEmpty()) alert.setHeaderText(header);
        if (!message.isEmpty()) alert.setContentText(message);

        alert.showAndWait();
    }

    public void mensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.NONE, null, ButtonType.CLOSE);

        alert.setTitle("Remoção de Funcionários");
        if (!mensagem.isEmpty()) alert.setContentText(mensagem);

        alert.showAndWait();
    }

    public void setViewPrincipal(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
}
