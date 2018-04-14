/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;


import dao.FacturaDAO;
import dao.ItemDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import modelo.Cliente;
import modelo.Factura;
import modelo.Item;
import modelo.Produto;
import util.DateUtil;

/**
 *
 * @author informatica
 */
@Named(value = "carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {

    private static final long serialVersionUID = 1L;
  
    
    private FacturaDAO facturaDAO;

   
    private ItemDAO itemDAO;

    @Inject
    FacturaMBean facturaMBean;

    private List<Item> carrinho = new ArrayList<>();
    private List<Cliente> clientes;

    private Cliente cliente;
    private Factura factura;

    private float total;

    @PostConstruct
    public void inti() {
        facturaDAO = new FacturaDAO();
        itemDAO = new ItemDAO();
        cliente = new Cliente();
        factura = new Factura();

        clientes = new ArrayList<>();

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FacturaMBean getFacturaMBean() {
        return facturaMBean;
    }

    public void setFacturaMBean(FacturaMBean facturaMBean) {
        this.facturaMBean = facturaMBean;
    }

    public CarrinhoBean() {
    }

    private int isExisting(Produto p) {
        for (int i = 0; i < this.carrinho.size(); i++) {
            if (this.carrinho.get(i).getProduto().getIdProduto() == p.getIdProduto()) {
                return i;
            }
        }
        return -1;

    }

    public long suma() {
        long s = 0;
        for (Item it : this.carrinho) {
            s += it.getQuantidade() * it.getProduto().getPrecoProduto();
        }
        return s;
    }

    public void delete(Item it) {
        System.out.println("Delelte >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (this.carrinho != null && !this.carrinho.isEmpty()) {
            if (it != null) {
                this.carrinho.remove(it);
            }
        }

    }

    public String addicionarProdutoCarrinho(Produto p) {
        int index = isExisting(p);
        if (index == -1) {
            this.carrinho.add(new Item(p, p.getPrecoProduto(), 1));
        } else {
            int quantidade = this.carrinho.get(index).getQuantidade() + 1;
            this.carrinho.get(index).setQuantidade(quantidade);
        }
        // return "carrinho?face-redirect=true";
        return null;
    }

    public List<Item> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Item> carrinho) {
        this.carrinho = carrinho;
    }

    public void registarCompra() {
        // guarda a factura -> busca a ultima factura

        double totalFactura = suma();
        // define o total da factura actual
        facturaMBean.getFactura().setValorTotal(totalFactura);

        //chamada do metodo que regista a factura actual
        facturaMBean.registarFactura();

        //busca a ultima factura registada
        Integer numeroFatura = facturaDAO.buscaUltimaFactura();
        //define o nunmeo da factura actual
        factura.setIdFactura(numeroFatura);
        //percorre o carrinho e regista cada item
        for (Item item : carrinho) {
            item.setIdFactura(factura);
            itemDAO.save(item);
        }
        carrinho.clear();
    }

}
