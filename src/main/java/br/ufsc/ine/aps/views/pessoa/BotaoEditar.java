package br.ufsc.ine.aps.views.pessoa;

import br.ufsc.ine.aps.assets.Assets;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.views.cliente.ViewClienteList;
import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BotaoEditar extends TableCell<Disposer.Record, Boolean> {

    private ListPessoaView view;

    final Image imageEdit = new Image(Assets.editImage());
    final Button cellButton = new Button("", new ImageView(imageEdit));

    public BotaoEditar(ListPessoaView view){
        this.view = view;

        cellButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Pessoa pessoa  = (Pessoa) BotaoEditar.this.getTableView().getItems().get(BotaoEditar.this.getIndex());
                view.abreTelaEdicao(pessoa);
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
