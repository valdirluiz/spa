package br.ufsc.ine.aps.views.principal;

import br.ufsc.ine.aps.views.protocolo.ViewProtocolo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Valdir Luiz on 12/06/2016.
 */
public class ViewPrincipal implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleCadastroProtocoloButtonAction(ActionEvent event) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(ViewProtocolo.class.getResource("protocolo.fxml"));
            stage.setTitle("Home");
            stage.setScene(new Scene(root, 800, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
