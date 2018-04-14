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
import modelo.Categoria;
import modelo.Factura;
import util.ConnectionDB;
import util.GenericoDAO;

/**
 *
 * @author informatica
 */
public class FacturaDAO implements GenericoDAO<Factura>{
      private static final String INSERT = "INSERT INTO factura(data_factura, id_cliente, valor_total)VALUES(?, ?, ?)";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL ="SELECT id_factura, data_factura, c.nome,valor_total FROM factura f INNER JOIN cliente c ON (f.id_cliente = c.id_cliente)";
    private static final String SELECT_BY_ID ="";
    
    private static final String SELECT_MAX_ID_FACTURA="SELECT MAX(id_factura) FROM factura";
    
  
    
    
    
     @Override
    public boolean save(Factura factura) {
       PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        if (factura == null) {
            System.err.println("FacturaDAO:save: O valor oassado nao pode ser nulo!");
        }
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setDate(1, new java.sql.Date(factura.getDataFactura().getTime()));
            ps.setInt(2, factura.getCliente().getIdCliente());
            ps.setDouble(3, factura.getValorTotal());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("FacturaDAO:save:Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("FacturaDAO:save:Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    @Override
    public boolean update(Factura factura) {
        //implementar
        return false;
    }

    @Override
    public boolean delete(Factura factura) {
        //implementar
       return false;
    }

    @Override
    public Factura findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
       Factura factura = new Factura();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("CategoriaDAO:findById: nenhum registo com o id: " + id);
            }
            fillData(factura, rs);
        } catch (SQLException ex) {
            System.err.println("CategoriaDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return factura;
    }

   
   

   
   

    @Override
    public List<Factura> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Factura> facturas = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
               Factura factura = new Factura();
                fillData(factura, rs);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return facturas;
    }

   
     public Integer buscaUltimaFactura() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Factura factura = new Factura();
        Integer ultimo=null;
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_MAX_ID_FACTURA);
             rs = ps.executeQuery();
            if (rs.next()) {
               
                ultimo = rs.getInt(1);
            }
            
            

        } catch (SQLException ex) {
            System.out.println("FacturaDAO: buscaUltimaFactura -> Erro ao carregar dados" + ex.getMessage());
        } finally {
           ConnectionDB.closeConnection(conn);
        }
        return ultimo;

    }
   

    @Override
    public void fillData(Factura factura, ResultSet rs) {
        try {            
           
           factura.setIdFactura(rs.getInt("id_categoria"));
          

        } catch (SQLException ex) {
            System.err.println("Error on fill data Factura: " + ex.getLocalizedMessage());
        }

    }
}
