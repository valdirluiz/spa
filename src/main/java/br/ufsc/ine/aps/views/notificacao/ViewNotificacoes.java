package br.ufsc.ine.aps.views.notificacao;

import br.ufsc.ine.aps.controllers.login.Autenticador;
import br.ufsc.ine.aps.controllers.notificacao.ControllerNotificacao;
import br.ufsc.ine.aps.models.Notificacao;

import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Valdir Luiz on 02/07/2016.
 */
public class ViewNotificacoes implements Initializable{

    private Autenticador autenticador;
    private ControllerNotificacao controllerNotificacao;
    private List<Notificacao> notificacoes;

    @FXML
    private TableView<Notificacao> notificacaoTableView;

    @FXML
    private TableColumn columnVer;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.autenticador = Autenticador.getInstance();
        this.controllerNotificacao = ControllerNotificacao.getInstance();
        this.notificacoes = controllerNotificacao.findByUsuario(this.autenticador.getUsuarioLogado());
        this.notificacaoTableView.setItems(FXCollections.observableArrayList(this.notificacoes));
        this.insereBotaoDetalhes();
    }

    public void exibirDetalhes(Notificacao notificacao) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("detalhes.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            ViewDetalhes controller = fxmlLoader.<ViewDetalhes>getController();
            controller.setNotificacao(notificacao);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 500, 500));
            stage.setTitle("Detalhes");
            //stage.initOwner(pane.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void insereBotaoDetalhes(){
        columnVer.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {
                    @Override
                    public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                        return new BotaoVisualizar(ViewNotificacoes.this);
                    }
                });
    }
}
