/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static UI.Login.UID;
import static UI.Login.UName;
import static UI.Login.UPassword;
import static UI.Login.isLoggedIn;
import javax.swing.JOptionPane;

/**
 *
 * @author tavon
 */
public class Logout {
    
    public Logout(){
        
    }
    
    public void isLoggedOut(){
        int response = JOptionPane.showConfirmDialog(null,"Would You Like To Logout?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        if(response==JOptionPane.YES_OPTION){
           UName = "";
           UID = 0;
           UPassword ="";
           isLoggedIn=false;
           System.out.println("You Have Been Logged Out");
           new Login().setVisible(true);  
        }
    }
    
    
}
