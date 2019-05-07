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
    private List<Product> allProducts = new ArrayList();
    private List<Product> boards = new ArrayList();
    private List<Product> boots = new ArrayList();
    private List<Product> bindings = new ArrayList();
    private String message;
    private String nameSuggestions;
    
    private Product  selectedProduct;

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
    private void addBoardsToList() {
        boards = productBean.getAllBoards(); 
    }
    
    private void addBootsToList() {
        boots= productBean.getAllBoots();
    }
    private void addAllProductsToList() {
        allProducts= productBean.getAllProducts();
    }
    private void addBindingsToList() {
        bindings= productBean.getAllBindings();
    }

    public void onload() {
         //productBean.saveProductToDB();
         addBoardsToList();
         addBootsToList();  
         addBindingsToList();
         addAllProductsToList();
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
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
        for (Product p : allProducts) {
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
        this.inputName = inputName;
    }
    
    public String showSelectedProduct(Product p) {
        setSelectedProduct(p);
        return "show_details";
    }
    
    public void findMatchingProduct() {
            allProducts.forEach(p->{
            if((p.getBrand() + " " + p.getName()).equals(inputName)){
                setSelectedProduct(p);
            }
            });
    }
    
    public String showMatchingProduct(){
            findMatchingProduct();
            setInputName("");
             return "show_details";
    }      
    public String returnToIndex() {
        setSelectedProduct(null);
        
        return "return_index";
    
    }

}
            
