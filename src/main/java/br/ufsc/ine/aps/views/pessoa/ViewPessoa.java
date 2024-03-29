package br.ufsc.ine.aps.views.pessoa;


import br.ufsc.ine.aps.models.Pessoa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public abstract  class ViewPessoa {

    @FXML
    protected TextField cpf;
    @FXML
    protected TextField nome;
    @FXML
    protected PasswordField senha;
    @FXML
    protected TextField email;
    @FXML
    protected TextField telefone;

    public void mensagem(String title, String header, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);

        if (!title.isEmpty()) alert.setTitle(title);
        if (!header.isEmpty()) alert.setHeaderText(header);
        if (!message.isEmpty()) alert.setContentText(message);

        alert.showAndWait();
    }

    public void mensagem(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.NONE, null, ButtonType.CLOSE);

        if (!title.isEmpty()) alert.setTitle(title);
        if (!message.isEmpty()) alert.setContentText(message);

        alert.showAndWait();
    }

    public void resetForm() {
        this.cpf.clear();
        this.nome.clear();
        this.email.clear();
        this.telefone.clear();
        this.senha.clear();
    }


}
