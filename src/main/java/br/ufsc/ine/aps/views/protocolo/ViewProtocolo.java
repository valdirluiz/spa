package br.ufsc.ine.aps.views.protocolo;


import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.controllers.protocolo.ControllerProtocolo;

import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.views.principal.ViewPrincipal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewProtocolo extends AnchorPane implements Initializable {

    private ViewPrincipal viewPrincipal;
    private ControllerProtocolo controllerProtocolo;
    private ControllerCliente controllerCliente;

    @FXML
    private TextField cpf;

    @FXML
    private TextField nomeCliente;



    public void initialize(URL location, ResourceBundle resources) {
        this.controllerCliente = ControllerCliente.getInstance();
        this.controllerProtocolo = ControllerProtocolo.getInstance();

    }



    @FXML
    private void completaCliente(Event event){
        if(this.cpf.getText().length()==14){
            Cliente cliente = controllerCliente.findByCpf(cpf.getText());
            if(cliente==null){
                nomeCliente.setText("");
                mensagem("Cliente não encontrado");
            } else{
                nomeCliente.setText(cliente.getNome());
            }
        }
    }

    public void mensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR, null, ButtonType.CLOSE);
        alert.setTitle("Cadastro de protocolo");
        if (!mensagem.isEmpty()) alert.setContentText(mensagem);
        alert.showAndWait();
    }


    public void setViewPrincipal(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
}
