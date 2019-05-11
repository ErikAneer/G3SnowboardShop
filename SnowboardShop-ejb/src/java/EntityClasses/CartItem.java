/*

 */
package EntityClasses;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Erik
 */
@Entity
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Product item;
    
    private int itemQuantity;
    private double unitPrice;
    private double totalPrice;

    public CartItem(Product item, int itemQuantity) {
        this.item = item;
        this.itemQuantity = itemQuantity;
    }

    public CartItem() {
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice, User3 customer) {
        if (customer.getStatus().equals("premium")) {
               this.unitPrice= this.getItem().getPremiumPrice();
            }
            else {
              this.unitPrice= this.getItem().getPrice();
            }
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = itemQuantity * unitPrice;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof CartItem)) {
            return false;
        }
        CartItem other = (CartItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.CartItem[ id=" + id + " ]";
    }
    
}
