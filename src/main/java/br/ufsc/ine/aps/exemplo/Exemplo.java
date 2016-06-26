package br.ufsc.ine.aps.exemplo;

import br.ufsc.ine.aps.views.login.ViewLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Exemplo  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("exemplo.fxml"));
        primaryStage.setTitle("Exemplos");
        primaryStage.setScene(new Scene(root, 900, 875));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
