
package br.ufsc.ine.aps.views.cliente;

import br.ufsc.ine.aps.assets.Assets;
import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.models.Cliente;
import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;

/**
 * Created by Valdir Luiz on 19/06/2016.
 */
public class BotaoDeletar  extends TableCell<Disposer.Record, Boolean> {

    private ViewClienteList view;

    final Image imageDelete = new Image(Assets.deleteImage());
    final Button cellButton = new Button("", new ImageView(imageDelete));

    public BotaoDeletar(ViewClienteList view){
        this.view = view;

        cellButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Cliente cliente  = (Cliente) BotaoDeletar.this.getTableView().getItems().get(BotaoDeletar.this.getIndex());

                view.deletarCliente(cliente);

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
