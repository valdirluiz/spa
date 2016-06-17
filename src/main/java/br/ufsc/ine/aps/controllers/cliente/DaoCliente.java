package br.ufsc.ine.aps.controllers.cliente;


import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.Atendente;
import br.ufsc.ine.aps.models.Autenticavel;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Gerente;
import br.ufsc.ine.aps.utils.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
