/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Julia
 */
@Entity
public class User3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String firstname, familyname, telephone, address, postnr, postaddress, email, code, status;

    @OneToMany(mappedBy="user3")
    private List<Orderning3> orders = new ArrayList();

    public User3(String firstname, String familyname, String telephone, String address, String postnr, String postaddress, String email, String code, String status) {
        this.firstname = firstname;
        this.familyname = familyname;
        this.telephone = telephone;
        this.address = address;
        this.postnr = postnr;
        this.postaddress = postaddress;
        this.email = email;
        this.code = code;
        this.status = status;
    }

    
    public User3() {
    }

    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getPostaddress() {
        return postaddress;
    }

    public void setPostaddress(String postaddress) {
        this.postaddress = postaddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Orderning3> getOrders() {
        return orders;
    }

    public void setOrders(List<Orderning3> orders) {
        this.orders = orders;
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
        if (!(object instanceof User3)) {
            return false;
        }
        User3 other = (User3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.User3[ id=" + id + " ]";
    }
    
}
