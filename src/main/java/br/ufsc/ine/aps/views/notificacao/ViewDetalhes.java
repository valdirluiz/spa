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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setNotificacao(Notificacao notificacao) {
        this.tipo.setText(notificacao.getInteracao().getTipo().getDescricao());
        this.mensagem.setText(notificacao.getInteracao().getMensagem());
    }
}
