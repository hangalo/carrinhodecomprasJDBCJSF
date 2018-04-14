/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.ClienteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import modelo.Cliente;

/**
 *
 * @author informatica
 */
@Named(value = "clienteMBean")
@RequestScoped
public class ClienteMBean {
private static final long serialVersionUID = 1L;
   
    private ClienteDAO clienteDAO;
    
   
    private Cliente cliente;
    private List<Cliente> clientes;
    
    public ClienteMBean() {
    }
    
    @PostConstruct
    public void init(){
    cliente = new Cliente();
    clienteDAO = new ClienteDAO();
    clientes = new ArrayList<>();
    clientes = clienteDAO.findAll();
    
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    
    
    
    
}
