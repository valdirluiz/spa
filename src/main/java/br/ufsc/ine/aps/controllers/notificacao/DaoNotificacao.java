package br.ufsc.ine.aps.controllers.notificacao;

import br.ufsc.ine.aps.models.Notificacoes;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Valdir Luiz on 26/06/2016.
 */

public class DaoNotificacao {

    private Connection bdConnection;

    private static final String SQL_INSERT = "insert into notificacoes(visualizado, id_usuario, id_interacao) values (?, ?, ?);";

    private static DaoNotificacao ourInstance = new DaoNotificacao();

    public static DaoNotificacao getInstance() {
        return ourInstance;
    }

    private DaoNotificacao() {
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }

    public void save(Notificacoes notificaao) throws Exception {
        PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_INSERT);
        stmt.setBoolean(1, notificaao.isVisualizado());
        stmt.setInt(2, notificaao.getUsuario().getId());
        stmt.setInt(3, notificaao.getInteracao().getId());
        stmt.executeUpdate();
    }
}
