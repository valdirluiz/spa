package br.ufsc.ine.aps.controllers.protocolo;

import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Protocolo;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Valdir Luiz on 26/06/2016.
 */
public class DaoProtocolo {


    private static final String SQL_INSERT = "INSERT INTO protocolos (dataCriacao, mensagemLivre, status, area, categoria, idCliente, idResponsavel ) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private Connection bdConnection;

    private static DaoProtocolo ourInstance = new DaoProtocolo();

    public static DaoProtocolo getInstance() {
        return ourInstance;
    }

    private DaoProtocolo() {
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }

    public Integer findAbertosByCliente(Cliente cliente){
        //TODO: implementar
        return 0;
    }

    public Protocolo salvar(Protocolo protocolo) throws Exception {
        PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_INSERT);
        stmt.setDate(1, new java.sql.Date(protocolo.getDataCriacao().getTime()));
        stmt.setString(2, protocolo.getMensagemLivre());
        stmt.setInt(3, protocolo.getStatus().getId());
        stmt.setInt(4, protocolo.getArea().getId());
        stmt.setInt(5, protocolo.getCategoria().getId());
        stmt.setInt(6, protocolo.getCliente().getId());
        if(protocolo.getResponsavel()!=null){
            stmt.setInt(7, protocolo.getResponsavel().getId());
        } else{
            stmt.setNull(7, 1);
        }

        stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if(generatedKeys.next()){
            protocolo.setId(generatedKeys.getInt(1));
        } else{
            throw new Exception("Falha ao salvar interação");
        }
        return protocolo;
    }

    public Integer findSemelhantes(Protocolo protocolo) {
        //TODO: implementar
        return 0;
    }

    public void atualizar(Protocolo protocolo) {
        //TODO: implementar
    }
}
