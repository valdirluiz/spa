package br.ufsc.ine.aps.controllers.interacao;

import br.ufsc.ine.aps.enuns.TipoInteracao;
import br.ufsc.ine.aps.models.Interacao;
import br.ufsc.ine.aps.models.Notificacao;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Created by Valdir Luiz on 25/06/2016.
 */
public class DaoInteracao {

    private static final String SQL_INSERT = "insert into interacoes(data, mensagem, id_usuario, tipo, id_protocolo) values(?, ?, ?, ?, ?);";


    private Connection bdConnection;

    private static DaoInteracao ourInstance = new DaoInteracao();

    public static DaoInteracao getInstance() {
        return ourInstance;
    }

    private DaoInteracao() {
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }
    
    public Interacao save(Interacao interacao) throws Exception {
        PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_INSERT);
        stmt.setDate(1, new java.sql.Date(interacao.getData().getTime()));
        stmt.setString(2, interacao.getMensagem());
        stmt.setInt(3, interacao.getUsuario().getId());
        stmt.setInt(4, interacao.getTipo().getId());
        stmt.setInt(5, interacao.getProtocolo().getId());
        stmt.executeUpdate();
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if(generatedKeys.next()){
            interacao.setId(generatedKeys.getInt(1));
        } else{
            throw new Exception("Falha ao salvar interação");
        }
        return interacao;
    }


}
