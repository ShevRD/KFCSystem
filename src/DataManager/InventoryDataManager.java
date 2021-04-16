/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManager;

import BusinessLogic.Products;
import static UI.DisplayMenu.SearchBY;
import static UI.Login.UID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import static UI.ProductSelection.insertid;
import static UI.ProductSelection.insertname;
import static UI.ProductSelection.insertcategory;
import static UI.ProductSelection.insertdescription;
import static UI.ProductSelection.insertprice;
import static UI.ProductSelection.imgview;


/**
 *
 * @author tavon
 */
public class InventoryDataManager {
    private Connection conn;
    private Connection conn1;
    private String query;
    private PreparedStatement  state;
    private ResultSet rs;
    private Statement ste;
    private CartDataManager cart;


    public InventoryDataManager(){
        conn = null;
        query=null;
        state = null;
        rs = null;
        conn1 = null;
        ste = null;
        cart = new CartDataManager();
        
    }
    
    
    public ArrayList<Products> manageInventory(){
        ArrayList<Products> list  = new ArrayList<Products>();
        try{
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
           ste = conn.createStatement();
           rs = ste.executeQuery("SELECT id, prodname, quantity, category FROM products");
           
           Products p;
           while (rs.next()){
               p = new Products(rs.getInt("id"),rs.getString("prodname"),rs.getInt("quantity"),0,rs.getString("category"),null,null);
               list.add(p);
           }
        }catch(SQLException ex){
        }
    return list;
    
    }
    
    
    public ArrayList<Products> searchByName(){
        ArrayList<Products> list  = new ArrayList<Products>();
        try{
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
           ste = conn.createStatement();
           rs = ste.executeQuery("SELECT * FROM products WHERE prodname LIKE'%"+SearchBY+"%'");
           Products p;
           while (rs.next()){
               p = new Products(rs.getInt("id"),rs.getString("prodname"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("category"),rs.getBytes("prodimage"),null);
               list.add(p);
           }
        } catch (SQLException ex) {
        }
        return list;
    }
    
    public boolean updateInventory(Products product){
        boolean res=false;
        try{
           
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            query="UPDATE products SET quantity= quantity+? WHERE id=?";
            state = conn.prepareStatement(query);
            
            state.setInt(1, product.getQuantity());
            state.setInt(2, product.getId());
            if(state.executeUpdate()==1)
                res=true;
            else if(state.executeUpdate()==0){
                res=false;
            }
        }catch(SQLException ex){
            
        } finally{
            return res;
        }

            
    }
    
    public  boolean deleteProductFromInventory(Products product){
        boolean res=false;
        try{
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            query="DELETE FROM products WHERE id=?";
            state = conn.prepareStatement(query);
                  
            state.setInt(1, product.getId());
            if(state.executeUpdate()==1)
                res=true;
            else if(state.executeUpdate()==0){
                res=false;
            }
        }catch(SQLException ex){
            
        }finally{
            return res;
        }
            
    }
    
    public boolean addNewProduct(Products newproduct){
        Random ran = new Random();
        int n = ran.nextInt(1000000)+1;
        boolean res=false;
        try{
            
            newproduct.setId(n);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            query="INSERT INTO products(id,prodname,quantity,price,category,prodimage,description) VALUES(?,?,?,?,?,?,?)";
            state = conn.prepareStatement(query);
            state.setInt(1, newproduct.getId());
            state.setString(2, newproduct.getName());
            state.setInt(3, newproduct.getQuantity());
            state.setInt(4, newproduct.getPrice());
            state.setString(5, newproduct.getCategory());
            state.setBytes(6, newproduct.getMyImage());
            state.setString(7, newproduct.getDescription());
            if (state.executeUpdate()==1){
                res=true;
            }else{
                res=false;
            }            
        }catch(SQLException ex){
            
        }finally{
            return res;
        }
    }

    //Used to check if the product is already in the database
    public boolean checkIfProductExists(Products newproduct){
        boolean test = false;
        String comp = newproduct.getName();
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            query= "SELECT * FROM products";
            ste = conn.createStatement();
            rs = ste.executeQuery("SELECT prodname FROM products");
            while (rs.next()){
               String p = rs.getString("prodname");
               if(comp.equals(p)){
                   test = true;
               }   
            }
        } catch (SQLException ex) {
    }
    return test;
    }
    
    public void tempProductInsertion(String id){
         try{
            query ="SELECT * FROM products where id='"+id+"'";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
            state = conn.prepareStatement(query);
            rs = state.executeQuery();
            if(rs.next()){
                insertid = rs.getInt("id");
                insertname = rs.getString("prodname");
                insertprice = rs.getInt("price");
                insertcategory = rs.getString("category");
                insertdescription = rs.getString("description");
                imgview = rs.getBytes("prodimage");
            }    
        }catch (SQLException ex) {
        }
    }
    
    public ArrayList<Products> GenerateTable(){
        ArrayList<Products> list  = new ArrayList<Products>();
        try{
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
           ste = conn.createStatement();
           rs = ste.executeQuery("SELECT * FROM products");
           
           Products p;
           while (rs.next()){
               p = new Products(rs.getInt("id"),rs.getString("prodname"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("category"),rs.getBytes("prodimage"),rs.getString("description"));
               list.add(p);
           }
        }catch(SQLException ex){
        }
        return list;
    }
    
    
    public ArrayList<Products> GenerateCart(){
        ArrayList<Products> list1  = new ArrayList<Products>();
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
           Statement ste = conn.createStatement();
           ResultSet rs = ste.executeQuery("SELECT * FROM cart");
           
           Products p1;
           while (rs.next()){
               p1 = new Products(rs.getInt("id"),rs.getString("prodname"),rs.getInt("qtyinstock"),rs.getInt("price"),rs.getString("category"),null,null);
               list1.add(p1);
           }
        }catch(SQLException ex){
        }
        return list1;
    }
    
    public void updatePostPurchase(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
            conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            ste = conn.createStatement();
            rs = ste.executeQuery("SELECT * FROM cart WHERE customerid ='"+UID+"'");
            while (rs.next()){
               int p = rs.getInt("id");
               int q = rs.getInt("quantity");
               try{
               query="UPDATE products SET quantity=quantity-? WHERE id=?";
               state = conn1.prepareStatement(query);
               state.setInt(1, q);
               state.setInt(2, p);
               state.executeUpdate();
               }catch(SQLException ex){
               }
            }
            cart.clearCart();
            
        }catch(SQLException ex){
            
        }
    }
    
}
