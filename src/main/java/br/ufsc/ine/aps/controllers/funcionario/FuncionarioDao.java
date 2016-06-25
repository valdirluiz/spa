package br.ufsc.ine.aps.controllers.funcionario;

import br.ufsc.ine.aps.controllers.pessoa.PessoaDao;
import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.Atendente;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Operador;
import br.ufsc.ine.aps.models.Pessoa;
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

    @Override
    public Connection getConnection() {
        return this.bdConnection;
    }


}
