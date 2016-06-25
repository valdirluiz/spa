package br.ufsc.ine.aps.controllers.funcionario;

import br.ufsc.ine.aps.controllers.pessoa.PessoaDao;
import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.*;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioDao extends PessoaDao {

    private Connection bdConnection;

    private static FuncionarioDao ourInstance = new FuncionarioDao();

    public static FuncionarioDao getInstance() {
        return ourInstance;
    }

    private FuncionarioDao() {
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


    @Override
    public Connection getConnection() {
        return this.bdConnection;
    }


}
