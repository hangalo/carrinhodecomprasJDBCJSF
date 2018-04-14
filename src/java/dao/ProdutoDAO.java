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
import modelo.Produto;
import util.ConnectionDB;
import util.GenericoDAO;

/**
 *
 * @author informatica
 */
public class ProdutoDAO  implements GenericoDAO<Produto> {
    private static final String INSERT = "";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL ="SELECT id_produto, nome_produto, preco_produto, quantidade, c.descricao FROM produto p INNER JOIN categoria c ON (p.categoria = c.id_categoria)";
    private static final String SELECT_BY_ID ="SELECT id_produto, nome_produto, preco_produto, quantidade, c.descricao FROM produto p INNER JOIN categoria c ON (p.categoria = c.id_categoria) WHERE id_produto = ? ";
    private static final String SELECT_BY_CATEGORIA ="SELECT id_produto, nome_produto, preco_produto, quantidade, c.descricao FROM produto p INNER JOIN categoria c ON (p.categoria = c.id_categoria)WHERE c.id_categoria = ? ";
    
  
    
    
     @Override
    public boolean save(Produto produto) {
        //implementar
      return false;
    }

    @Override
    public boolean update(Produto produto) {
        //implementar
        return false;
    }

    @Override
    public boolean delete(Produto produto) {
        //implementar
       return false;
    }

    @Override
    public Produto findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
       Produto produto = new Produto();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ProdutoDAO:findById: nenhum registo com o id: " + id);
            }
            fillData(produto, rs);
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return produto;
    }

   
   

   
   

    @Override
    public List<Produto> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
               Produto produto = new Produto();
                fillData(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO: findAll: -> Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return produtos;
    }

  
   
    
      public List<Produto> findProdutoByCategoria(Categoria categoria) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_CATEGORIA);
            ps.setInt(1, categoria.getIdCategoria());
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                fillData(produto, rs);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("ProdutoDAO:findProdutoByCategoria: ->Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return produtos;
    }

    @Override
    public void fillData(Produto produto, ResultSet rs) {
        try {            
          
           produto.setIdProduto(rs.getInt("id_produto"));
           produto.setNomeProduto(rs.getString("nome_produto"));
           produto.setPrecoProduto(rs.getDouble("preco_produto"));
           produto.setQuantidade(rs.getInt("quantidade"));
           Categoria categoria = new Categoria();
           categoria.setDescricao(rs.getString("descricao"));
           produto.setCategoria(categoria);
              

        } catch (SQLException ex) {
            System.err.println("Error on fill data Lingua: " + ex.getLocalizedMessage());
        }

    }

}
