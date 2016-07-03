package br.ufsc.ine.aps.views.protocolo;

import br.ufsc.ine.aps.controllers.protocolo.ControllerProtocolo;
import br.ufsc.ine.aps.models.Protocolo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewProtocoloList implements Initializable {


    @FXML
    private TableColumn columnExcluir;

    @FXML
    private TableColumn colunaEditar;

    @FXML
    private TableView<Protocolo> tabelaProtocolos;

    private List<Protocolo> protocolos;
    private ControllerProtocolo controllerProtocolo;
    private ObservableList rows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controllerProtocolo = ControllerProtocolo.getInstance();
        this.geraDadosParaTabela();

    }

    private void geraDadosParaTabela() {
        this.protocolos = controllerProtocolo.listarProtocolos();
        tabelaProtocolos.getItems().clear();
        rows = FXCollections.observableArrayList(protocolos);
        tabelaProtocolos.setItems(rows);
        tabelaProtocolos.refresh();

    }



}
