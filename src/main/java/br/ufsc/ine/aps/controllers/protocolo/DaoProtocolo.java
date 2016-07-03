package br.ufsc.ine.aps.controllers.protocolo;

import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Protocolo;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


/**
 * Created by Valdir Luiz on 26/06/2016.
 */
public class DaoProtocolo {


    private static final String SQL_INSERT = "INSERT INTO protocolos (dataCriacao, mensagemLivre, status, area, categoria, idCliente, idResponsavel ) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_IDENTIFICADOR = "update protocolos set identificador = ? where id = ? ;";
    private static final String SQL_COUNT_EM_ABERTO = "SELECT COUNT(*) AS total_aberto FROM  protocolos where status not in (4, 5) and idCliente = ?";
    private static final String SQL_COUNT_SEMELHANTES = "SELECT COUNT(*) AS semelhantes FROM  protocolos  where idCliente = ? and categoria = ? and area = ?";
    private static final String SQL_CANCELAR = "update protocolos set status = ? where id = ?";

    private Connection bdConnection;

    private static DaoProtocolo ourInstance = new DaoProtocolo();

    public static DaoProtocolo getInstance() {
        return ourInstance;
    }

    private DaoProtocolo() {
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }

    public Integer findAbertosByCliente(Cliente cliente) {
        Integer count = null;
        try {

            PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_COUNT_EM_ABERTO);
            stmt.setInt(1, cliente.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("total_aberto");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public Protocolo salvar(Protocolo protocolo) throws Exception {
        PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_INSERT);
        stmt.setDate(1, new java.sql.Date(protocolo.getDataCriacao().getTime()));
        stmt.setString(2, protocolo.getMensagemLivre());
        stmt.setInt(3, protocolo.getStatus().getId());
        stmt.setInt(4, protocolo.getArea().getId());
        stmt.setInt(5, protocolo.getCategoria().getId());
        stmt.setInt(6, protocolo.getCliente().getId());
        if (protocolo.getResponsavel() != null) {
            stmt.setInt(7, protocolo.getResponsavel().getId());
        } else {
            stmt.setNull(7, 1);
        }

        stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            protocolo.setId(generatedKeys.getInt(1));
        } else {
            throw new Exception("Falha ao salvar interação");
        }
        stmt.close();
        return protocolo;
    }

    public void savarIdentificador(Protocolo protocolo){
        try {
            PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_UPDATE_IDENTIFICADOR);
            stmt.setString(1, protocolo.getIdentificador());
            stmt.setInt(2, protocolo.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer findSemelhantes(Protocolo protocolo) {
        Integer count = null;
        try {
            PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_COUNT_SEMELHANTES);
            stmt.setInt(1, protocolo.getCliente().getId());
            stmt.setInt(2, protocolo.getCategoria().getId());
            stmt.setInt(3, protocolo.getArea().getId());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("semelhantes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Protocolo> buscaProtocolos() {
        return null;
    }

    public void cancelar(Protocolo protocolo) {
        try{
            PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_CANCELAR);
            stmt.setInt(1, protocolo.getStatus().getId());
            stmt.setInt(1, protocolo.getId());
            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
