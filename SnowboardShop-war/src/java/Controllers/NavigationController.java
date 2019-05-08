/*

 */
package Controllers;

import EntityClasses.Product;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Erik
 */
@Named(value = "navigationController")
@SessionScoped
public class NavigationController implements Serializable {

    private String previousPage;
    private String secondPreviousPage;
    private UserController userCon;

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

    public String navigate(String pageTo, String currentPage) {
        secondPreviousPage = previousPage;
        previousPage = currentPage;
        return pageTo;
    }

    public String logInLogOut(String currentPage, UserController u) {
        String page = "";
        secondPreviousPage = previousPage;
            previousPage = currentPage;
        
        if (!u.getIsLoggedIn()) {
            page = "login";
        } else { 
            page = "logout";
        }
        return page;
    }

    public String loginUser(String currentPage, UserController u) {
        secondPreviousPage = previousPage;
        previousPage = currentPage;
        return u.login();
    }

    public String logout(UserController u) {
        secondPreviousPage = "";
        previousPage = "";
        u.logOut();
        return "logout";
    }

    public String navigateToProduct(String currentPage, ProductController pCon, Product product) {
        secondPreviousPage = previousPage;
        previousPage = currentPage;
        return pCon.showSelectedProduct(product);
    }

    public String goToCart(String currentPage, UserController u) {
        secondPreviousPage = previousPage;
        previousPage = currentPage;
        return u.visaKorg(u.getCurrentUser().getEmail());

    }

}
