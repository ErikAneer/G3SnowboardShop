/*

 */
package Controllers;

import EntityClasses.Product;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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

    /**
     * Creates a new instance of NavigationController
     */
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

    public String logInLogOut(String currentPage) {
        String page = "";
       refreshVisitedPages(currentPage);
        
        if (!userController.getIsLoggedIn()) {
            page = "login";
        } else { 
            page = "logout";
        }
        return page;
    }

    public String loginUser(String currentPage) {
        refreshVisitedPages(currentPage);
        return userController.login();
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

    public String goToCart(String currentPage) {
        refreshVisitedPages(currentPage);
        return userController.visaKorg(userController.getCurrentUser().getEmail());

    }

}