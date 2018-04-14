/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;


import dao.CategoriaDAO;
import javax.enterprise.context.spi.Context;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import modelo.Categoria;


/**
 *
 * @author informatica
 */
@FacesConverter(value = "categoriaConverter")
public class CategoriaConverter implements javax.faces.convert.Converter {

  CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
     
        Categoria categoria;
        if (value != null) {
            categoria = (Categoria)categoriaDAO.findById(Integer.parseInt(value));
            return categoria;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Categoria cat = (Categoria)value;
            return  String.valueOf(cat.getIdCategoria());
          
        }
        return null;
    }

   
    
    
    
}
