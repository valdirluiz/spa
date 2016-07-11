package br.ufsc.ine.aps.views.estatisticas;

import br.ufsc.ine.aps.controllers.protocolo.ControllerProtocolo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class ViewEstatistica implements Initializable {

    @FXML
    private Label totalProtocolos;
    @FXML
    private Label totalAguardandoAtendimento;
    @FXML
    private Label totalAtendimento;
    @FXML
    private Label totalAguardandoFeedback;
    @FXML
    private Label totalCancelado;
    @FXML
    private Label totalFinalizado;


    private ControllerProtocolo controllerProtocolo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controllerProtocolo = ControllerProtocolo.getInstance();
        mountEstatiticas();
    }

    private void mountEstatiticas() {
        totalProtocolos.setText(controllerProtocolo.countProtocolos().toString());
        totalAtendimento.setText(controllerProtocolo.countAtendimento().toString());
        totalAguardandoAtendimento.setText(controllerProtocolo.countAguardandoAtendimento().toString());
        totalAguardandoFeedback.setText(controllerProtocolo.countAguardandoFeedback().toString());
        totalCancelado.setText(controllerProtocolo.countCancelado().toString());
        totalFinalizado.setText(controllerProtocolo.countFizalizado().toString());

    }
}
