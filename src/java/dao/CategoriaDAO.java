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
import util.ConnectionDB;
import util.GenericoDAO;

/**
 *
 * @author informatica
 */
public class CategoriaDAO  implements GenericoDAO<Categoria> {
    private static final String INSERT = "";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL ="SELECT id_categoria, descricao FROM categoria;";
    private static final String SELECT_BY_ID ="SELECT id_categoria, descricao FROM categoria WHERE id_categoria =?";
    
    
    
    
     @Override
    public boolean save(Categoria categoria) {
        //implementar
      return false;
    }

    @Override
    public boolean update(Categoria categoria) {
        //implementar
        return false;
    }

    @Override
    public boolean delete(Categoria categoria) {
        //implementar
       return false;
    }

    @Override
    public Categoria findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
       Categoria categoria = new Categoria();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("CategoriaDAO:findById: nenhum registo com o id: " + id);
            }
            fillData(categoria, rs);
        } catch (SQLException ex) {
            System.err.println("CategoriaDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return categoria;
    }

   
   

   
   

    @Override
    public List<Categoria> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Categoria> categorias = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
               Categoria categoria = new Categoria();
                fillData(categoria, rs);
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return categorias;
    }

   
   

    @Override
    public void fillData(Categoria categoria, ResultSet rs) {
        try {            
           
           categoria.setIdCategoria(rs.getInt("id_categoria"));
           categoria.setDescricao(rs.getString("descricao"));     

        } catch (SQLException ex) {
            System.err.println("Error on fill data Categoria: " + ex.getLocalizedMessage());
        }

    }

}
