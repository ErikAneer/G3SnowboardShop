/*

 */
package Controllers;

import EJB.OrderBean;
import EntityClasses.CartItem;
import EntityClasses.Order;
import EntityClasses.Product;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Erik
 */
@Named(value = "cartController")
@SessionScoped
public class cartController implements Serializable {

    @EJB
    private OrderBean orderBean;

    
    
    @Inject 
    UserController userController;
    
    private List<CartItem> cartitems  = new ArrayList();
    private int totalNumberCartItems;
    /**
     * Creates a new instance of cartController
     */
    public cartController() {
    }

    public List<CartItem> getCartitems() {
        return cartitems;
    }

    public void setCartitems(List<CartItem> cartitems) {
        this.cartitems = cartitems;
    }

    public int getTotalNumberCartItems() {
        return totalNumberCartItems;
    }

    public void setTotalNumberCartItems(int totalNumberCartItems) {
        this.totalNumberCartItems = totalNumberCartItems;
    }
    
    public void addItemToCart(Product p){
        
            boolean isItemInCart = false;
            for(CartItem i : cartitems) {
                    if (i.getItem().getName().equals(p.getName())) {
                        i.setItemQuantity(i.getItemQuantity()+1);
                        isItemInCart = true;
                    }
            }
            if(!isItemInCart) {
                cartitems.add(new CartItem(p,1));
            }
            countCartItems();
    }
    
    public void removeItemfromCart(Product p){

             for(int i=0; i<cartitems.size(); i++){
                    if (cartitems.get(i).getItem().getName().equals(p.getName())) {
                        if(cartitems.get(i).getItemQuantity() > 1) {
                                cartitems.get(i).setItemQuantity(cartitems.get(i).getItemQuantity()-1);
                        }
                        else {
                                cartitems.remove(i);
                        }
                    }
            }
            countCartItems();
    }
    
    public void countCartItems() {
            int items = 0;
            for(CartItem i : cartitems) {
                  items += i.getItemQuantity();
            }   
        setTotalNumberCartItems(items);
    }
    
    public Order makeOrder(){
        Order order = null;
        //User3, List<CartItem> String streetAddress, String postalAddress, int postalCode
        orderBean.registerOrder(order, orderBean, userController.get, postalAddress, postalCode);
        currentUser
                userController.
        return order;
    }
    
}
