package br.ufsc.ine.aps.controllers.pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public abstract class PessoaDao {

    public void deletarPessoa(Integer id) throws SQLException {
        PreparedStatement stmt  = this.getConnection().prepareStatement("delete from pessoas where id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public abstract Connection getConnection();

}
