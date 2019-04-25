
package pac;

import EntityClasses.SnowBeanLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    
    private List<User> users = new ArrayList();
    private List<User> kunder = new ArrayList();
    private String status;
    private User currentUser;
    private Boolean isLoggedIn = false;
    private String userField;
    
    private String firstName, familyName, phoneNumber, streetAddress, postalCode, postalAddress, email, password, confirmPassword;


    /**
     * Creates a new instance of LoginBean //(String firstName, String familyName, String email, String password, String status)
     */
    public UserController() {
        status = "Logga in";
        if(users.size()==0){
            users.add(new User("Erik", "Aneer", "erik@test.nu", "1234", "customer"));
            users.add(new User("aaaa", "aaaa", "aaaa@test.nu", "1234", "customer"));
            users.add(new User("bbbb", "bbbb", "bbbb@test.nu", "1234", "customer"));
            users.add(new User("cccc", "cccc", "cccc@test.nu", "1234", "customer"));
            users.add(new User("ffff", "ffff", "ffff@test.nu", "1234", "premium"));
            users.add(new User("gggg", "gggg", "gggg@test.nu", "1234", "premium"));
            users.add(new User("Admin 1", "dddd", "dddd@test.nu", "1234", "admin"));
            users.add(new User("Admin 2", "eeee", "eeee@test.nu", "1234", "admin"));           
        }
        if(kunder.size() == 0){
            for(User k: users){
                if((k.getStatus().equals("customer")) || k.getStatus().equals("premium")){
                    kunder.add(k);
                }
            }
        }
        
    }
   
    public void login() {
            String page = "Logga in";
            status = snowBean.log();
            
            //Method needed to check if inserted email address exists among registererd users. ex.
            
            /*for(User u: users){
                if((password.matches(u.getPassword())) && email.matches(u.getEmail())){
                    currentUser = u;
                    //setUserField(u.getFirstName());
                    setIsLoggedIn(true);
                    page = currentUser.getStatus();
                    setStatus("Logga ut");
                    setEmail(null);                 
                    break;
                }
            }
            if(page.equals("Logga in")){
                setEmail(null);
                setPassword(null);
                FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Sorry, ditt användarnamn och/eller lösenord stämmer inte! Försök igen.", null);
                FacesContext.getCurrentInstance().addMessage("loginForm:loginButton", javaTextMsg); 
           }*/
          // return page;
    }

    public String logOut(){
        currentUser = null;
        return "index";
    }
    
        public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getKunder() {
        return kunder;
    }

    public void setKunder(List<User> kunder) {
        this.kunder = kunder;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public String getPassword() {
        return password;
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
    
    

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void validateUniquePassword(FacesContext context, UIComponent component, Object value) throws ValidatorException{
    
        if (!continueValidation()) {
            return;
        }
        System.out.println("Validate unique email method is called.");
         String password = (String) value;
        boolean isUnique = true;
        for(User u : users) {
            if (u.getPassword().equals(password)) {
                isUnique = false;
            }
        }
        
        if (!isUnique) {
            String message = "Den angivna mailadressen finns redan registrerad hos oss som en befintlig kund";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));

        }
        
    }
    protected boolean continueValidation() {
        String skipValidator = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("skipValidator");
        if (skipValidator != null && skipValidator.equalsIgnoreCase("true")) {
            return false;
        }
        return true;
    }
    
    
    public String registerNewCustomer(){
        users.add(new User(firstName, familyName, email, password, "customer"));
        kunder.add(new User(firstName, familyName, email, password, "customer"));
        return "go";
        //return login();
    }
    
    public void clearRegisterForm() {
        firstName = null; familyName = null; phoneNumber=null; streetAddress=null; postalCode=null; postalAddress=null; email=null; password=null; confirmPassword=null;
    
    }
}