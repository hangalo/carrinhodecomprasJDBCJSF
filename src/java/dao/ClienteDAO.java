/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import util.ConnectionDB;
import util.GenericoDAO;

/**
 *
 * @author informatica
 */
public class ClienteDAO implements GenericoDAO<Cliente> {

    private static final String INSERT = "";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL = "SELECT id_cliente, nome FROM cliente";
    private static final String SELECT_BY_ID = "SELECT id_cliente, nome FROM cliente WHERE id_cliente =?";

    
      @Override
    public boolean save(Cliente cliente) {
        //implementar
      return false;
    }

    @Override
    public boolean update(Cliente cliente) {
        //implementar
        return false;
    }

    @Override
    public boolean delete(Cliente cliente) {
        //implementar
       return false;
    }
    
    
      
    
    
    @Override
    public Cliente findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ClienteDAO:findById: nenhum registo com o id: " + id);
            }
            fillData(cliente, rs);
        } catch (SQLException ex) {
            System.err.println("CclienteDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                fillData(cliente, rs);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return clientes;
    }

    @Override
    public void fillData(Cliente cliente, ResultSet rs) {
        try {

            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setNome(rs.getString("nome"));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Cliente: " + ex.getLocalizedMessage());
        }

    }

   

}
