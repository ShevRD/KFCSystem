/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

import DataManager.CartDataManager;
import DataManager.CustomerDataManager;
import DataManager.EmployeeDataManager;
import DataManager.InventoryDataManager;
import DataManager.OrderDataManager;
import DataManager.PaymentDataManager;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import Security.SecurityManager;

/**
 *
 * @author tavon
 */
public class Facade {
    private InventoryDataManager inventory;
    private CartDataManager cart;
    private OrderDataManager orderC;
    private PaymentDataManager pay;
    private CustomerDataManager cust;
    private EmployeeDataManager emp;
    private SecurityManager secman;
    
    
    public Facade(){
        inventory = new InventoryDataManager();
        cart = new CartDataManager();
        orderC= new OrderDataManager();
        pay = new PaymentDataManager();
        cust = new CustomerDataManager();
        emp = new EmployeeDataManager();
        secman = new SecurityManager();
    
    }
    
    public void registerCustomer(String Name, int ID, String Password){
        Customer customer = new Customer();
        customer.setName(Name);
        customer.setId(ID);
        customer.setPassword(Password);
        String hashedPassword=secman.hashPassword(customer.getPassword(),customer.getId());
        customer.setPassword(hashedPassword);
        cust.registerCustomer(customer);
    
    }
    
    public void getCustomer(String Name, int ID, String Password){
        Customer customer = new Customer();
        customer.setName(Name);
        customer.setId(ID);
        customer.setPassword(Password);
        String hashedPassword=secman.hashPassword(customer.getPassword(),customer.getId());
        customer.setPassword(hashedPassword);
        cust.getCustomer(customer);
    }
    public void getAdmin(String Name, int ID, String Password){
        Admin admin = new Admin();
        admin.setName(Name);
        admin.setId(ID);
        admin.setPassword(Password);
        emp.getAdmin(admin);
    }
    
    public void getEmployee(String Name, int ID, String Password){
        Employee employee = new Employee();
        employee.setName(Name);
        employee.setId(ID);
        employee.setPassword(Password);
        emp.getEmployee(employee);
    }
    
    public boolean isAuthorized(String cNum, String cExpm, String cExpy, String cCvv){
        long cnum = Long.parseLong(cNum);
        int cexpm = Integer.valueOf(cExpm);
        int cexpy = Integer.valueOf(cExpy);
        int ccvv = Integer.valueOf(cCvv);
        Payment paid = new Payment();
        paid.setCardNum(cnum);
        paid.setExpDateMonth(cexpm);
        paid.setExpDateYear(cexpy);
        paid.setCVV(ccvv);
        boolean res = pay.isAuthorized(paid);
        return res;
    }
    
    
    public boolean placeOrder(String Name, int CID, String Order, int TotalCost, String Status,String Quantity,String Email){
        Order order = new Order();
        order.setName(Name);
        order.setCID(CID);
        order.setOrders(Order);
        order.setTotalCost(TotalCost);
        order.setStatus(Status);
        order.setQTy(Quantity);
        order.setEmail(Email);
        boolean res = orderC.placeOrder(order);
        return res;
    }
    
    public ArrayList<String> grabDetails(){
        ArrayList<String> list  = new ArrayList<>();
        list = orderC.grabDetails();
        return list; 
    }
    
    public ArrayList<Order> viewOrders(){
        return orderC.viewOrders();
    }
    
    public ArrayList<Order> receiveOrder(){
        return orderC.receiveOrder();
    }
    
    public boolean cancelOrder(String id) throws ParseException{
        boolean res = orderC.cancelOrder(id);
        return res;
    }
    
    public void notifyCustomer(String recepient, String Msg) throws Exception{
        orderC.notifyCustomer(recepient, Msg);
    }
    
    public ArrayList<Products> GenerateCart(){
        return cart.GenerateCart();
    }
    
    public boolean addtoCart(int ID, String Name, int QtyInStock, int Price, String Category){
        Products product = new Products();
        product.setId(ID);
        product.setName(Name);
        product.setQuantity(QtyInStock);
        product.setPrice(Price);
        product.setCategory(Category);
        boolean res = cart.addtoCart(product);
        return res;
        
    }
    
    public int calculateTotal(int Quantity, int ID){
        Order order = new Order();
        String quantity = Integer.toString(Quantity);
        order.setQTy(quantity);
        order.setId(ID);
        int total = cart.calculateTotal(order);
        return total;
    }
    
    
    public boolean removefromCart(int ID){
        Products product = new Products();
        product.setId(ID);
        boolean res = cart.removefromCart(product);
        return res;
    }
    
    public void clearCart(){
        cart.clearCart();

    }
    
    public ArrayList<Products> GenerateTable(){
        return inventory.GenerateTable();
    }
    
    public ArrayList<Products> searchByName(){
        return inventory.searchByName();
    }
    
    public boolean updateQuantityOfProduct(int product_id, int product_quantity){
        Products product= new Products();
        product.setId(product_id);
        product.setQuantity(product_quantity);
        boolean res = inventory.updateInventory(product);
        return res;

    }

    public boolean deleteProductFromInventory(int delid){
        Products product = new Products();
        product.setId(delid);
        boolean res = inventory.deleteProductFromInventory(product);
        return res;
    }
    
    public ArrayList<Products> manageInventory(){
        return inventory.manageInventory();
    }
    
    
    public boolean checkIfProductExists(String newproductname){
        Products newproduct= new Products();
        newproduct.setName(newproductname);
        boolean res = inventory.checkIfProductExists(newproduct);
        return res;
    }

    public boolean addNewProduct(String Name,int stock,int price,String categ, byte[] item_image,String descrip){
        Products newproduct = new Products(0,Name,stock,price,categ, item_image, descrip);
        boolean res=inventory.addNewProduct(newproduct);
        return res;

    }
    
    public void tempProductInsertion(String id){
        inventory.tempProductInsertion(id);
    }
}
