/*

 */
package EJB;

import EntityClasses.CartItem;
import EntityClasses.Order;
import EntityClasses.User3;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Erik
 */
@Stateless
@LocalBean
public class OrderBean {

    @PersistenceContext(unitName = "SnowboardShop-ejbPU")
    private EntityManager em;

    public Object registerOrder(Object customer, Object orderItems, String streetAddress, String postalAddress, String postalCode) {
            
        
        User3 orderCustomer = (User3) customer;
        List<CartItem> items = (List<CartItem>) orderItems;
        Order order = new Order("TestNumber", orderCustomer, items, streetAddress, postalAddress, postalCode);
        //(String orderNumber, User3 customer, List<CartItem> orderitems, String streetAddress, String postalAddress, String postalCode) 
        //Orderning3 order = new Orderning3(ordernr, email, fullname, productname, count, totalprice, fulladdress, postnraddress, telephone, u3);
        orderCustomer.getOrders().add(order);
        
        //List<Orderning3> orders = u3.getOrders();
        //orders.add(order);
        //u3.setOrders(orders);
        em.persist(order);
        
        return order;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    //User3, List<CartItem> String streetAddress, String postalAddress, int postalCode

    public void persist(Object object) {
        em.persist(object);
    }
    
}
