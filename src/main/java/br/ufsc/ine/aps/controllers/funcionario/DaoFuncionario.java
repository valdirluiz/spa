package br.ufsc.ine.aps.controllers.funcionario;

import br.ufsc.ine.aps.controllers.pessoa.PessoaDao;
import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.*;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.*;


public class DaoFuncionario extends PessoaDao {

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




    public Gerente findOperadorDisponivel(){
        //TODO: implementar
        return null;
    }

    @Override
    public Connection getConnection() {
        return this.bdConnection;
    }


}
