
package Controllers;

import EntityClasses.Product;
import EntityClasses.User3;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Erik
 */
@Named(value = "navigationController")
@SessionScoped
public class NavigationController implements Serializable {

    @Inject
    UserController userController;
    
    @Inject
    ProductController productController;
    
    private String previousPage;
    private String secondPreviousPage;

    public NavigationController() {
        previousPage = "";
        secondPreviousPage = "";
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    public String getSecondPreviousPage() {
        return secondPreviousPage;
    }

    public void setSecondPreviousPage(String secondPreviousPage) {
        this.secondPreviousPage = secondPreviousPage;
    }
    
    public void refreshVisitedPages(String currentPage){
        secondPreviousPage = previousPage;
        previousPage = currentPage;
    }

    public String navigate(String pageTo, String currentPage) {
        refreshVisitedPages(currentPage);
        return pageTo;
    }
     public String navigateFromProduct(String pageTo, String currentPage) {
        refreshVisitedPages(currentPage);
        productController.setSelectedProduct(null);
        return pageTo;
    }

    public String logInLogOut(String currentPage) {
        String page = "";
       refreshVisitedPages(currentPage);
        
        if (!userController.getIsLoggedIn()) {
            page=  "login";
        } else { 
            page = "logout";
        }
        return page;
    }

    public String loginUser(String currentPage) {
        String pageTo="";
        String loginResult = userController.login(); 
       
        if (loginResult.equals("loginFailure")){
            return "loginFailure";
        }
         if(userController.getCurrentUser() != null && userController.getCurrentUser().getStatus().equals("admin")){
            pageTo = "admin";
        }
        else {
            pageTo = previousPage;
            refreshVisitedPages(currentPage);
        }
        return pageTo;
    }

    public String logout() {
        secondPreviousPage = "";
        previousPage = "";
        userController.logOut();
        return "logout";
    }

    public String navigateToProduct(String currentPage, Product product) {
        refreshVisitedPages(currentPage);
        return productController.showSelectedProduct(product);
    }
    
     public String  navigateToProductAutocomplete(String currentPage) {
        refreshVisitedPages(currentPage);
        productController.findMatchingProduct();
        return productController.showSelectedProductAutoComplete();
    }

    public String goToCart(String currentPage) {
        productController.setSelectedProduct(null);
        refreshVisitedPages(currentPage);
        if(userController.getCurrentUser() == null){
            return "cart";
        }
        return userController.visaKorg(userController.getCurrentUser().getEmail());

    }
    
    public String navigateToCompleteOrder(String currentPage){
        refreshVisitedPages(currentPage);
        if(userController.getIsLoggedIn()) {
            String test1 = userController.saveChange(userController.getCurrentUser());
            String test2 = userController.buyAll(userController.getCurrentUser(), userController.getProducts());
            return "orderCompleted";
        }
        return "login";
    }
    public String navigateFromRegisterPage(String currentPage){
        String pageTo = "";
       
        String emailCheck = userController.registerNewCustomer();
        
         if (emailCheck.equals("emailExists")){
            return "registerFailure";
        }
        else {
            pageTo = previousPage;
            refreshVisitedPages(currentPage);
        }
        return pageTo;
    }
    
    public String navigateCustomerDetails(String currentPage, User3 u) {
        refreshVisitedPages(currentPage);
        return userController.showOrderNrs(u);
    }

}
