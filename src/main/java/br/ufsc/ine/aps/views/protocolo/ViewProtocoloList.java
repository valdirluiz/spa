package br.ufsc.ine.aps.views.protocolo;

import br.ufsc.ine.aps.controllers.protocolo.ControllerProtocolo;
import br.ufsc.ine.aps.exceptions.ProtocoloJaCancelado;
import br.ufsc.ine.aps.exceptions.StatusEmAndamento;
import br.ufsc.ine.aps.models.Protocolo;

import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.util.Callback;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewProtocoloList implements Initializable {


    @FXML
    private TableColumn columnCancelar;

    @FXML
    private TableView<Protocolo> tabelaProtocolos;

    private List<Protocolo> protocolos;
    private ControllerProtocolo controllerProtocolo;
    private ObservableList rows;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controllerProtocolo = ControllerProtocolo.getInstance();
        this.geraDadosParaTabela();
        this.insereBotaoCancelar();

    }

    private void geraDadosParaTabela() {
        this.protocolos = controllerProtocolo.listarProtocolos();
        tabelaProtocolos.getItems().clear();
        rows = FXCollections.observableArrayList(protocolos);
        tabelaProtocolos.setItems(rows);
        tabelaProtocolos.refresh();

    }

    private void insereBotaoCancelar(){
        columnCancelar.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        return new BotaoCancelarProtocolo(ViewProtocoloList.this);
                    }
                });
    }

    public void cancelarProtocolo(Protocolo protocolo) {
        try {
            this.controllerProtocolo.cancelarProtocolo(protocolo);
            this.geraDadosParaTabela();
            this.mensagem("Protocolo cancelado com sucesso.",Alert.AlertType.CONFIRMATION );
        } catch (ProtocoloJaCancelado e){
            this.mensagem("Protocolo já cancelado.",Alert.AlertType.ERROR );
        } catch (StatusEmAndamento e){
            this.mensagem("Não é possível cancelar, protocolo já esta em execução.",Alert.AlertType.ERROR );
        } catch (Exception e){
            this.mensagem("Falha ao cancelar o protocolo.",Alert.AlertType.ERROR );
            e.printStackTrace();
        }

    }

    public void mensagem(String mensagem, Alert.AlertType type) {
        Alert alert = new Alert(type, null, ButtonType.CLOSE);
        alert.setTitle("Remoção de Funcionários");
        if (!mensagem.isEmpty()) alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
