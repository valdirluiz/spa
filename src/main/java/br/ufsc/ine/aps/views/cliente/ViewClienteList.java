package br.ufsc.ine.aps.views.cliente;

import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.models.Cliente;

import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.views.pessoa.BotaoDeletar;
import br.ufsc.ine.aps.views.pessoa.BotaoEditar;
import br.ufsc.ine.aps.views.pessoa.ViewPessoaList;
import br.ufsc.ine.aps.views.principal.ViewPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.prism.impl.Disposer.Record;

import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class ViewClienteList implements Initializable, ViewPessoaList {

    private ControllerCliente ctrl;

    private ViewPrincipal viewPrincipal;

    @FXML
    private TableView<Cliente> tabelaClientes;

    @FXML
    private TableColumn columnExcluir;

    @FXML
    private TableColumn columnEditar;

    @FXML
    private AnchorPane pageContent;

    @FXML
    private AnchorPane pageTitle;

    private List<Cliente> clientes;
    private ObservableList clientesTabela;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ctrl = ControllerCliente.getInstance();
        this.ctrl.setViewList(this);
        this.geraDadosParaTabela();
        this.insereBotaoExcluir();
        this.insereBotaoEditar();
    }

    private void geraDadosParaTabela(){
        this.clientes = this.ctrl.buscarTodos();
        tabelaClientes.getItems().clear();
        clientesTabela = FXCollections.observableArrayList(clientes);
        tabelaClientes.setItems(clientesTabela);
        tabelaClientes.refresh();
    }

    private void insereBotaoExcluir(){
        columnExcluir.setCellFactory(
            new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
                @Override
                public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                    return new BotaoDeletar(ViewClienteList.this);
                }
            });
    }

    private void insereBotaoEditar(){
        columnEditar.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
                    @Override
                    public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                        return new BotaoEditar(ViewClienteList.this);
                    }
                });
    }

    public void mensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.NONE, null, ButtonType.CLOSE);

        alert.setTitle("Remoção de Clientes");
        if (!mensagem.isEmpty()) alert.setContentText(mensagem);

        alert.showAndWait();
    }

    public void setViewPrincipal(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }

    @Override
    public void abreTelaEdicao(Pessoa cliente){
        try{
            AnchorPane content = new AnchorPane();
            FXMLLoader loader = new FXMLLoader();
            Parent page =  loader.load(ViewCliente.class.getResourceAsStream("editar.fxml"));
            ViewCliente controller =  loader.getController();
            controller.setToEdit(cliente);
            content.getChildren().setAll(page);
            this.viewPrincipal.atualizaConteudo(content);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void deletarPessoa(Pessoa pessoa) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Atenção");
        alert.setContentText("Realmente deseja excluir o cliente " + pessoa.getNome()   + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                this.ctrl.deletar(pessoa.getId());
                geraDadosParaTabela();
                this.mensagem("Cliente deletado com sucesso!");
            } catch (Exception e){
                this.mensagem("Remoção de Cliente", "Alerta", "Falha ao deletar cliente", Alert.AlertType.ERROR);
            }

        }
    }

    public void mensagem(String title, String header, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);

        if (!title.isEmpty()) alert.setTitle(title);
        if (!header.isEmpty()) alert.setHeaderText(header);
        if (!message.isEmpty()) alert.setContentText(message);

        alert.showAndWait();
    }

}

