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


    public List<Pessoa> findAll(){
        List<Pessoa> pessoas = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = bdConnection.createStatement();
            rs = stmt.executeQuery( "select * from pessoas where tipo_usuario in (2,3);" );
            Pessoa pessoa = null;
            while ( rs.next() ) {
                TipoUsuario tipo = TipoUsuario.getById(rs.getInt("tipo_usuario"));
                if(tipo.equals(TipoUsuario.ATENDENTE)){
                    pessoa = new Atendente();
                } else {
                    pessoa = new Operador();
                }
                pessoa.setId(rs.getInt("id"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setTipoUsuario(tipo);
                pessoas.add(pessoa);
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return pessoas;
    }

    public void atualizarPessoa(Pessoa pessoa){

    }
}
