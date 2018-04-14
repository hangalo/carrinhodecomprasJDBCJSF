/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author informatica
 * @param <T>
 */
public interface GenericoDAO<T> {
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);
    T findById(Integer id);
    List<T> findAll();
    void fillData(T t, ResultSet rs);
}


/*
    private static final String INSERT = "";
    private static final String UPDATE = "";
    private static final String DELETE = "";
    private static final String SELECT_ALL ="";
    private static final String SELECT_BY_ID ="";

*/

