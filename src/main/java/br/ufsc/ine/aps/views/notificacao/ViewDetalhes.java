package br.ufsc.ine.aps.views.notificacao;

import br.ufsc.ine.aps.models.Notificacao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Valdir Luiz on 02/07/2016.
 */
public class ViewDetalhes implements Initializable {

    @FXML
    private TextField tipo;
    @FXML
    private TextArea mensagem;

    private Notificacao notificacao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tipo.setText(this.notificacao.getInteracao().getTipo().getDescricao());
    }

    public void setNotificacao(Notificacao notificacao) {
        this.notificacao = notificacao;
    }
}
