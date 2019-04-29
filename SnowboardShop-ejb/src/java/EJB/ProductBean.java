/*

 */
package EJB;

import EntityClasses.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Erik
 */
@Stateless
@LocalBean
public class ProductBean {

    @PersistenceContext(unitName = "SnowboardShop-ejbPU")
    private EntityManager em;

    private List<Product> products;

    /**
     * Get the value of products
     *
     * @return the value of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Set the value of products
     *
     * @param products new value of products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Product> getAllBoards() {
        
        Query query = em.createNamedQuery("Product.getByType");
        query.setParameter("productType", "snowboard");
        List<Product> results = query.getResultList();
        return results;
    }
    
    public List<Product> getAllBoots() {
        
        Query query = em.createNamedQuery("Product.getByType");
        query.setParameter("productType", "boot");
        List<Product> results = query.getResultList();
        return results;
    }

    //Do only use if saving new objects to DB.
    public void saveProductToDB() {
        persist(new Product("snowboard", "Ride", "Warpig S", 148, "Best grom board out there!", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
       persist(new Product("snowboard", "Burton", "Chicklet 120", 120, "Best grom board out there!", 1890, 1890,  "/resources/images/Chicklet1152019Snowboardresized.jpg"));
        persist(new Product("snowboard", "Burton", "Custom Smalls", 140, "Best grom board out there!", 3490, 3490,  "/resources/images/CustomSmalls1402019Snowboardresized.jpg"));
        persist(new Product("snowboard", "Burton", "Process 162", 162, "Best grom board out there!", 4490, 3490,  "/resources/images/Process1622019Snowboardresized.jpg"));
        persist(new Product("snowboard", "Burton", "Air Retro 156", 156, "Best grom board out there!", 3490, 3490,  "/resources/images/SpeedDateRetro1562019Snowboardresized.jpg"));
        persist(new Product("snowboard", "Burton", "TWC Pro 156", 156, "Best grom board out there!", 499990, 499990,  "/resources/images/BurtonTWCPro.jpg"));
        
        persist(new Product("boot", "32", "Lashed Melancon", 42, "Description", 3490, 3490, "/resources/images/32LashedMelancon2019Snowboardboots.jpg"));
        persist(new Product("boot", "32", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        persist(new Product("boot", "32", "X BT Light", 42, "Description", 3490, 3490, "/resources/images/32XBlueTomatoLight2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
    }
    
    
   public void createProductList(){
        
        products.add(new Product("snowboard", "Burton", "Chicklet 120", 120, "Best grom board out there!", 1890, 1890,  "/resources/images/Chicklet1152019Snowboardresized.jpg"));
        products.add(new Product("snowboard", "Burton", "Custom Smalls", 140, "Best grom board out there!", 3490, 3490,  "/resources/images/CustomSmalls1402019Snowboardresized.jpg"));
        products.add(new Product("snowboard", "Burton", "Process 162", 162, "Best grom board out there!", 4490, 3490,  "/resources/images/Process1622019Snowboardresized.jpg"));
        products.add(new Product("snowboard", "Burton", "Air Retro 156", 156, "Best grom board out there!", 3490, 3490,  "/resources/images/SpeedDateRetro1562019Snowboardresized.jpg"));
        products.add(new Product("snowboard", "Burton", "TWC Pro 156", 156, "Best grom board out there!", 499990, 499990,  "/resources/images/BurtonTWCPro.jpg"));
        
        products.add(new Product("boot", "32", "Lashed Melancon", 42, "Description", 3490, 3490, "/resources/images/32LashedMelancon2019Snowboardboots.jpg"));
        products.add(new Product("boot", "32", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        products.add(new Product("boot", "32", "X BT Light", 42, "Description", 3490, 3490, "/resources/images/32XBlueTomatoLight2019Snowboardboots.jpg"));
        products.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        products.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        products.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        products.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        products.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        products.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
   
   
   }
    
    
}
