package br.ufsc.ine.aps.views.notificacao;

import br.ufsc.ine.aps.models.Notificacao;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.views.pessoa.BotaoDeletar;
import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

/**
 * Created by Valdir Luiz on 02/07/2016.
 */
public class BotaoVisualizar  extends TableCell<Disposer.Record, Boolean> {

    private ViewNotificacoes view;

    final Button cellButton = new Button("Ver");

    public BotaoVisualizar(ViewNotificacoes view){

        cellButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Notificacao notificacao  = (Notificacao) BotaoVisualizar.this.getTableView().getItems().get(BotaoVisualizar.this.getIndex());
                view.exibirDetalhes(notificacao);
            }
        });

    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }
    }
}
