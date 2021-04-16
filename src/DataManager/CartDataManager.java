/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManager;

import BusinessLogic.Order;
import BusinessLogic.Products;
import static UI.Login.UID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author tavon
 */
public class CartDataManager {
    
    public CartDataManager() {

    }
    
    public boolean addtoCart(Products product){
        boolean res=false;
        boolean tst = false;
        int n = product.getId();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
            Statement ste = con.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM cart WHERE customerid ='"+UID+"'");
            while (rs.next()){
               int p = rs.getInt("id");
               if(p==n){
                   tst = true;
               }   
            }
            if (tst==true){
            }else{
                PreparedStatement prep = con.prepareStatement("INSERT INTO cart(customerid,id,prodname,quantity,price,category) VALUES(?,?,?,?,?,?)");
                prep.setInt(1,UID);
                prep.setInt(2, product.getId());
                prep.setString(3, product.getName());
                prep.setInt(4, product.getQuantity());
                prep.setInt(5, product.getPrice());
                prep.setString(6, product.getCategory());
                prep.executeUpdate();
                res = true;
            }
        }catch(SQLException ex){
            
        }
    return res;  
    }
    
    public int calculateTotal(Order order){
        int total = 0;
        int Quantity = Integer.parseInt(order.getQty());
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
           Statement ste = conn.createStatement();
           ResultSet rs = ste.executeQuery("SELECT price FROM products WHERE id ='"+order.getId()+"'");
           while (rs.next()){
               total = total + ((rs.getInt("price"))* Quantity);
           }
        }catch(SQLException ex){
        }
        return total;
    }
    
    public boolean removefromCart(Products product){
        boolean res=false;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
            PreparedStatement prep = conn.prepareStatement("DELETE FROM cart WHERE id=? and customerid =?");
            
            prep.setInt(1, product.getId());
            prep.setInt(2, UID);
            if(prep.executeUpdate()==1)
                res=true;
            else if(prep.executeUpdate()==0){
                res=false;
            }
        }catch(SQLException ex){
            
        }finally{
            return res;
        }
    }
    
    public void clearCart(){

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
            PreparedStatement prep = conn.prepareStatement("DELETE FROM cart WHERE customerid ='"+UID+"'");
            prep.executeUpdate();
        }catch(SQLException ex){    
        }

    }
    
    
    public ArrayList<Products> GenerateCart(){
        ArrayList<Products> list1  = new ArrayList<Products>();
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
           Statement ste = conn.createStatement();
           ResultSet rs = ste.executeQuery("SELECT * FROM cart where customerid='"+UID+"'");
           
           Products p1;
           while (rs.next()){
               p1 = new Products(rs.getInt("id"),rs.getString("prodname"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("category"),null,null);
               list1.add(p1);
           }
        }catch(SQLException ex){
        }
        return list1;
    }
    
    
    
    
}
