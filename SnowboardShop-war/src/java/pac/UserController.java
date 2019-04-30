package pac;

import EJB.UserBean;
import EntityClasses.SnowBeanLocal;
import EntityClasses.User2;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Erik
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private SnowBeanLocal snowBean;

    private List<User2> users;
    private List<User2> kunder;
    private User2 currentUser;
    private Boolean isLoggedIn = false;
    private String userField, loggedInStatus;

    private String firstname, familyname, telephone, address, postnr, postaddress, email, code, status;

    private String confirmPassword;

    /**
     * Creates a new instance of LoginBean //(String firstName, String
     * familyName, String email, String password, String status)
     */
    public UserController() {
        loggedInStatus = "Logga in";
    }

    public String login() {
        String page = "Logga in";
       
        users = snowBean.callAllUsers();
        kunder = snowBean.callAllKunders("customer", "premium");

        if (!snowBean.checkIfUserExists(email, code)) {
            setEmail(null);
            setCode(null);
            FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Sorry, ditt användarnamn och/eller lösenord stämmer inte! Försök igen.", null);
            FacesContext.getCurrentInstance().addMessage("loginForm:loginButton", javaTextMsg);
        } else {
            User2 u = (User2) snowBean.login(email, code);
            page = u.getStatus();
            currentUser = u;
            //setUserField(u.getFirstName());
            setIsLoggedIn(true);
            status = currentUser.getStatus();
            loggedInStatus = "Logga ut";
            page = status;
            setEmail(null);
        }
        return page;
    }
    
    public String logInLogOut(){
            if(isLoggedIn) {
                logOut();
                return "index";
            }
            else return "login";
    
    }

    public String logOut() {
        loggedInStatus = "Logga in";
        currentUser = null;
        firstname = null;
        familyname = null;
        telephone = null;
        address = null;
        postnr = null;
        postaddress = null;
        email = null; 
        code = null;
        status = null;
        return "index";
    }

    public String registerNewCustomer() {
        snowBean.save(firstname, familyname, telephone, address, postnr, postaddress, email, code, "customer");
        return login();
    }

    public void clearRegisterForm() {
        firstname = null;
        familyname = null;
        telephone = null;
        address = null;
        postnr = null;
        postaddress = null;
        email = null;
        code = null;
        confirmPassword = null;

    }

    public List<User2> getUsers() {
        return users;
    }

    public void setUsers(List<User2> users) {
        this.users = users;
    }

    public String getSameEmailmsg() {
        return sameEmailmsg;
    }

    public void setSameEmailmsg(String sameEmailmsg) {
        this.sameEmailmsg = sameEmailmsg;
    }
    
    

    public List<User2> getKunder() {
        return kunder;
    }

    public void setKunder(List<User2> kunder) {
        this.kunder = kunder;
    }

    public User2 getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User2 currentUser) {
        this.currentUser = currentUser;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getUserField() {
        return userField;
    }

    public void setUserField(String userField) {
        this.userField = userField;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLoggedInStatus() {
        return loggedInStatus;
    }

    public void setLoggedInStatus(String loggedInStatus) {
        this.loggedInStatus = loggedInStatus;
    }
    
    

    public void onload() {

        //snowBean.saveTestUsersToDB();
    }
}
