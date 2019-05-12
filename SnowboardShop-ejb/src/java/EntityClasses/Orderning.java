/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Julia
 */
@Entity
public class Orderning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(fetch=LAZY)
    private String ordernr, email, fullname, productname;
    private int count;
    private double totalprice;
    private String fulladdress, postnraddress, telephone;

    public Orderning(String ordernr, String email, String fullname, String productname, int count, double totalprice, String fulladdress, String postnraddress, String telephone) {
        this.ordernr = ordernr;
        this.email = email;
        this.fullname = fullname;
        this.productname = productname;
        this.count = count;
        this.totalprice = totalprice;
        this.fulladdress = fulladdress;
        this.postnraddress = postnraddress;
        this.telephone = telephone;
    }
    

    public Orderning() {
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
        if (!(object instanceof Orderning)) {
            return false;
        }
        Orderning other = (Orderning) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Orderning[ id=" + id + " ]";
    }
    
}
