package br.ufsc.ine.aps.views.cliente;



import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.models.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.prism.impl.Disposer.Record;

import javafx.util.Callback;

public class ViewClienteList implements Initializable {

    private ControllerCliente ctrl;

    @FXML
    private TableView<Cliente> tabelaClientes;

    @FXML
    private TableColumn columnExcluir;

    private List<Cliente> clientes;
    private ObservableList clientesTabela;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctrl = ControllerCliente.getInstance();
        this.clientes = this.ctrl.findAll();
        this.geraDadosParaTabela();
        this.insereBotaoExcluir();
    }


    private void geraDadosParaTabela(){
        this.clientes = this.ctrl.findAll();
        tabelaClientes.getItems().clear();
        clientesTabela =
                FXCollections.observableArrayList(clientes);
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

    public void deletarCliente(Cliente cliente) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Atenção");
        alert.setContentText("Realmente deseja excluir o cliente " + cliente.getNome()   + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            this.ctrl.deletarCliente(cliente.getId());
            geraDadosParaTabela();
        }
    }



}
