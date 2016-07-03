package br.ufsc.ine.aps.controllers.pessoa;

import br.ufsc.ine.aps.enuns.TipoUsuario;
import br.ufsc.ine.aps.models.Atendente;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Operador;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.utils.Md5Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class PessoaDao {

    public void delete(Integer id) throws Exception {
        PreparedStatement stmt   = this.getConnection().prepareStatement("DELETE FROM pessoas WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public void save(Pessoa pessoa) throws Exception{
        PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO pessoas (cpf, email, nome, senha, telefone, tipo_usuario, is_cliente, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        this.setDadosPessoa(pessoa, stmt);
        this.setDadosCliente(pessoa, stmt);
        stmt.executeUpdate();
        stmt.close();
    }

    private void setDadosPessoa(Pessoa pessoa, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, pessoa.getCpf());
        stmt.setString(2, pessoa.getEmail());
        stmt.setString(3, pessoa.getNome());
        stmt.setString(4, Md5Utils.convertStringToMd5(pessoa.getSenha()));
        stmt.setString(5, pessoa.getTelefone());
        stmt.setInt(6, pessoa.getTipoUsuario().getId());
        stmt.setBoolean(7, pessoa.isCliente());
    }


    private void setDadosCliente(Pessoa pessoa, PreparedStatement stmt) throws SQLException {
        if(pessoa instanceof Cliente){
            stmt.setString(8,new Date().toString());
        } else{
            stmt.setNull(8,1);
        }
    }

    public void update(Pessoa pessoa) throws Exception {
        PreparedStatement stmt = getConnection().prepareStatement("UPDATE pessoas SET cpf = ?, email = ?, nome = ?, telefone = ?, tipo_usuario = ? WHERE id = ?");
        stmt.setString(1, pessoa.getCpf());
        stmt.setString(2, pessoa.getEmail());
        stmt.setString(3, pessoa.getNome());
        stmt.setString(4, pessoa.getTelefone());
        stmt.setInt(5, pessoa.getTipoUsuario().getId());
        stmt.setInt(6, pessoa.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    private  void setTipos(PreparedStatement preparedStatement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    private  String preparePlaceHolders(int length) {
        return String.join(",", Collections.nCopies(length, "?"));
    }

    public List<Pessoa> findByTipos(List<Integer> tipos) {
        List<Pessoa> pessoas = new ArrayList<>();
        try{

            String sql = String.format("select * from pessoas where tipo_usuario in (%s);", this.preparePlaceHolders(tipos.size()));
            ResultSet rs = null;
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            this.setTipos(stmt, tipos.toArray());
            rs = stmt.executeQuery();
            Pessoa pessoa = null;
            while ( rs.next() ) {
                TipoUsuario tipo = TipoUsuario.getById(rs.getInt("tipo_usuario"));
                if(tipo.equals(TipoUsuario.ATENDENTE)){
                    pessoa = new Atendente();
                } else if(tipo.equals(TipoUsuario.OPERADOR_SUPORTE)){
                    pessoa = new Operador();
                } else{
                    pessoa = new Cliente();
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
            rs.close();
            stmt.close();


        } catch (Exception e){
            e.printStackTrace();
        }

        return pessoas;
    }

    public boolean existeCpf(String cpf, TipoUsuario tipoUsuario) {
        Boolean existe = false;
        try {
            ResultSet rs;
            PreparedStatement stmt = null;
            stmt = this.getConnection().prepareStatement("select * from pessoas where cpf = ? AND tipo_usuario = ?");
            stmt.setString(1, cpf);
            stmt.setInt(2, tipoUsuario.getId());
            existe =  stmt.executeQuery().next();

        } catch (Exception e){
            e.printStackTrace();
        }

        return existe;

    }

    public abstract Connection getConnection();



}
