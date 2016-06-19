package br.ufsc.ine.aps.controllers.pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public abstract class PessoaDao {

    public void deletarPessoa(Integer id) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt  = this.getConnection().prepareStatement("DELETE FROM pessoas WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
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

    public abstract Connection getConnection();

}
