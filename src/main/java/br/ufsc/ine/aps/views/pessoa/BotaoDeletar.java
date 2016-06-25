
package br.ufsc.ine.aps.views.pessoa;

import br.ufsc.ine.aps.assets.Assets;
import br.ufsc.ine.aps.models.Pessoa;
import com.sun.prism.impl.Disposer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BotaoDeletar  extends TableCell<Disposer.Record, Boolean> {

    private ViewPessoaList view;

    final Image imageDelete = new Image(Assets.deleteImage());
    final Button cellButton = new Button("", new ImageView(imageDelete));

    public BotaoDeletar(ViewPessoaList view){
        this.view = view;

        cellButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Pessoa pessoa  = (Pessoa) BotaoDeletar.this.getTableView().getItems().get(BotaoDeletar.this.getIndex());
                view.deletarPessoa(pessoa);
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
