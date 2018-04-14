/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.ProdutoDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import modelo.Categoria;
import modelo.Produto;

/**
 *
 * @author informatica
 */
@Named(value = "produtoMBean")
@SessionScoped
public class ProdutoMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Produto produto;

    private ProdutoDAO produtoDAO;

    private Categoria categoria;

    private List<Produto> produtos;
    private List<Produto> produtosCategorias;

    @PostConstruct
    public void init() {
        produto = new Produto();
        categoria = new Categoria();
        produtoDAO = new ProdutoDAO();
        produtos = new ArrayList<>();
        produtosCategorias = new ArrayList<>();
        produtos = produtoDAO.findAll();
    }

    public ProdutoMBean() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void carregarProdutosDaCategoria() {

        produtosCategorias = produtoDAO.findProdutoByCategoria(categoria);

    }

    public List<Produto> getProdutosCategorias() {
        return produtosCategorias;
    }

    public void setProdutosCategorias(List<Produto> produtosCategorias) {
        this.produtosCategorias = produtosCategorias;
    }

}
