/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;


import dao.ClienteDAO;
import javax.enterprise.context.spi.Context;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import modelo.Cliente;


/**
 *
 * @author informatica
 */
@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements javax.faces.convert.Converter {

 ClienteDAO clienteDAO = new ClienteDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
     
        Cliente cliente;
        if (value != null) {
            cliente = (Cliente)clienteDAO.findById(Integer.parseInt(value));
            return cliente;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Cliente cli = (Cliente)value;
            return  String.valueOf(cli.getIdCliente());
         
        }
        return null;
    }

    
  
   
}
