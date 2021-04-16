/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

/**
 *
 * @author tavon
 */
public class Admin {
    
    private String name;
    private int id;
    private String password;
    
    public Admin(){
    
    }
    
    public Admin(String Name, int ID, String Password){
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
