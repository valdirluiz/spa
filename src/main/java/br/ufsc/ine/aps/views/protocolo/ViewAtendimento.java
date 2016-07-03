package br.ufsc.ine.aps.views.protocolo;

import br.ufsc.ine.aps.controllers.protocolo.ControllerProtocolo;
import br.ufsc.ine.aps.exceptions.ProtocoloJaCancelado;
import br.ufsc.ine.aps.exceptions.StatusEmAndamento;
import br.ufsc.ine.aps.models.Protocolo;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Valdir Luiz on 03/07/2016.
 */
public class ViewAtendimento  implements Initializable {

    private Protocolo protocolo;

    private ControllerProtocolo controllerProtocolo;

    @FXML
    private TextField cliente;
    @FXML
    private TextField status;
    @FXML
    private TextField area;
    @FXML
    private TextField categoria;
    @FXML
    private TextArea descricao;
    @FXML
    private TextArea resposta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controllerProtocolo = ControllerProtocolo.getInstance();
    }


    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
        this.cliente.setText(protocolo.getCliente().getNome());
        this.status.setText(protocolo.getStatusDescricao());
        this.area.setText(protocolo.getArea().getDescricao());
        this.categoria.setText(protocolo.getCategoria().getDescricao());
        this.descricao.setText(protocolo.getMensagemLivre());

    }

    @FXML
    public void finalizar(Event event){

    }

    @FXML
    public void iniciarAtendimento(Event event){
        this.controllerProtocolo.iniciarAtendimento(protocolo);
    }

    @FXML
    public void cancelarProtocolo(Event event) {
        try {
            this.controllerProtocolo.cancelarProtocolo(this.protocolo);
            this.mensagem("Protocolo cancelado com sucesso.", Alert.AlertType.CONFIRMATION );
        } catch (ProtocoloJaCancelado e){
            this.mensagem("Protocolo já cancelado.",Alert.AlertType.ERROR );
        } catch (StatusEmAndamento e){
            this.mensagem("Não é possível, protocolo já esta em execução.",Alert.AlertType.ERROR );
        } catch (Exception e){
            this.mensagem("Falha ao updateStatus o protocolo.",Alert.AlertType.ERROR );
            e.printStackTrace();
        }

    }

    public void mensagem(String mensagem, Alert.AlertType type) {
        Alert alert = new Alert(type, null, ButtonType.CLOSE);
        alert.setTitle("Remoção de Funcionários");
        if (!mensagem.isEmpty()) alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
