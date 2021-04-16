/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManager;

import BusinessLogic.Admin;
import BusinessLogic.Employee;
import static UI.Login.empisLoggedIn;
import static UI.Login.adminisLoggedIn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static UI.Login.EName;

/**
 *
 * @author tavon
 */
public class EmployeeDataManager {
    
    public EmployeeDataManager(){
        
    }
    
    
    public void getEmployee(Employee employee){
        String Query = "SELECT `name`,`id`,`password` FROM `employee` WHERE `name`='"+employee.getName()+"' and `id`='"+employee.getId()+"' and `password`='"+employee.getPassword()+"'";
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            Statement state = conn.createStatement();
            ResultSet Rs = state.executeQuery(Query);
            if(Rs.next()){
                EName = employee.getName();
                empisLoggedIn=true;
            }
            else{
                empisLoggedIn=false;
            }
        } catch (SQLException ex) {
            
        }
    }
    
    public void getAdmin(Admin admin){
        String Query = "SELECT `adminname`,`id`,`password` FROM `admins` WHERE `adminname`='"+admin.getName()+"' and `id`='"+admin.getId()+"' and `password`='"+admin.getPassword()+"'";
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdatabase","root","");
            Statement state = conn.createStatement();
            ResultSet Rs = state.executeQuery(Query);
            if(Rs.next()){
                adminisLoggedIn=true;
            }
            else{
                adminisLoggedIn=false;
            }
        } catch (SQLException ex) {
            
        }
    }
   
}
