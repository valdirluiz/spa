package br.ufsc.ine.aps.views.protocolo;

import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.models.Protocolo;
import br.ufsc.ine.aps.views.pessoa.BotaoDeletar;
import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

/**
 * Created by Valdir Luiz on 03/07/2016.
 */
public class BotaoCancelarProtocolo extends TableCell<Disposer.Record, Boolean> {

    private ViewProtocoloList view;
    final Button cellButton = new Button("Cancelar");

    public BotaoCancelarProtocolo(ViewProtocoloList view){
        this.view = view;

        cellButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Protocolo protocolo  = (Protocolo) BotaoCancelarProtocolo.this.getTableView().getItems().get(BotaoCancelarProtocolo.this.getIndex());
                view.cancelarProtocolo(protocolo);
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
