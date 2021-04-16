/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author tavon
 */
public class Payment {
    private long cardnum;
    private String cardtype;
    private int expdatemonth;
    private int expdateyear;
    private int cvv;
    
    public Payment(){}
    
    public Payment(long CardNum,String CardType, int ExpDateMonth, int ExpDateYear, int CVV){
        this.cardnum = CardNum;
        this.cardtype = CardType;
        this.expdatemonth = ExpDateMonth;
        this.expdateyear = ExpDateYear;
        this.cvv = CVV;
    }
    
    public long getCardNum(){
        return cardnum;
    }
    public void setCardNum(long CardNum){
        this.cardnum = CardNum;
    }
    public String getCardType(){
        return cardtype;
    }
    public void setCardType(String CardType){
        this.cardtype = CardType;
    }
    public int getExpDateMonth(){
        return expdatemonth;
    }
    public void setExpDateMonth(int ExpDateMonth){
        this.expdatemonth = ExpDateMonth;
    }
     public int getExpDateYear(){
        return expdateyear;
    }
    public void setExpDateYear(int ExpDateYear){
        this.expdateyear = ExpDateYear;
    }
    public int getCVV(){
        return cvv;
    }
    public void setCVV(int CVV){
        this.cvv = CVV;
    }
}
