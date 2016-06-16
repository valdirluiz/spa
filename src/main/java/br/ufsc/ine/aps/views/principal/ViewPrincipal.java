package br.ufsc.ine.aps.views.principal;

import br.ufsc.ine.aps.views.cliente.ViewCliente;
import br.ufsc.ine.aps.views.protocolo.ViewProtocolo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class ViewPrincipal implements Initializable {

    @FXML
    private AnchorPane conteudoDinamico;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleCadastroProtocoloButtonAction(ActionEvent event) {
        try {
            AnchorPane pane = new AnchorPane();
            Parent conteudoDaView =  FXMLLoader.load(ViewProtocolo.class.getResource("protocolo.fxml"));
            pane.getChildren().setAll(conteudoDaView);
            this.atualizaConteudo(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void atualizaConteudo(AnchorPane conteudo) {
        conteudoDinamico.getChildren().clear();
        conteudoDinamico.getChildren().add(conteudo);

    }

    @FXML
    private void handleClientesButtonAction(ActionEvent ev) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(ViewCliente.class.getResource("cadastro_cliente.fxml"));
            stage.setTitle("Clientes");
            stage.setScene(new Scene(root, 800, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
