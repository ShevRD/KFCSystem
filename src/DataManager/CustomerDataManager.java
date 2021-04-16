/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManager;

import BusinessLogic.Customer;
import static UI.Login.UID;
import static UI.Login.UName;
import static UI.Login.UPassword;
import static UI.Login.isLoggedIn;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author tavon
 */
public class CustomerDataManager {
    
    public CustomerDataManager(){
        
    }
    
    public void getCustomer(Customer customer){
        String Query = "SELECT `name`,`id`,`password` FROM `customers` WHERE `name`='"+customer.getName()+"' and `id`='"+customer.getId()+"' and `password`='"+customer.getPassword()+"'";
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
            Statement state = conn.createStatement();
            ResultSet Rs = state.executeQuery(Query);
            if(Rs.next()){
                UName = customer.getName();
                UID = customer.getId();
                UPassword = customer.getPassword();
                isLoggedIn=true;
            }
            else{
                isLoggedIn=false;
            }
        } catch (SQLException ex) {
            
        }
    }
    
    public void registerCustomer(Customer customer){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
            PreparedStatement state = conn.prepareStatement("INSERT INTO customers(name,id,password) VALUES(?,?,?)");
            state.setString(1, customer.getName());
            state.setInt(2, customer.getId());
            state.setString(3, customer.getPassword());
            if(state.executeUpdate()==1)
                JOptionPane.showMessageDialog(null, "Registration Successful!"); 
        }catch (SQLException ex) {
        }
    
    }
    
}
