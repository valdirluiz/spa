package br.ufsc.ine.aps.views.login;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private TextField cpfField;

    @FXML
    private TextField senhaField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Iniciar aqui controladores necessarios para efetuar login
    }



    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        System.out.println("CPF: "  + this.cpfField.getText());
        System.out.println("SENHA: "  + this.cpfField.getText());
    }



}
