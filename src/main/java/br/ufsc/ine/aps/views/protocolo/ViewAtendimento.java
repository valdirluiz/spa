package br.ufsc.ine.aps.views.protocolo;

import br.ufsc.ine.aps.controllers.protocolo.ControllerProtocolo;
import br.ufsc.ine.aps.models.Protocolo;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Valdir Luiz on 03/07/2016.
 */
public class ViewAtendimento  implements Initializable {

    private Protocolo protocolo;

    private ControllerProtocolo controllerProtocolo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerProtocolo = ControllerProtocolo.getInstance();
    }


    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
        this.controllerProtocolo.iniciarAtendimento(protocolo);
    }
}
