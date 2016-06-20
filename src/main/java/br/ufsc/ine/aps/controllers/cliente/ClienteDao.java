package br.ufsc.ine.aps.controllers.cliente;


import br.ufsc.ine.aps.controllers.pessoa.PessoaDao;
import br.ufsc.ine.aps.models.*;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao extends PessoaDao {

    private Connection bdConnection;

    public ClienteDao(){
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }

    public Cliente buscarCPF(String cpf) {
        ResultSet rs;
        PreparedStatement stmt = null;
        try {
            stmt = this.bdConnection.prepareStatement("select * from pessoas where cpf = ? AND tipo_usuario = 1 AND is_cliente = 1");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            if(rs.next()){
                return new Cliente(rs.getInt("id"), rs.getString("cpf"), rs.getString("senha"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"));
            } else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public Cliente buscarId(Integer id) {
        ResultSet rs;
        PreparedStatement stmt = null;
        try {
            stmt = this.bdConnection.prepareStatement("select * from pessoas where id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                return new Cliente(rs.getInt("id"), rs.getString("cpf"), rs.getString("senha"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"));
            } else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean salvar(Cliente cliente) {
        PreparedStatement stmt = null;
        try {
            if (cliente.getId() != 0) {
                stmt = bdConnection.prepareStatement("UPDATE pessoas SET cpf = ?, email = ?, nome = ?, telefone = ? WHERE id = ?");
            } else {
                stmt = bdConnection.prepareStatement("INSERT INTO pessoas (cpf, email, nome, senha, telefone, tipo_usuario, is_cliente, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            }
            if (cliente.getId() == 0) {
                stmt.setString(1, cliente.getCpf());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getNome());
                stmt.setString(4, cliente.getSenha());
                stmt.setString(5, cliente.getTelefone());
                stmt.setInt(6, cliente.getTipoUsuario().getId());
                stmt.setBoolean(7, cliente.isCliente());
                stmt.setString(8, cliente.getDataDeCadastro().toString());
            } else {
                stmt.setString(1, cliente.getCpf());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getNome());
                stmt.setString(4, cliente.getTelefone());
                stmt.setInt(5, cliente.getId());
            }
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public List<Cliente> buscar(){
        List<Cliente> pessoas = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = bdConnection.createStatement();
            rs = stmt.executeQuery( "select * from pessoas where tipo_usuario = 1 AND is_cliente = 1;" );
            while ( rs.next() ) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setTelefone(rs.getString("telefone"));
                pessoas.add(cliente);
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

    public static void main(String[] args) {
       ClienteDao dao  = new ClienteDao();
       List<Cliente> pessoas = dao.buscar();

       for(Pessoa pessoa : pessoas){
           System.out.println(pessoa.getNome());
       }
    }

    @Override
    public Connection getConnection() {
        return this.bdConnection;
    }
}
