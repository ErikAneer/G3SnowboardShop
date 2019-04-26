package pac;

import EJB.ProductBean;
import EntityClasses.Product;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Erik
 */
@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable {

    @EJB //injecerar en ejb
    private ProductBean productBean;
    private List<Product> boards = new ArrayList();
    private List<Product> boots = new ArrayList();
    private List<Product> bindings = new ArrayList();
    private String message;
    private String nameSuggestions;

    /**
     * Creates a new instance of ProductController
     */
    public ProductController() {
    }

    public List<Product> getBoards() {
        return boards;
    }

    public void setBoards(List<Product> boards) {
        this.boards = boards;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNameSuggestions() {
        return nameSuggestions;
    }

    public void setNameSuggestions(String nameSuggestions) {
        this.nameSuggestions = nameSuggestions;
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public List<Product> getBoots() {
        return boots;
    }

    public void setBoots(List<Product> boots) {
        this.boots = boots;
    }

    public List<Product> getBindings() {
        return bindings;
    }

    public void setBindings(List<Product> bindings) {
        this.bindings = bindings;
    }

    
    
    private void createBoards() {
        
        //type productType, String brand, String name, double productLength, String description, double price, double premiumPrice, String imagePath
        boards.add(new Product("snowboard", "Burton", "Chicklet 120", 120, "Best grom board out there!", 1890, 1890,  "/resources/images/Chicklet1152019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Burton", "Custom Smalls", 140, "Best grom board out there!", 3490, 3490,  "/resources/images/CustomSmalls1402019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Burton", "Process 162", 162, "Best grom board out there!", 4490, 3490,  "/resources/images/Process1622019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Burton", "Air Retro 156", 156, "Best grom board out there!", 3490, 3490,  "/resources/images/SpeedDateRetro1562019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Burton", "TWC Pro 156", 156, "Best grom board out there!", 499990, 499990,  "/resources/images/BurtonTWCPro.jpg"));
        boards.add(new Product("snowboard", "Ride", "Warpig S", 148, "Best grom board out there!", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
       
        /*
        boards.add(new Product("snowboard", "Ride", "Warpig S", 148, "Best grom board out there!", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Ride", "Warpig S", 148, "Best grom board out there!", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Ride", "Warpig S", 148, "Best grom board out there!", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Ride", "Warpig S", 148, "Best grom board out there!", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Ride", "Warpig S", 148, "Best grom board out there!", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Ride", "Warpig S", 148, "Best grom board out there!", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
        boards.add(new Product("snowboard", "Ride", "Warpig S", 148, "Best grom board out there!", 4590, 4590,  "/resources/images/Warpig1482019Snowboardresized.jpg"));
        */
    }
    
    private void createBoots() {
        
        boots.add(new Product("boot", "32", "Lashed Melancon", 42, "Description", 3490, 3490, "/resources/images/32LashedMelancon2019Snowboardboots.jpg"));
        boots.add(new Product("boot", "32", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        boots.add(new Product("boot", "32", "X BT Light", 42, "Description", 3490, 3490, "/resources/images/32XBlueTomatoLight2019Snowboardboots.jpg"));
        boots.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        boots.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        boots.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        boots.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        boots.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
        boots.add(new Product("boot", "Burton", "Jones MTB", 42, "Description", 3490, 3490, "/resources/images/32JonesMTB2019Snowboardboots.jpg"));
    
    }

    public void onload() {
        createBoards();
         createBoots();
    }

    /**
     * Auto-completes product names from the Product List
     *
     * @param text
     * @return
     */
    private String inputName;

    public List<String> nameSuggestions(String enteredValue) {
        List<String> matches = new ArrayList<>();
        //using data factory for getting suggestions
        for (Product p : boards) {
            if ((p.getBrand().toLowerCase() + " " + p.getName().toLowerCase()).contains(enteredValue.toLowerCase())) {
                matches.add(p.getBrand()+ " " + p.getName());
            }
        }
        return matches;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        System.out.println(inputName);
        this.inputName = inputName;
    }
}
