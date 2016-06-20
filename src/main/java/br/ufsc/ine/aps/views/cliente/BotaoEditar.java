package br.ufsc.ine.aps.views.cliente;

import br.ufsc.ine.aps.assets.Assets;
import br.ufsc.ine.aps.models.Cliente;
import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by rafaelignacio on 19/06/16.
 */
public class BotaoEditar extends TableCell<Disposer.Record, Boolean> {
    private ViewClienteList view;

    final Image imageEdit = new Image(Assets.editImage());
    final Button cellButton = new Button("", new ImageView(imageEdit));

    public BotaoEditar(ViewClienteList view){
        this.view = view;

        cellButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Cliente cliente  = (Cliente) BotaoEditar.this.getTableView().getItems().get(BotaoEditar.this.getIndex());

                view.abreTelaEdicao(cliente);
            }
        });

    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }
    }}
