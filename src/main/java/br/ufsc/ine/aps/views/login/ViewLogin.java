package br.ufsc.ine.aps.views.login;


import br.ufsc.ine.aps.controllers.login.Autenticador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewLogin implements Initializable {

    private Autenticador autenticador;

    @FXML
    private TextField cpfField;

    @FXML
    private PasswordField senhaField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.autenticador = Autenticador.getInstance();
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        Optional<String> notificacao = this.autenticador.efetuarLogin(this.cpfField.getText(), this.senhaField.getText());
        if(notificacao.isPresent()){
            this.exibeNotificacao(notificacao.get());
        } else{
            this.abrirTelaPrincipal();
        }
    }

    private void abrirTelaPrincipal(){
        // TODO: 12/06/2016 implementar logica para abrir tela principal
    }

    private void exibeNotificacao(String notificacao){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erro");
        alert.setHeaderText("Aconteceu uma falha:");
        alert.setContentText(notificacao);
        alert.showAndWait();

    }



}
