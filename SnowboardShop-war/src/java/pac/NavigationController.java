/*

 */
package pac;

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
public class NavigationController implements Serializable{

    private String previousPage;
    private String secondPreviousPage;
    private UserController userCon;
    /**
     * Creates a new instance of NavigationController
     */
    public NavigationController() {
        previousPage="";
        secondPreviousPage="";
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
    
    public String logInLogOut(String currentPage, UserController u){
            if(u.getIsLoggedIn()) {
                u.logOut();
                secondPreviousPage = "";
                previousPage="";
                return "logout";
            }
            userCon= u;
            secondPreviousPage = previousPage;   
            previousPage = currentPage;
                return "login"; 
    }
    
    public String login(String currentPage, UserController u){
            secondPreviousPage = previousPage;   
            previousPage = currentPage;
            
            u.setLoggedInStatus("Logga ut");
            return u.login();
    }
    
    public String logout(UserController u){
        u.logOut();
        return "index";
    }
    
    
}
