/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import BusinessLogic.Products;
import static UI.DisplayMenu.FilterBY;

/**
 *
 * @author tavon
 */

public class Menu {
    
    
    public Menu(){}
    
    
    public ArrayList<Products> isSorted(){
        ArrayList<Products> list  = new ArrayList<Products>();
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
           Statement ste = conn.createStatement();
           ResultSet rs = ste.executeQuery("SELECT * FROM products WHERE category LIKE'%"+FilterBY+"%'");
           Products p;
           while (rs.next()){
               p = new Products(rs.getInt("id"),rs.getString("prodname"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("category"),rs.getBytes("prodimage"),null);
               list.add(p);
           }
        } catch (SQLException ex) {
        }
        return list;
    }
}
