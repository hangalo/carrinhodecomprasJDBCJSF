/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author informatica
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer idItem;  
    private Integer quantidade;
    private Double precoCompra;   
    private Factura idFactura; 
    private Produto produto;

    public Item() {
    }

    public Item(Integer idItem, Integer quantidade, Double precoCompra, Factura idFactura, Produto produto) {
        this.idItem = idItem;
        this.quantidade = quantidade;
        this.precoCompra = precoCompra;
        this.idFactura = idFactura;
        this.produto = produto;
    }
  public Item(Produto p, Double precoProduto, int quantidade) {
      this.produto=p;
      this.precoCompra=precoProduto;
      this.quantidade = quantidade;
    }
    
    
    
    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idItem);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.idItem, other.idItem)) {
            return false;
        }
        return true;
    }

   
    
    
}
