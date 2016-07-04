package br.ufsc.ine.aps.controllers.protocolo;

import br.ufsc.ine.aps.enuns.Area;
import br.ufsc.ine.aps.enuns.Categoria;
import br.ufsc.ine.aps.enuns.Status;
import br.ufsc.ine.aps.models.Atendente;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.models.Protocolo;
import br.ufsc.ine.aps.utils.Data;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Valdir Luiz on 26/06/2016.
 */
public class DaoProtocolo {


    private static final String SQL_INSERT = "INSERT INTO protocolos (dataCriacao, mensagemLivre, status, area, categoria, idCliente, idResponsavel ) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_IDENTIFICADOR = "update protocolos set identificador = ? where id = ? ;";
    private static final String SQL_COUNT_EM_ABERTO = "SELECT COUNT(*) AS total_aberto FROM  protocolos where status not in (4, 5) and idCliente = ?";
    private static final String SQL_COUNT_SEMELHANTES = "SELECT COUNT(*) AS semelhantes FROM  protocolos  where idCliente = ? and categoria = ? and area = ?";
    private static final String SQL_UPDATE_STATUS = "update protocolos set status = ? where id = ?";
    private static final String SQL_LIST = "SELECT * FROM protocolos";
    private static final String SQL_INICIAR_ATENDIMENTO = "update protocolos set status = ?, idResponsavel =?, dataInicioExecucao = ? where id = ?";
    private static final String SQL_FINALIZAR_ATENDIMENTO = "update protocolos set status = ?,  dataFimExecucao = ?, respota=? where id = ?";
    private static final String SQL_BUSCA_PROTOCOLOS_OPERADOR = "select count(*) from protocolos where idResponsavel = ? and (status = 1 or status=2)";
    private static final String SQL_INSERIR_FEEDBACK = "update protocolos set status = 5, feedback = ? where id = ?";

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
        stmt.setDate(1, new java.sql.Date(protocolo.getDataCriacao().getTimeInMillis()));
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

    public void updateStatus(Protocolo protocolo) {
        try{
            PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_UPDATE_STATUS);
            stmt.setInt(1, protocolo.getStatus().getId());
            stmt.setInt(2, protocolo.getId());
            stmt.executeUpdate();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void iniciarAtendimento(Protocolo protocolo) throws Exception {
        try {
            PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_INICIAR_ATENDIMENTO);
            stmt.setInt(1, protocolo.getStatus().getId());
            stmt.setInt(2, protocolo.getResponsavel().getId());
            stmt.setDate(3, new Date(protocolo.getDataInicioExecucao().getTime()));
            stmt.setInt(4, protocolo.getId());
            stmt.executeUpdate();
            stmt.close();
        }  catch (Exception e){
            e.printStackTrace();
        }

    }

    public void finalizarAtendimento(Protocolo protocolo) throws SQLException {
        PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_FINALIZAR_ATENDIMENTO);
        stmt.setInt(1, protocolo.getStatus().getId());
        stmt.setDate(2, new Date(protocolo.getDataFimExecucao().getTime()));
        stmt.setString(3, protocolo.getResposta());
        stmt.setInt(4, protocolo.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Protocolo> list(){
        List<Protocolo> protocolos = new ArrayList<>();
        try {
            PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_LIST);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Protocolo protocolo = new Protocolo();

                protocolo.setId(rs.getInt(1));
                protocolo.setDataCriacao(Data.dateToCalendar(rs.getDate(2)));
                protocolo.setDataFimExecucao(rs.getDate(3));
                protocolo.setDataInicioExecucao(rs.getDate(4));
                protocolo.setFeedback(rs.getString(5));
                protocolo.setIdentificador(rs.getString(6));
                protocolo.setMensagemLivre(rs.getString(7));

                protocolo.setResposta(rs.getString(8));
                protocolo.setStatus(Status.getById(rs.getInt(9)));
                protocolo.setArea(Area.getById(rs.getInt(10)));
                protocolo.setCategoria(Categoria.getById(rs.getInt(11)));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt(12));
                protocolo.setCliente(cliente);

                Pessoa atendente = new Atendente();
                atendente.setId(rs.getInt(13));
                protocolo.setResponsavel(atendente);

                protocolos.add(protocolo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return protocolos;
    }


    public Integer buscaProtocolosDoOperador(Integer idOperador) {
        Integer count = null;
        try {
            PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_BUSCA_PROTOCOLOS_OPERADOR);
            stmt.setInt(1,idOperador);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void inserirFeedback(Protocolo protocolo) throws Exception {
        PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_INSERIR_FEEDBACK);
        stmt.setString(1, protocolo.getFeedback());
        stmt.setInt(2, protocolo.getId());
        stmt.executeUpdate();
        stmt.close();
    }
}
