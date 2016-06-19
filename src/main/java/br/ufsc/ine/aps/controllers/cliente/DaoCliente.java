package br.ufsc.ine.aps.controllers.cliente;


import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.*;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCliente {

    private Connection bdConnection;

    public DaoCliente(){
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }

    public Cliente findByCPF(String cpf) {
        ResultSet rs;
        PreparedStatement pstmt;
        try {
            pstmt = this.bdConnection.prepareStatement("select * from pessoas where cpf = ?");
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            if(rs.next()){
                return new Cliente(rs.getInt("id"), rs.getString("cpf"), rs.getString("senha"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"));
            } else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cadastrar(Cliente cliente) {
        try {
            PreparedStatement stmt = bdConnection.prepareStatement("INSERT INTO pessoas (cpf, email, nome, senha, telefone, tipo_usuario, is_cliente, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getSenha());
            stmt.setString(5, cliente.getTelefone());
            stmt.setInt(6, cliente.getTipoUsuario().getId());
            stmt.setBoolean(7, cliente.isCliente());
            stmt.setString(8, cliente.getDataDeCadastro().toString());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Cliente> findClientes(){
        List<Cliente> pessoas = new ArrayList<>();
        try {
            Statement stmt = bdConnection.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from pessoas where tipo_usuario = 1;" );
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
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
        }
        return pessoas;
    }

    public static void main(String[] args) {
       DaoCliente dao  = new DaoCliente();
       List<Cliente> pessoas = dao.findClientes();
       for(Pessoa pessoa : pessoas){
           System.out.println(pessoa.getNome());
       }
    }

}
