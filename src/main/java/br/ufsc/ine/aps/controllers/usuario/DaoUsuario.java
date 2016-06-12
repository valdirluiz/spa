package br.ufsc.ine.aps.controllers.usuario;


import br.ufsc.ine.aps.bd.SQLiteConnection;
import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.*;

import java.sql.*;

public class DaoUsuario {

    private Connection bdConnection;

    public DaoUsuario(){
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }

    public Autenticavel findByCPF(String cpf){
        ResultSet rs;
        PreparedStatement pstmt;
        try {
            pstmt = this.bdConnection.prepareStatement("select * from pessoas where cpf = ?");
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            if(rs.next()){
                return this.montaUsuario(rs);
            } else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Autenticavel montaUsuario(ResultSet rs) throws SQLException {
        Autenticavel autenticavel = null;
        Integer tpUsuario = rs.getInt("tipo_usuario");
        TipoUsuario tipoUsuario = TipoUsuario.getById(tpUsuario);
        if(tipoUsuario.equals(TipoUsuario.ATENDENTE)){
            autenticavel = new Atendente();
        } else if (tipoUsuario.equals(TipoUsuario.CLIENTE)){
            autenticavel = new Cliente();
        } else if(tipoUsuario.equals(TipoUsuario.GERENTE)){
            autenticavel = new Gerente();
        } else if(tipoUsuario.equals(TipoUsuario.OPERADOR_SUPORTE)){

        }

        Integer id  = rs.getInt("id");
        autenticavel.setId(id);

        String nome = rs.getString("nome");
        autenticavel.setNome(nome);

        String senha = rs.getString("senha");
        autenticavel.setSenha(senha);

        autenticavel.setTipoUsuario(tipoUsuario);

        return autenticavel;

    }
}
