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
public class Products {
    
    private int id;
    private String name;
    private int quantity;
    private int price;
    private String category;
    private byte[] Image;
    private String description;
    
    public Products(){}
    
    public Products(int ID,String Name, int Quantity, int Price, String Category, byte[] image, String Description){
        this.id = ID;
        this.name = Name;
        this.quantity = Quantity;
        this.price = Price;
        this.category = Category;
        this.Image = image;
        this.description = Description;
    }

    public void setImage(byte[] Image) {
        this.Image = Image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int Quantity){
        this.quantity = Quantity;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int Price){
        this.price = Price;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String Category){
        this.category = Category;
    }
    public byte[] getMyImage(){
        return Image;
    }
}
