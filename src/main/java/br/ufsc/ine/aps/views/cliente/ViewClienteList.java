package br.ufsc.ine.aps.views.cliente;


import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.models.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewClienteList implements Initializable {

    private ControllerCliente ctrl;

    @FXML
    private TableView<Cliente> tabelaClientes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctrl = ControllerCliente.getInstance();
        tabelaClientes.setItems(this.getInitialTableData());
    }

    private ObservableList getInitialTableData() {
        ObservableList data = FXCollections.observableList(this.ctrl.findAll());
        return data;
    }


}
