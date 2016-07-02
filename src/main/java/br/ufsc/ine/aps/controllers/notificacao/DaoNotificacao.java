package br.ufsc.ine.aps.controllers.notificacao;

import br.ufsc.ine.aps.controllers.interacao.DaoInteracao;
import br.ufsc.ine.aps.enuns.TipoInteracao;
import br.ufsc.ine.aps.models.Interacao;
import br.ufsc.ine.aps.models.Notificacao;
import br.ufsc.ine.aps.models.Protocolo;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valdir Luiz on 26/06/2016.
 */

public class DaoNotificacao {



    private Connection bdConnection;
    private DaoInteracao daoInteracao;

    private static final String FIND_BY_USER = "select notificacoes.* , interacoes.*, protocolos.*   from notificacoes" +
            "  join interacoes on notificacoes.id_interacao = interacoes.id" +
            "  join protocolos  on interacoes.id_protocolo = protocolos.id" +
            "  where notificacoes.id_usuario = ?";

    private static final String SQL_INSERT = "insert into notificacoes(visualizado, id_usuario, id_interacao) values (?, ?, ?);";

    private static DaoNotificacao ourInstance = new DaoNotificacao();

    public static DaoNotificacao getInstance() {
        return ourInstance;
    }

    private DaoNotificacao() {
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
        this.daoInteracao = DaoInteracao.getInstance();
    }

    public void save(Notificacao notificaao) throws Exception {
        PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_INSERT);
        stmt.setBoolean(1, notificaao.isVisualizado());
        stmt.setInt(2, notificaao.getUsuario().getId());
        stmt.setInt(3, notificaao.getInteracao().getId());
        stmt.executeUpdate();
    }

    public List<Notificacao> findByUsuario(Integer idUsuario) {
        List<Notificacao> notificacaos = new ArrayList<>();
        try{
            ResultSet rs = null;
            PreparedStatement stmt = bdConnection.prepareStatement(FIND_BY_USER);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
            Notificacao notificacao = null;

            while ( rs.next() ) {
                notificacao = new Notificacao();
                notificacao.setVisualizado(rs.getBoolean("visualizado"));
                Interacao interacao = new Interacao();
                interacao.setTipo(TipoInteracao.findById(rs.getInt("tipo")));
                interacao.setData(rs.getDate("data"));
                Protocolo protocolo = new Protocolo();
                protocolo.setIdentificador(rs.getString("identificador"));
                interacao.setProtocolo(protocolo);
                notificacao.setInteracao(interacao);
                notificacaos.add(notificacao);
            }
            rs.close();
            stmt.close();


        } catch (Exception e){
            e.printStackTrace();
        }

        return notificacaos;
    }
}
