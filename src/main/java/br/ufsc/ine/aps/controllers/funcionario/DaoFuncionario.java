package br.ufsc.ine.aps.controllers.funcionario;

import br.ufsc.ine.aps.controllers.pessoa.PessoaDao;
import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.*;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.*;
import java.util.Optional;


public class DaoFuncionario extends PessoaDao {

    private static final String FIND_OPERADOR_LIVRE = "select pessoas.id as id_responsavel from pessoas where not exists (select * from protocolos where protocolos.idResponsavel is not null and status not in (4, 5) and  protocolos.idResponsavel =  id_responsavel ) and tipo_usuario = 3 limit 1";

    private Connection bdConnection;

    private static DaoFuncionario ourInstance = new DaoFuncionario();

    public static DaoFuncionario getInstance() {
        return ourInstance;
    }

    private DaoFuncionario() {
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }


    public Gerente findGerente()  {
        Gerente gerente = new Gerente();
        try {
            ResultSet rs;
            PreparedStatement stmt = null;
            stmt = this.bdConnection.prepareStatement("select * from pessoas where tipo_usuario = ?");
            stmt.setInt(1, TipoUsuario.GERENTE.getId());
            rs = stmt.executeQuery();
            if(rs.next()){
                gerente.setId(rs.getInt("id"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return gerente;
    }




    public Operador findOperadorDisponivel(){
        Operador operador = null;
        try {

            PreparedStatement stmt = this.bdConnection.prepareStatement(FIND_OPERADOR_LIVRE);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                operador = new Operador();
                operador.setId(rs.getInt("id_responsavel"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return operador;
    }

    @Override
    public Connection getConnection() {
        return this.bdConnection;
    }


}
