/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;


import dao.FacturaDAO;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import modelo.Factura;
import util.DateUtil;

/**
 *
 * @author informatica
 */
@Named(value = "facturaMBean")
//@RequestScoped
@SessionScoped
public class FacturaMBean implements Serializable{
private static final long serialVersionUID = 1L;

    private FacturaDAO facturaDAO;

    private Factura factura;

    public FacturaMBean() {
    }

    @PostConstruct
    public void init() {
        facturaDAO = new FacturaDAO();
        factura = new Factura();
        factura.setDataFactura(DateUtil.getDataActual());
    }

    public void registarFactura() {
       
        facturaDAO.save(factura);

    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void definirValorTotal(Double valor) {
        factura.setValorTotal(valor);
    }

   

}
