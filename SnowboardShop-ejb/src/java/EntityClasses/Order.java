/*

 */
package EntityClasses;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Erik
 */
@Entity
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String orderNumber;
    
    @ManyToOne
    @JoinColumn(name="USER3_ID", referencedColumnName="ID")
    private User3 customer;
    
    
    private List<CartItem> orderitems;
    private int totalOrderItems;
    private double totalOrderSum ;
    
    private String streetAddress, postalAddress, postalCode;


    public Order(String orderNumber, User3 customer, List<CartItem> orderitems, String streetAddress, String postalAddress, String postalCode) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.orderitems = orderitems;
        this.streetAddress = streetAddress;
        this.postalAddress = postalAddress;
        this.postalCode = postalCode;
    }

    public Order() {
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User3 getCustomer() {
        return customer;
    }

    public void setCustomer(User3 customer) {
        this.customer = customer;
    }

    public List<CartItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<CartItem> orderitems) {
        this.orderitems = orderitems;
    }

    public int getTotalOrderItems() {
        return totalOrderItems;
    }

    public void setTotalOrderItems(int totalOrderItems) {
        this.totalOrderItems = totalOrderItems;
    }

    public double getTotalOrderSum() {
        return totalOrderSum;
    }

    public void setTotalOrderSum(double totalOrderSum) {
        this.totalOrderSum = totalOrderSum;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public void setTotalOrderSum(){
        orderitems.forEach((c) -> {
            totalOrderSum += c.getTotalPrice();
        });     
    }
    public void setTotalOrderItems(){
        orderitems.forEach((c) -> {
            totalOrderItems += c.getItemQuantity();
        });   
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Order[ id=" + id + " ]";
    }
    
}
