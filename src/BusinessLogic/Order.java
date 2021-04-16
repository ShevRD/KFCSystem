/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author tavon
 */
public class Order {
    
    private int id;
    private String name;
    private int cid;
    private String order;
    private int totalcost;
    private String stat;
    private String qty;
    private String email;
    
    public Order(){}
    
    public Order(int ID,String Name, int CID, String Orders, int TotalCost,String Quantity, String Status, String Email){
        this.id = ID;
        this.name = Name;
        this.order = Orders;
        this.totalcost = TotalCost;
        this.stat = Status;
        this.cid= CID;
        this.qty = Quantity;
        this.email = Email;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int ID){
        this.id = ID;
    }
    public String getName(){
        return name;
    }
    public void setName(String Name){
        this.name = Name;
    }
    public int getCID(){
        return cid;
    }
    public void setCID(int CID){
        this.cid = CID;
    }
    
    public String getOrders(){
        return order;
    }
    public void setOrders(String Orders){
        this.order = Orders;
    }
    
    public int getTotalCost(){
        return totalcost;
    }
    public void setTotalCost(int TotalCost){
        this.totalcost = TotalCost;
    }
    
    public String getQty(){
        return qty;
    }
    public void setQTy(String Quantity){
        this.qty = Quantity;
    }
    
    public String getStatus(){
        return stat;
    }
    public void setStatus(String Status){
        this.stat = Status;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String Email){
        this.email = Email;
    }
    
}