package br.ufsc.ine.aps.views.login;


import br.ufsc.ine.aps.controllers.login.Autenticador;
import br.ufsc.ine.aps.views.principal.ViewPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewLogin implements Initializable {

    private Autenticador autenticador;
    @FXML
    private TextField cpfField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private ComboBox<String> perfil;
    @FXML
    private Pane loginPanel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.autenticador = Autenticador.getInstance();
    }

    @FXML
    private void efetuarLogin(ActionEvent event) {
        String perfil = this.perfil.getSelectionModel().getSelectedItem();
        Optional<String> notificacao = this.autenticador.
                                                efetuarLogin(this.cpfField.getText(), this.senhaField.getText(), perfil);
        if(notificacao.isPresent()){
            this.exibeNotificacao(notificacao.get());
        } else{
            this.abrirTelaPrincipal();
            Stage loginStage = (Stage) this.loginPanel.getScene().getWindow();
            loginStage.close();
        }
    }

    private void abrirTelaPrincipal(){
        try {
            Screen screen = Screen.getPrimary();
            Rectangle2D janela = screen.getVisualBounds();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(ViewPrincipal.class.getResource("home.fxml"));
            stage.setTitle("Home");
            stage.setScene(new Scene(root));
            stage.setX(janela.getMinX());
            stage.setY(janela.getMinY());
            stage.setWidth(janela.getWidth());
            stage.setHeight(janela.getHeight());
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void exibeNotificacao(String notificacao){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText("Aconteceu uma falha:");
        alert.setContentText(notificacao);
        alert.showAndWait();

    }
}
