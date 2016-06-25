package br.ufsc.ine.aps.views.protocolo;


import br.ufsc.ine.aps.controllers.protocolo.ControllerProtocolo;
import br.ufsc.ine.aps.views.principal.ViewPrincipal;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewProtocolo extends AnchorPane implements Initializable {

    private ViewPrincipal viewPrincipal;
    private ControllerProtocolo controllerProtocolo;

    public void initialize(URL location, ResourceBundle resources) {
        this.controllerProtocolo = ControllerProtocolo.getInstance();
    }

    public void setViewPrincipal(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
}
