/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManager;

import BusinessLogic.Payment;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author tavon
 */
public class PaymentDataManager {
    
    public PaymentDataManager(){
    
    }
    
    public boolean isAuthorized(Payment payment){
        boolean res=false;
        long cnum = payment.getCardNum();
        int cexpm = payment.getExpDateMonth();
        int cexpy = payment.getExpDateYear();
        int ccvv = payment.getCVV();
        int cardno = String.valueOf(cnum).length();
        int cardexpm = String.valueOf(cexpm).length();
        int cardexpy = String.valueOf(cexpy).length();
        int cardcvv = String.valueOf(ccvv).length();
        if (cardno!=16 || cardexpm>12|| cardexpy>99||cardcvv!=3){
            res = false;
        }else if (onlyLong(cnum)== false|| onlyDigits(cexpm)== false||onlyDigits(cexpy)== false||onlyDigits(ccvv)== false){
            res = false;
        }else{
            res = true;
        }   
    return res;
    }
    
    public boolean onlyDigits(int detail){
        String str = String.valueOf(detail);
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex); 
        if (str == null) { 
            return false; 
        }
        Matcher m = p.matcher(str); 
        return m.matches(); 
    }
    public boolean onlyLong(long detail){
        String str = String.valueOf(detail);
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex); 
        if (str == null) { 
            return false; 
        }
        Matcher m = p.matcher(str); 
        return m.matches(); 
    }
    
}
