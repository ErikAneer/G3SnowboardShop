/*

 */
package EntityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Erik
 */


@Entity
@NamedQueries({
        @NamedQuery(name="Product.getAll", query="select p from Product p"),
        @NamedQuery(name="Product.getByBrand", query="select p from Product p WHERE p.brand = :brand" ),
        @NamedQuery(name="Product.getByName", query="select p from Product p WHERE p.name = :name" ),
        @NamedQuery(name="Product.getByType", query="select p from Product p WHERE p.type = :type" ),
        @NamedQuery(name="Product.getByPrice", query="select p from Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice" ),
        @NamedQuery(name="Product.getBySizeSpan", query="select p from Product p WHERE p.productSize >= :minSize AND p.productSize <= :maxSize" ),
        @NamedQuery(name="Product.getByLengthSpan", query="select p from Product p WHERE p.productLength >= :minLength AND p.productLength <= :maxLength" )
})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //@Basic(fetch=LAZY)
    
    @NotNull
    private String productType; // available "snowboard, binding, boot"

    @NotNull
    @Size(max = 20, min = 5)
    private String brand;
    @NotNull
    @Size(max = 20, min = 5)
    private String name;
    private double productLength;
    private String productSize;
    @NotNull
    private String description;
    @NotNull
    private double price;
    @NotNull
    private double premiumPrice;
    @NotNull
    private String imagePath;

    public Product(String productType, String brand, String name, double productLength, String description, double price, double premiumPrice, String imagePath) {
        this.productType = productType;
        this.brand = brand;
        this.name = name;
        this.productLength = productLength;
        this.description = description;
        this.price = price;
        this.premiumPrice = premiumPrice;
        this.imagePath = imagePath;
    }

    public Product(String productType, String brand, String name, String productSize, String description, double price, String imagePath) {
        this.productType = productType;
        this.brand = brand;
        this.name = name;
        this.productSize = productSize;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPremiumPrice() {
        return premiumPrice;
    }

    public void setPremiumPrice(double price) {
        this.premiumPrice = price * 0.9 ;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getProductLength() {
        return productLength;
    }

    public void setProductLength(double productLength) {
        this.productLength = productLength;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClasses.Product[ id=" + id + " ]";
    }
    
}
