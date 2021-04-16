/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import static UI.Login.UID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author tavon
 */
public class Customer {
    private String name;
    private int id;
    private String password;
    
    public Customer(){
    
    }
    
    public Customer(String Name, int ID, String Password){
        this.name = Name;
        this.id = ID;
        this.password = Password;
    }
    public String getName(){
        return name;
    }
    public void setName(String Name){
        this.name = Name;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int ID){
        this.id = ID;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String Password){
        this.password = Password;
    }
    
}
