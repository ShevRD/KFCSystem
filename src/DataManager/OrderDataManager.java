/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManager;

import BusinessLogic.Employee;
import BusinessLogic.Order;
import static UI.Login.UID;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author tavon
 */
public class OrderDataManager {
    private final InventoryDataManager inventory;
    public OrderDataManager(){
    inventory = new InventoryDataManager();
    }
    
    public boolean placeOrder(Order order){
        Random ran = new Random();
        int n = ran.nextInt(1000000)+1;
        boolean res=false;
        try{
            Connection connr = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            PreparedStatement state = connr.prepareStatement("INSERT INTO orders(orderid,cname,cid,ords,totalcost,status,odate,quantity,email) VALUES(?,?,?,?,?,?,?,?,?)");
            state.setInt(1, n);
            state.setString(2, order.getName());
            state.setInt(3, order.getCID());
            state.setString(4, order.getOrders());
            state.setInt(5, order.getTotalCost());
            state.setString(6, order.getStatus());
            state.setTimestamp(7,java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            state.setString(8, order.getQty());
            state.setString(9, order.getEmail());
            state.executeUpdate();
            Statement ste = connr.createStatement();
            ResultSet rs = ste.executeQuery("SELECT orderid FROM orders");
            while (rs.next()){
               int p = rs.getInt("orderid");
               if(p==n){
                   res = true;
               }   
            }
            if(res==true){
                inventory.updatePostPurchase();
            }
        }catch(SQLException ex){
            
        }
        return res;
    }
    
    public ArrayList<String> grabDetails(){
        ArrayList<String> list  = new ArrayList<>();
        String c = "";
        String q = "";
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdatabase","root","");
           Statement ste = conn.createStatement();
           ResultSet rs = ste.executeQuery("SELECT * FROM cart");
           while(rs.next()){
               String d = rs.getString("prodname");
               int g = rs.getInt("quantity");
               String h = Integer.toString(g);
               c = c + d +",";
               q = q + h +",";
           }
           list.add(c);
           list.add(q);
        }catch(SQLException ex){
        }
        return list;
    }
    
    
    
    public ArrayList<Order> viewOrders(){
        ArrayList<Order> list  = new ArrayList<Order>();
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
           java.sql.Statement ste = conn.createStatement();
           ResultSet rs = ste.executeQuery("SELECT orderid, cname, cid, ords, totalcost, status, quantity, email FROM orders WHERE cid='"+UID+"'");
           
           Order o;
           while (rs.next()){
               o = new Order(rs.getInt("orderid"),rs.getString("cname"),rs.getInt("cid"),rs.getString("ords"),rs.getInt("totalcost"),rs.getString("quantity"),rs.getString("status"),rs.getString("email"));
               list.add(o);
           }
        }catch(SQLException ex){
        }
        return list;
    }
    
    
    public ArrayList<Order> receiveOrder(){
        ArrayList<Order> list  = new ArrayList<Order>();
        try{
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
           java.sql.Statement ste = conn.createStatement();
           ResultSet rs = ste.executeQuery("SELECT orderid, cname, cid, ords, totalcost, status, quantity FROM orders");
           
           Order o;
           while (rs.next()){
               o = new Order(rs.getInt("orderid"),rs.getString("cname"),rs.getInt("cid"),rs.getString("ords"),rs.getInt("totalcost"),rs.getString("quantity"),rs.getString("status"),null);
               list.add(o);
           }
        }catch(SQLException ex){
        }
        return list;
    }
    
    public static void notifyCustomer(String recepient, String Msg) throws Exception{
        Properties properties = new  Properties();
        
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.mail.yahoo.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail="kfconlineuwi@yahoo.com";
        String password= "vihrbtiwiadwzfqi";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient, Msg);
        Transport.send(message);
        JOptionPane.showMessageDialog(null,"Email Sent!");
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String msg){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("KFC Online Order");
            message.setText(msg);
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean cancelOrder(String id) throws ParseException{
        int response = JOptionPane.showConfirmDialog(null,"Would You Like To Cancel Order?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        Date d = null;
        int OrderId = 0;
        boolean res = false;
        if(response==JOptionPane.YES_OPTION){
            try{
            String query ="SELECT * FROM orders where orderid='"+id+"'";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            PreparedStatement prep = conn.prepareStatement(query);
            ResultSet rs = prep.executeQuery();
            if(rs.next()){
                d = rs.getTimestamp("odate");
                OrderId = rs.getInt("orderid");
            }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex); 
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            Date date = new Date();
            String dateStop = dateFormat.format(date);
            Date secondParsedDate = dateFormat.parse(dateStop);
            long diff = secondParsedDate.getTime() - d.getTime();
            long diffMinutes = diff / (60 * 1000)%60;
            if (diffMinutes<5){
                String request = "Cancel Requested";
                try{
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
                PreparedStatement prep = conn.prepareStatement("UPDATE orders SET status=? WHERE orderid=?");
            
                prep.setString(1,request);
                prep.setInt(2,OrderId);
                if(prep.executeUpdate()==1){
                    JOptionPane.showMessageDialog(null, "Cancel Request Sent");
                    res = true;
                }else{
                    JOptionPane.showMessageDialog(null, "Entry does not exist!");
                }
                }catch(SQLException ex){
            
                }
            }else{
                JOptionPane.showMessageDialog(null, "Time to Cancel Your Order Has Passed");
            }
        }else{
            
        }    
        return res;
    }
    
}
