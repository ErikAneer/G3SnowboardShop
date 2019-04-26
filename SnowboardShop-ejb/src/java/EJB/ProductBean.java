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
        
        persist(new Product("snowboard", "Capita", "Children OTG", 149, "Best grom board out there!", 3190, 3190,  "/resources/images/ChildrenOfTheGnar149Snowboardresized.jpg"));
        persist(new Product("snowboard", "Ride", "Compact 154", 154, "Best grom board out there!", 3890, 3890,  "/resources/images/Compact1542019Snowboard.jpg"));
        persist(new Product("snowboard", "Burton", "Custom FV 154", 1154, "Best grom board out there!", 6290, 6290,  "/resources/images/CustomFlyingV1542019Snowboardresized.jpg"));
        
        persist(new Product("snowboard", "Bataleon", "Distortia 149", 149, "Best grom board out there!", 4690, 4690,  "/resources/images/BataleonDistortia1492019Snowboard.jpg"));
        persist(new Product("snowboard", "Capita", "Jess Kimura", 150, "Best grom board out there!", 4890, 4890,  "/resources/images/CapitaJessKimuraPro1502019Snowboard.jpg"));
        persist(new Product("snowboard", "Bataleon", "Minishred 100", 100, "Best grom board out there!", 1890, 1890,  "/resources/images/BataleonMinishred100MiniShredBdg2019Snowboardpaket.jpg"));
        persist(new Product("snowboard", "Bataleon", "Push UP 149", 149, "Best grom board out there!", 4890, 4890,  "/resources/images/BataleonPushUp1492019Snowboard.jpg"));
        persist(new Product("snowboard", "Ride", "Saturday 146", 146, "Best grom board out there!", 4490, 4490,  "/resources/images/RideSaturday1462019Snowboard.jpg"));
        persist(new Product("snowboard", "Capita", "Space M Fantasy", 149, "Best grom board out there!", 3990, 3990,  "/resources/images/CapitaSpaceMetalFantasy1492019Snowboard.jpg"));
        persist(new Product("snowboard", "Capita", "Ultrafear 151", 151, "Best grom board out there!", 5290, 5290,  "/resources/images/CapitaUltrafear1512019Snowboard.jpg"));
        
        persist(new Product("boot", "32", "Lashed Melancon", 42, "Description", 3490, 3490, "/resources/images/32LashedMelancon2019Snowboardboots.jpg"));
        persist(new Product("boot", "32", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        persist(new Product("boot", "32", "X BT Light", 42, "Description", 3490, 3490, "/resources/images/32XBlueTomatoLight2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Grom Boa", 29, "Description", 1290, 1290, "/resources/images/BurtonGromBoa2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Ion Boa Black", 42, "Description", 4590, 4590, "/resources/images/BurtonIonBoa2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Limelight Boa", 39, "Description", 3090, 3090, "/resources/images/BurtonLimelightBoa2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Photon Boa", 42, "Description", 3790, 3790, "/resources/images/BurtonPhotonBoa2019Snowboardboots.jpg"));
        persist(new Product("boot", "Burton", "Ruler Speed Z", 42, "Description", 2790, 2790, "/resources/images/BurtonRulerSnowboardboots.jpg"));
        persist(new Product("boot", "Ride", "Lasso  Black", 42, "Description", 3190, 3190, "/resources/images/RideLasso2019Snowboardboots.jpg"));
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
