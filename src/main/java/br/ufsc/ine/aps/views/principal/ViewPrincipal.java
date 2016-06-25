package br.ufsc.ine.aps.views.principal;

import br.ufsc.ine.aps.controllers.login.Autenticador;
import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.Autenticavel;
import br.ufsc.ine.aps.views.cliente.ViewCliente;
import br.ufsc.ine.aps.views.cliente.ViewClienteList;
import br.ufsc.ine.aps.views.funcionario.ViewFuncionario;
import br.ufsc.ine.aps.views.funcionario.ViewFuncionarioList;
import br.ufsc.ine.aps.views.protocolo.ViewProtocolo;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;

import javafx.scene.control.Menu;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

public class ViewPrincipal implements Initializable {

    @FXML
    private AnchorPane pageTitle;

    @FXML
    private AnchorPane pageContent;

    @FXML
    private Menu menuFuncionarios;

    @FXML
    private Menu menuClientes;

    @FXML
    private MenuItem menuCadProtocolo;

    private Autenticavel usuarioLogado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.usuarioLogado = Autenticador.getInstance().getUsuarioLogado();
        this.showMenuFuncionarios();
        this.showMenuClientes();
        this.showMenuCadastrarProtocolo();
    }



    @FXML
    private void handleCadastroProtocoloButtonAction(ActionEvent event) {
        try {
            AnchorPane content = new AnchorPane();
            Parent conteudoDaView =  FXMLLoader.load(ViewProtocolo.class.getResource("cadastrar.fxml"));
            content.getChildren().setAll(conteudoDaView);

            AnchorPane title = new AnchorPane();
            Parent titleWrapper = FXMLLoader.load(ViewProtocolo.class.getResource("_titulo.fxml"));
            title.getChildren().setAll(titleWrapper);

            this.atualizaConteudo(title, content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleClientesConsultarAction(ActionEvent ev) {
        try {
            AnchorPane content = new AnchorPane();
            FXMLLoader loader = new FXMLLoader();
            Parent page =  loader.load(ViewCliente.class.getResourceAsStream("listar.fxml"));
            ViewClienteList controller =  loader.getController();
            controller.setViewPrincipal(this);
            content.getChildren().setAll(page);

            AnchorPane title = new AnchorPane();
            Parent titleWrapper = FXMLLoader.load(ViewCliente.class.getResource("_titulo.fxml"));
            title.getChildren().setAll(titleWrapper);

            this.atualizaConteudo(title, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClientesCadastrarAction(ActionEvent ev) {
        try {
            AnchorPane content = new AnchorPane();
            Parent page =  FXMLLoader.load(ViewCliente.class.getResource("cadastrar.fxml"));
            content.getChildren().setAll(page);

            AnchorPane title = new AnchorPane();
            Parent titleWrapper = FXMLLoader.load(ViewCliente.class.getResource("_titulo.fxml"));
            title.getChildren().setAll(titleWrapper);

            this.atualizaConteudo(title, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFuncionariosCadastrarAction(ActionEvent ev) {
        try {
            AnchorPane content = new AnchorPane();
            Parent page =  FXMLLoader.load(ViewFuncionario.class.getResource("cadastrar.fxml"));
            content.getChildren().setAll(page);

            AnchorPane title = new AnchorPane();
            Parent titleWrapper = FXMLLoader.load(ViewFuncionario.class.getResource("_titulo.fxml"));
            title.getChildren().setAll(titleWrapper);

            this.atualizaConteudo(title, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFuncionariosListarAction(ActionEvent ev) {
        try {
            AnchorPane content = new AnchorPane();
            FXMLLoader loader = new FXMLLoader();
            Parent page =  loader.load(ViewFuncionario.class.getResourceAsStream("listar.fxml"));
            ViewFuncionarioList controller =  loader.getController();
            controller.setViewPrincipal(this);
            content.getChildren().setAll(page);

            AnchorPane title = new AnchorPane();
            Parent titleWrapper = FXMLLoader.load(ViewFuncionario.class.getResource("_titulo.fxml"));
            title.getChildren().setAll(titleWrapper);

            this.atualizaConteudo(title, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizaConteudo(AnchorPane title, AnchorPane conteudo) {
        pageContent.getChildren().clear();
        pageContent.getChildren().add(conteudo);

        pageTitle.getChildren().clear();
        pageTitle.getChildren().add(title);
    }

    public void atualizaConteudo(AnchorPane conteudo) {
        pageContent.getChildren().clear();
        pageContent.getChildren().add(conteudo);
    }


    private void showMenuFuncionarios(){
        this.menuFuncionarios.setVisible(this.usuarioLogado.getTipoUsuario().equals(TipoUsuario.GERENTE));
    }

    private void showMenuClientes(){
        this.menuClientes.setVisible(this.usuarioLogado.getTipoUsuario().equals(TipoUsuario.GERENTE)
                || this.usuarioLogado.getTipoUsuario().equals(TipoUsuario.ATENDENTE) || this.usuarioLogado.getTipoUsuario().equals(TipoUsuario.OPERADOR_SUPORTE) );
    }

    private void showMenuCadastrarProtocolo() {
        this.menuCadProtocolo.setVisible(this.usuarioLogado.getTipoUsuario().equals(TipoUsuario.GERENTE)
                || this.usuarioLogado.getTipoUsuario().equals(TipoUsuario.ATENDENTE));
    }

}
