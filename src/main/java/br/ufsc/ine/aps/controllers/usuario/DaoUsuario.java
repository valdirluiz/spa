package br.ufsc.ine.aps.controllers.usuario;


import br.ufsc.ine.aps.utils.SQLiteConnection;
import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.*;

import java.sql.*;

public class DaoUsuario {

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM pessoas WHERE id = ?";

    private Connection bdConnection;

    private static DaoUsuario ourInstance = new DaoUsuario();

    public static DaoUsuario getInstance() {
        return ourInstance;
    }

    public DaoUsuario(){
        this.bdConnection = SQLiteConnection.getInstance().getConnection();
    }

    public Autenticavel findByCPFAndFlCliente(String cpf, Boolean flCliente){
        ResultSet rs;
        PreparedStatement stmt = null;
        try {
            stmt = this.bdConnection.prepareStatement("select * from pessoas where cpf = ? and is_cliente = ?");
            stmt.setString(1, cpf);
            stmt.setInt(2, flCliente ? 1 : 0);
            rs = stmt.executeQuery();
            if(rs.next()){
                return this.montaUsuario(rs);
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
            autenticavel = new Operador();
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


    public Pessoa findPessoaById(int id){
        Pessoa pessoa =  null;

        try {
            PreparedStatement stmt = this.bdConnection.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){

                if(rs.getInt(7) == TipoUsuario.ATENDENTE.getId()) {
                    pessoa = new Atendente();
                }else if(rs.getInt(7) == TipoUsuario.GERENTE.getId()){
                    pessoa = new Gerente();
                }

                pessoa.setId(rs.getInt(1));
                pessoa.setCpf(rs.getString(2));
                pessoa.setEmail(rs.getString(3));
                pessoa.setNome(rs.getString(4));
                pessoa.setSenha(rs.getString(5));
                pessoa.setTelefone(rs.getString(6));
                pessoa.setCliente(rs.getInt(8) == 1 ? true : false);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return pessoa;
    }
}
