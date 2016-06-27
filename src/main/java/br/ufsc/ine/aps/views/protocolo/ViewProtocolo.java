package br.ufsc.ine.aps.views.protocolo;


import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.controllers.protocolo.ControllerProtocolo;

import br.ufsc.ine.aps.exceptions.LimiteProtocoloExedido;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.views.principal.ViewPrincipal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    @FXML
    private ComboBox<String> categoria;

    @FXML
    private ComboBox<String> area;

    @FXML
    private TextArea descricao;

    private  Cliente cliente;



    public void initialize(URL location, ResourceBundle resources) {
        this.controllerCliente = ControllerCliente.getInstance();
        this.controllerProtocolo = ControllerProtocolo.getInstance();

    }

    @FXML
    private void enviarSolicitacao(Event event){
        try {
            String notificacao = this.controllerProtocolo.adicionar(cliente,
                    categoria.getSelectionModel().getSelectedItem(),
                    area.getSelectionModel().getSelectedItem(),
                    descricao.getText());
            this.mensagem(notificacao, Alert.AlertType.CONFIRMATION);
        } catch (LimiteProtocoloExedido e){
            this.mensagem("Cliente já possui 3 protocolos em aberto", Alert.AlertType.ERROR);
        } catch (Exception e){
            this.mensagem("Falha ao cadastrar protocolo", Alert.AlertType.CONFIRMATION);
        }


    }


    @FXML
    private void completaCliente(Event event){
        if(this.cpf.getText().length()==14){
            Cliente cliente = controllerCliente.findByCpf(cpf.getText());
            if(cliente==null){
                nomeCliente.setText("");
                mensagem("Cliente não encontrado", Alert.AlertType.ERROR);
            } else{
                this.cliente = cliente;
                nomeCliente.setText(cliente.getNome());
            }
        }
    }

    public void mensagem(String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo, null, ButtonType.CLOSE);
        alert.setTitle("Cadastro de protocolo");
        if (!mensagem.isEmpty()) alert.setContentText(mensagem);
        alert.showAndWait();
    }


    public void setViewPrincipal(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }
}
