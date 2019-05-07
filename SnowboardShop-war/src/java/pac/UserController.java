package pac;

import EJB.UserBean;
import EntityClasses.Cart;
import EntityClasses.Orderning;
import EntityClasses.Product;
import EntityClasses.SnowBeanLocal;
import EntityClasses.User2;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
    private User2 customer;
    private Boolean isLoggedIn = false;
    private String loggedInStatus;

    private String firstname, familyname, telephone, address, postnr, postaddress, email, code, status;

    private String confirmPassword;
    
    private String sameEmailmsg;
    private List<Cart> products = new ArrayList();
    private List<Orderning> orders = new ArrayList();
    private int cartItems;
    private double summary;

    /**
     * Creates a new instance of LoginBean //(String firstName, String
     * familyName, String email, String password, String status)
     */
    public UserController() {
        loggedInStatus = "Logga in";
        cartItems = 0;
    }

    public String login() {
        String page = "";
        users = snowBean.callAllUsers();
        kunder = snowBean.callAllCustomers("customer", "premium");

        if (!snowBean.checkIfUserExists(email, code)) {
            setEmail(null);
            setCode(null);
            FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Sorry, ditt användarnamn och/eller lösenord stämmer inte! Försök igen.", null);
            FacesContext.getCurrentInstance().addMessage("loginForm:loginButton", javaTextMsg);
        } else {
            User2 u = (User2) snowBean.login(email, code);
            currentUser = u;
            setIsLoggedIn(true);
            loggedInStatus = "Logga ut";
            page = u.getStatus();
            setEmail(null);
        }
        return page;
    }

    public void logOut() {
        loggedInStatus = "Logga in";
        isLoggedIn=false;
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
    }

    public void sameEmail() {
        boolean same = snowBean.isSameEmail(email);
        if (same) {
            sameEmailmsg = "same email";
        } else {
            sameEmailmsg = "ok";
        }
    }

    public String registerNewCustomer() {
        String sidan = "register";
        sameEmail();
        if(sameEmailmsg.equals("ok")){
            snowBean.save(firstname, familyname, telephone, address, postnr, postaddress, email, code, "customer");
            sidan = login();
            
        }
        return sidan;
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

    public List<Cart> getProducts() {
        return products;
    }

    public void setProducts(List<Cart> products) {
        this.products = products;
    }

    public List<Orderning> getOrders() {
        return orders;
    }

    public void setOrders(List<Orderning> orders) {
        this.orders = orders;
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

    public User2 getCustomer() {
        return customer;
    }

    public void setCustomer(User2 customer) {
        this.customer = customer;
    }
    

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
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

    public double getSummary() {
        return summary;
    }

    public void setSummary(double summary) {
        this.summary = summary;
    }

    public int getCartItems() {
        return cartItems;
    }

    public void setCartItems(int cartItems) {
        this.cartItems = cartItems;
    }

    
    public void onload() {

        //snowBean.saveTestUsersToDB();
    }
    
    public String addVaror(Product p, String str){
        snowBean.addProduct(p.getName(), str, 1, p.getPrice());
        String test1 = visaKorg(str);
        cartItems++;
        return "ok";
    }
    
    public String addVarorPremium(Product p, String str){
        snowBean.addProduct(p.getName(), str, 1, p.getPremiumPrice());
        String test2 = visaKorg(str);
        cartItems++;
        return "ok";
    }

    public String visaKorg(String mail) {
        products = snowBean.callProducts(mail);
        return "cart";
    }

    public String remove(String proname, long id, String mail) {
        snowBean.removeBypronameidemail(proname, id, mail);
        cartItems--;
        return visaKorg(mail);
    }

    public String buyAll(User2 user, List<Cart> korg) {
        String ordernr, mail, fullname, productname;
        int count;
        double totalprice;
        String fulladdress, postnraddress, telephone;
        
        LocalDateTime datetime = LocalDateTime.now();
        Random ran = new Random();
        int ran1 = ran.nextInt(9);
        int ran2 = ran.nextInt(9);
        int ran3 = ran.nextInt(9);
        int ran4 = ran.nextInt(9);
        
        ordernr = datetime.toString() + "::" + user.getEmail() + "::" + ran1+""+ran2+""+ran3+""+ran4;
        mail = user.getEmail();
        fullname = user.getFirstname() + " " + user.getFamilyname();
        fulladdress = user.getAddress();
        postnraddress = user.getPostnr() + " " + user.getPostaddress();
        telephone = user.getTelephone();
        for(Cart k: korg){
            productname = k.getProductname();
            count = k.getCount();
            totalprice = k.getTotalprice();
            
            snowBean.removeBypronameidemail(productname, k.getId(), mail);
            snowBean.sendOrder(ordernr, mail, fullname, productname, count, totalprice, fulladdress, postnraddress, telephone);
        }
        double test3 = callSumprice(mail);
        if(test3 >= 500000 && (currentUser.getStatus()).equals("customer")){
            snowBean.changeStatus(currentUser);
        }
        
        return visaKorg(mail);
    }
    
    public List<Orderning> callOrderbymail(String mail){
        orders = snowBean.callOrders(mail);
        return orders;
    }

    public double callSumprice(String mail){
        summary = snowBean.sumPrice(mail);
        return summary;
    }
    
     public String returnToIndex() {
        setCustomer(null);
        
        return "return_to_admin";
    
    }
     
         public String showCustomerOrders(User2 u){
             setCustomer(u);
             callOrderbymail(u.getEmail());
             
             return "show_customer_details";
    }  
         
         
         //Navcontroller functions here below
    private String previousPage;
    private String secondPreviousPage;


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
    
    public String logInLogOut(String currentPage){
            String page = "";
        
            if(!getIsLoggedIn()) {
                secondPreviousPage = previousPage;   
                previousPage = currentPage;
                page= "login"; 
            }
           
            else {     
                page= "logout";
            }
            return page;
    }
    
    public String logInUser(String currentPage){
            secondPreviousPage = previousPage;   
            previousPage = currentPage;
            return login();
    }
    
    public String logout(){
         secondPreviousPage = "";
               previousPage="";
        logOut();
        return "logout";
    }
    
      public String navigateToProduct(String currentPage, ProductController pCon, Product product) {
                secondPreviousPage = previousPage;
                previousPage = currentPage;
                return pCon.showSelectedProduct(product);
    }
    
    
    
}
