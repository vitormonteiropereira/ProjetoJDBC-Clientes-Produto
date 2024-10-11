package org.example.dao;

import org.example.domain.Cliente;
import org.example.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO implements IDAO<Cliente> {
    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO CLIENTES (CODIGO, NOME) VALUES (?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getCodigo());
            stm.setString(2, cliente.getNome());
            return stm.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
        finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public Cliente consultar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM CLIENTES WHERE CODIGO = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()){
                cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setId(rs.getLong("id"));
                cliente.setCodigo(rs.getString("codigo"));
            }

        } catch (Exception e) {
            throw e;
        }
        finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
            if (rs != null && !rs.isClosed()){
                rs.close();
            }
        }
        return cliente;
    }

    @Override
    public Integer excluir(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;


        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM CLIENTES WHERE CODIGO = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            return stm.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
        finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {

            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE clientes SET nome = ?, codigo = ? WHERE id = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCodigo());
            stm.setLong(3, cliente.getId());
            return stm.executeUpdate();
        } catch (Exception e){
            throw e;
        }
        finally {
            if (connection !=null && !connection.isClosed()){
                connection.close();
            }
            if (stm !=null && !stm.isClosed()){
                stm.close();
            }
        }

    }

    @Override
    public List<Cliente> retornaTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> lista = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM CLIENTES";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setCodigo(rs.getString("codigo"));
                cliente.setId(rs.getLong("id"));
                lista.add(cliente);
            }

        } catch (Exception e) {
            throw e;
        }


        return lista;
    }


}
