package br.ufsc.ine.aps.controllers.funcionario;

import br.ufsc.ine.aps.controllers.pessoa.PessoaDao;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void cadastrar(Pessoa pessoa) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = bdConnection.prepareStatement("INSERT INTO pessoas (cpf, email, nome, senha, telefone, tipo_usuario, is_cliente, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, pessoa.getCpf());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getNome());
            stmt.setString(4, pessoa.getSenha());
            stmt.setString(5, pessoa.getTelefone());
            stmt.setInt(6, pessoa.getTipoUsuario().getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
