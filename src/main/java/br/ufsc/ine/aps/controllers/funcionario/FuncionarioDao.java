package br.ufsc.ine.aps.controllers.funcionario;

import br.ufsc.ine.aps.controllers.pessoa.PessoaDao;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.Connection;

/**
 * Created by Valdir Luiz on 19/06/2016.
 *//**
 * Created by Valdir Luiz on 19/06/2016.
 */
public class FuncionarioDao extends PessoaDao {

    private Connection bdConnection;

    private static FuncionarioDao ourInstance = new FuncionarioDao();

    public static FuncionarioDao getInstance() {
        return ourInstance;
    }

    private FuncionarioDao() {
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }

    @Override
    public Connection getConnection() {
        return this.bdConnection;
    }
}
