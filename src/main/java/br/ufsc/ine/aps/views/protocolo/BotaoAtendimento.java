package br.ufsc.ine.aps.views.protocolo;

import br.ufsc.ine.aps.models.Protocolo;
import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 * Created by Valdir Luiz on 03/07/2016.
 */
public class BotaoAtendimento  extends TableCell<Disposer.Record, Boolean> {

    private ViewProtocoloList view;
    final Button cellButton = new Button("Atender");

    public BotaoAtendimento(ViewProtocoloList view){
        this.view = view;
        cellButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Protocolo protocolo  = (Protocolo) BotaoAtendimento.this.getTableView().getItems().get(BotaoAtendimento.this.getIndex());
                view.abrirTelaDeAtendimento(protocolo);
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
