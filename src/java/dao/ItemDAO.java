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
import modelo.Item;
import util.ConnectionDB;
import util.GenericoDAO;

/**
 *
 * @author informatica
 */
public class ItemDAO implements GenericoDAO<Item> {

    private static final String INSERT = "INSERT INTO item(id_produto, quantidade, preco_compra, id_factura) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL = "";
    private static final String SELECT_BY_ID = "";

    @Override
    public boolean save(Item item) {
        PreparedStatement ps = null;
        Connection conn = null;
        boolean flagControlo = false;
        if (item == null) {
            System.err.println("ItemDAO:save: O valor oassado nao pode ser nulo!");
        }
        try {

            /*
            id_produto, quantidade, preco_compra, id_factura
            
             */
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setInt(1, item.getProduto().getIdProduto());
            ps.setInt(2, item.getQuantidade());
            ps.setDouble(3, item.getPrecoCompra());
            ps.setInt(4, item.getIdFactura().getIdFactura());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("ItemDAO:save:Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("ItemDAO:save:Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, ps);
        }
    }

    @Override
    public boolean update(Item item) {
        //implementar
        return false;
    }

    @Override
    public boolean delete(Item item) {
        //implementar
        return false;
    }

    @Override
    public Item findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Item item = new Item();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("ItemDAO:findById: nenhum registo com o id: " + id);
            }
            fillData(item, rs);
        } catch (SQLException ex) {
            System.err.println("ItemDAO:findeByID: Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn, ps, rs);
        }
        return item;
    }

    @Override
    public List<Item> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                fillData(item, rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return items;
    }

    @Override
    public void fillData(Item item, ResultSet rs) {
        try {

            item.setIdItem(rs.getInt(""));

        } catch (SQLException ex) {
            System.err.println("Error on fill data Item: " + ex.getLocalizedMessage());
        }

    }
}
