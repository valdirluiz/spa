package br.ufsc.ine.aps.views.protocolo;

import br.ufsc.ine.aps.controllers.login.Autenticador;
import br.ufsc.ine.aps.controllers.protocolo.ControllerProtocolo;
import br.ufsc.ine.aps.enuns.Status;
import br.ufsc.ine.aps.exceptions.ProtocoloJaCancelado;
import br.ufsc.ine.aps.exceptions.SemRespostaPreenchida;
import br.ufsc.ine.aps.exceptions.StatusEmAndamento;
import br.ufsc.ine.aps.models.Protocolo;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Valdir Luiz on 03/07/2016.
 */
public class ViewAtendimento  implements Initializable {

    private Protocolo protocolo;

    private ControllerProtocolo controllerProtocolo;
    private Autenticador autenticador;

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
    @FXML
    private Button btnAtendimento;
    @FXML
    private Button btnFinalizar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnFeedback;
    @FXML
    private Label feedbackLabel;
    @FXML
    private TextArea feedback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controllerProtocolo = ControllerProtocolo.getInstance();
        this.autenticador = Autenticador.getInstance();
    }


    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
        this.cliente.setText(protocolo.getCliente().getNome());
        this.status.setText(protocolo.getStatusDescricao());
        this.area.setText(protocolo.getArea().getDescricao());
        this.categoria.setText(protocolo.getCategoria().getDescricao());
        this.resposta.setText(protocolo.getResposta());
        this.descricao.setText(protocolo.getMensagemLivre());
        this.btnAtendimento.setDisable(this.controllerProtocolo.desabilitarInicializar(autenticador.getUsuarioLogado().getId())
                || !protocolo.getStatus().equals(Status.AGUARDANDO_ATENDIMENTO));

        this.btnFinalizar.setDisable(!protocolo.getStatus().equals(Status.EM_ATENDIMENTO));

        this.btnFeedback.setVisible(exibeFeedback(protocolo));
        this.feedbackLabel.setVisible(exibeFeedback(protocolo));
        this.feedback.setVisible(exibeFeedback(protocolo));
        this.btnCancelar.setVisible(protocolo.getStatus().equals(Status.AGUARDANDO_ATENDIMENTO));


    }

    private boolean exibeFeedback(Protocolo protocolo) {
        return protocolo.getStatus().equals(Status.AGUARDANDO_FEEDBACK) && autenticador.getUsuarioLogado().getId().equals(protocolo.getCliente().getId());
    }

    @FXML
    public void finalizar(Event event){
        try {
            protocolo.setResposta(this.resposta.getText());
            this.controllerProtocolo.finalizarProtocolo(protocolo);
            atualizaView();
            this.mensagem("Atendimento finalizado com sucesso!", Alert.AlertType.CONFIRMATION);
        } catch (SemRespostaPreenchida e){
            mensagem("Preencha uma resposta!", Alert.AlertType.ERROR);
        } catch (Exception e){
            e.printStackTrace();
            this.mensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void atualizaView() {
        Protocolo protocoloAtualizado = this.controllerProtocolo.findById(protocolo.getId());
        if(protocoloAtualizado!=null){
            this.setProtocolo(protocoloAtualizado);
        }
    }

    @FXML
    public void iniciarAtendimento(Event event){
        try {
            this.controllerProtocolo.iniciarAtendimento(protocolo);
            this.btnCancelar.setVisible(false);
            this.btnAtendimento.setDisable(false);
            atualizaView();
            mensagem("Atendimento iniciado com sucesso!", Alert.AlertType.CONFIRMATION);
        }
        catch (Exception e){
            mensagem("Falha ao iniciar atendimento!", Alert.AlertType.ERROR);
        }
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
