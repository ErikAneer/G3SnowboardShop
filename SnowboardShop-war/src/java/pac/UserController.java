
package pac;

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
    private String userField;
    
    private String firstname, familyname, telephone, address, postnr, postaddress, email, code, status;
    
    private String confirmPassword;


    /**
     * Creates a new instance of LoginBean //(String firstName, String familyName, String email, String password, String status)
     */
    public UserController() {
        status = "Logga in";
        //users = snowBean.callArraylist();
        /*for(Object o: userObj){
            users.add((User2)o);
        }*/
        /*for(User2 k: users){
            if((k.getStatus().equals("customer")) || k.getStatus().equals("premium")){
                kunder.add(k);
            }
        }*/
        /*if(users.size()==0){
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
        }*/
        
    }
   
    public String login() {
            String page = "Logga in";
            User2 u = (User2)snowBean.login(email, code);
            currentUser = u;
            page = u.getStatus();
            
            users = snowBean.callAllUsers();
            kunder = snowBean.callAllKunders("customer", "premium");
            
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
          return page;
    }

   public String logOut(){
        currentUser = null;
        return "index";
    }
    

    
    /*public void validateUniquePassword(FacesContext context, UIComponent component, Object value) throws ValidatorException{
    
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
        
    }*/
   /* protected boolean continueValidation() {
        String skipValidator = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("skipValidator");
        if (skipValidator != null && skipValidator.equalsIgnoreCase("true")) {
            return false;
        }
        return true;
    }*/
    
    
    public String registerNewCustomer(){
        snowBean.save(firstname, familyname, telephone, address, postnr, postaddress, email, code, "customer");
        return login();
    }
    
    public void clearRegisterForm() {
        firstname = null; familyname = null; telephone=null; address=null; postnr=null; postaddress=null; email=null; code=null; confirmPassword=null;
    
    }

    public List<User2> getUsers() {
        return users;
    }

    public void setUsers(List<User2> users) {
        this.users = users;
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
}