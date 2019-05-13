package EntityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Julia
 */
@Entity
public class Orderning3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(fetch = LAZY)
    private String ordernr, email, fullname, productname;
    private int count;
    private double totalprice;
    private String fulladdress, postnraddress, telephone;

    @ManyToOne
    @JoinColumn(name = "USER3_ID", referencedColumnName = "ID")
    private User3 user3;

    public Orderning3(String ordernr, String email, String fullname, String productname, int count, double totalprice, String fulladdress, String postnraddress, String telephone, User3 user3) {
        this.ordernr = ordernr;
        this.email = email;
        this.fullname = fullname;
        this.productname = productname;
        this.count = count;
        this.totalprice = totalprice;
        this.fulladdress = fulladdress;
        this.postnraddress = postnraddress;
        this.telephone = telephone;
        this.user3 = user3;
    }

    public Orderning3() {
    }

    public String getOrdernr() {
        return ordernr;
    }

    public void setOrdernr(String ordernr) {
        this.ordernr = ordernr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getFulladdress() {
        return fulladdress;
    }

    public void setFulladdress(String fulladdress) {
        this.fulladdress = fulladdress;
    }

    public String getPostnraddress() {
        return postnraddress;
    }

    public void setPostnraddress(String postnraddress) {
        this.postnraddress = postnraddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User3 getUser3() {
        return user3;
    }

    public void setUser3(User3 user3) {
        this.user3 = user3;
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
        if (!(object instanceof Orderning3)) {
            return false;
        }
        Orderning3 other = (Orderning3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Orderning3[ id=" + id + " ]";
    }

}
