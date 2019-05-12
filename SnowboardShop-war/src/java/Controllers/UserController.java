package Controllers;

import EJB.UserBean;
import EntityClasses.Cart;
import EntityClasses.Orderning3;
import EntityClasses.Product;
import EJB.SnowBeanLocal;
import EntityClasses.User3;
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
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Erik  jjjj kk
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private SnowBeanLocal snowBean;

    private List<User3> users;
    private List<User3> kunder;
    private User3 currentUser;
    private User3 customer;
    private Boolean isLoggedIn = false;
    private String loggedInStatus;

    private String firstname, familyname, telephone, address, postnr, postaddress, email, code, status;

    private String confirmPassword;

    private String sameEmailmsg;
    private List<Cart> products = new ArrayList();
    private List<Orderning3> orders = new ArrayList();
    private int cartItems;
    private double summary;

    private List<String> ordernrs = new ArrayList();
    private List<Orderning3> detailorders = new ArrayList();
    private String ordernummer;
    private double sumprice;

    /**
     * Creates a new instance of LoginBean //(String firstName, String
     * familyName, String email, String password, String status) julia
     */ 
    public UserController() {
        loggedInStatus = "Logga in";
        cartItems = 0;
    }

    public void login() { 
        System.out.println("   entered login method");
        String page = "";
        users = snowBean.callAllUser3();
        kunder = snowBean.callAllCustomer3("customer", "premium");
        summary = snowBean.sumPrice(email);
        List<Cart> carts = snowBean.callProducts(email);
        cartItems = carts.size();

        if (!snowBean.checkIfUserExists(email, code)) {
            setEmail(null);
            setCode(null);
            FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Sorry, ditt användarnamn och/eller lösenord stämmer inte! Försök igen.", null);
            FacesContext.getCurrentInstance().addMessage("loginForm:loginButton", javaTextMsg);
            System.out.println("Sorry, ditt användarnamn och/eller lösenord stämmer inte! Försök igen.");
        } else {
            User3 u = (User3) snowBean.login(email, code);
            currentUser = u;
            setIsLoggedIn(true);
            loggedInStatus = "Logga ut";
            page = u.getStatus();
            setEmail(null);
            if(products.size()>0){
                if(u.getStatus().equals("customer")){
                    for(Cart c: products){
                        snowBean.addProduct3(c.getProductname(), u.getEmail(), c.getCount(), c.getTotalprice(), c.getPrice());
                    }
                    products = snowBean.callProducts(u.getEmail());
                    cartItems = products.size();                    
                }else if(u.getStatus().equals("premium")){
                    for(Cart c: products){
                        snowBean.addProduct3(c.getProductname(), u.getEmail(), c.getCount(), c.getTotalprice()*0.9, c.getPrice()*0.9);
                    }
                    products = snowBean.callProducts(u.getEmail());
                    cartItems = products.size();                                        
                }else{
                    products = new ArrayList();
                    cartItems = 0;
                }
            }
        }  
        int ttt = callItems();
        //return page;
    }

    public void logOut() {
        snowBean.removeAllpro(currentUser.getEmail());
        for(Cart c: products){
            snowBean.addProduct3(c.getProductname(), c.getEmail(), c.getCount(), c.getTotalprice(), c.getPrice());
        }
        
        loggedInStatus = "Logga in";
        isLoggedIn = false;
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
        summary = 0;
        cartItems = 0;
        products = new ArrayList();
        ordernummer = "";
    }
     
    public void registerNewCustomer() {
        String sidan = "register";
        if (snowBean.isSameEmail(email)) {
                FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Den angivna mailadressen finns redan registrerad! Ange en annan mailadress.", null);
                FacesContext.getCurrentInstance().addMessage("registerForm:contactInsert", javaTextMsg);
            System.out.println("Samma email finns");
        }   
        else{
       snowBean.save(firstname, familyname, telephone, address, postnr, postaddress, email, code, "customer");
            login();
            clearRegisterForm();
        } 
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

    public List<Orderning3> getOrders() {
        return orders;
    }

    public void setOrders(List<Orderning3> orders) {
        this.orders = orders;
    }

    public List<User3> getUsers() {
        return users;
    }

    public void setUsers(List<User3> users) {
        this.users = users;
    }

    public String getSameEmailmsg() {
        return sameEmailmsg;
    }

    public void setSameEmailmsg(String sameEmailmsg) {
        this.sameEmailmsg = sameEmailmsg;
    }

    public List<User3> getKunder() {
        return kunder;
    }

    public void setKunder(List<User3> kunder) {
        this.kunder = kunder;
    }

    public User3 getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User3 currentUser) {
        this.currentUser = currentUser;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public User3 getCustomer() {
        return customer;
    }

    public void setCustomer(User3 customer) {
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

    public List<String> getOrdernrs() {
        return ordernrs;
    }

    public void setOrdernrs(List<String> ordernrs) {
        this.ordernrs = ordernrs;
    }

    public List<Orderning3> getDetailorders() {
        return detailorders;
    }

    public void setDetailorders(List<Orderning3> detailorders) {
        this.detailorders = detailorders;
    }

    public String getOrdernummer() {
        return ordernummer;
    }

    public void setOrdernummer(String ordernummer) {
        this.ordernummer = ordernummer;
    }

    public double getSumprice() {
        return sumprice;
    }

    public void setSumprice(double sumprice) {
        this.sumprice = sumprice;
    }

    public void onload() {
       //snowBean.saveTestUsersToDB();
    }
    
    private long indexid = 1L;
    
    public String addVaror(User3 u, Product p, String str) {
                int size=0;
        if (u == null) {
            if(products.size()<1){
                Cart c = new Cart(p.getName(), "null", 1, p.getPrice());
                c.setPrice(p.getPrice());  
                c.setId(indexid);
                indexid++;
                products.add(c);
            }
            else{
                for(int i=0; i<products.size(); i++){
                    if((products.get(i).getProductname()).equals(p.getName())){
                        int cou = products.get(i).getCount();
                        cou++;
                        products.get(i).setCount(cou);
                        products.get(i).setTotalprice(products.get(i).getPrice()*cou);
                        break;
                    }else{
                        size++;
                        if(size==products.size()){
                            Cart c1 = new Cart(p.getName(), "null", 1, p.getPrice());
                            c1.setPrice(p.getPrice());  
                            c1.setId(indexid);
                            products.add(c1);
                            indexid++;
                            size = 0;
                            break;
                        }
                    }    
                }   
            }
        } else {
            
            if ((u.getStatus()).equals("customer")) {
                snowBean.addProduct3(p.getName(), str, 1, p.getPrice(), p.getPrice());
                String test1 = visaKorg(str);
                cartItems++;
            }
            if ((u.getStatus()).equals("premium")) {
                snowBean.addProduct3(p.getName(), str, 1, p.getPremiumPrice(), p.getPremiumPrice());
                String test2 = visaKorg(str);

            }
        }
        return "ok";
    }

    public String addVarorPremium(Product p, String str) {
        snowBean.addProduct(p.getName(), str, 1, p.getPremiumPrice());
        String test2 = visaKorg(str);
        return "ok";
    }

    public String visaKorg(String mail) {
        products = snowBean.callProducts(mail);
        return "cart";
    }

    public String remove(String proname, long id, User3 u) {
        String mail = "";
        if (u == null) {
            for (Cart c : products) {
                if (c.getProductname().equals(proname) && c.getId() == id) {
                    products.remove(c);
                    return "cart";
                }
            }
        } else {
            mail = u.getEmail();
            snowBean.removeBypronameidemail(proname, id, mail);
            cartItems--;
        }
        return visaKorg(mail);
    }

    public String removeall(User3 u) {
        String mail = "";
        if (u == null) {
            products = new ArrayList();
            cartItems = 0;
            return "cart";
        } else {
            mail = u.getEmail();
            snowBean.removeAllpro(u.getEmail());
            cartItems = 0;
        }
        return visaKorg(mail);
    }
    
    public String totalPrice(boolean b, long id, Cart p){
        for(Cart c: products){
            if(c.getProductname().equals(p.getProductname()) && c.getId() == id){
                int procount = p.getCount();
                if(b==true){ //+
                    procount++;
                }
                if(b==false){
                    procount--;
                }
                c.setCount(procount);
                c.setTotalprice(c.getPrice()*procount);
                break;
            }
        }
        return "cart";
    }
    
    public String saveChange(User3 u){
        if(u == null){
            return "loginPage";
        }else{
            snowBean.removeAllpro(u.getEmail());
            for(Cart c: products){
                snowBean.addProduct3(c.getProductname(), c.getEmail(), c.getCount(), c.getTotalprice(), c.getPrice());
            }
        }
        return visaKorg(u.getEmail());
    }
    
    public double callSummaryPrice(User3 user) {
        sumprice = 0;
        for(Cart c: products){
            sumprice = sumprice + c.getTotalprice();
        }
        /*if(user == null){
            sumprice = 0;
            for(Cart c: products){
                sumprice = sumprice + c.getTotalprice();
            }
        }else{
            String mail = user.getEmail();
            sumprice = snowBean.callSummaryPrice(mail);
        }*/
        return sumprice;
    }

    public int callItems(){
        int items = 0;
        for(Cart c: products){
            items += c.getCount();
        }
        return items;
    }
    
    public String buyAll(User3 user, List<Cart> korg) {
        if(user == null){
            return "loginPage";
        }
        cartItems = 0;
        String ordernr, mail, fullname, productname;
        int count;
        double totalprice, summaprice = 0;
        String fulladdress, postnraddress, telephone;

        LocalDateTime datetime = LocalDateTime.now();
        Random ran = new Random();
        int ran1 = ran.nextInt(9);
        int ran2 = ran.nextInt(9);
        int ran3 = ran.nextInt(9);
        int ran4 = ran.nextInt(9);

        ordernr = datetime.toString() + "-" + ran1 + "" + ran2 + "" + ran3 + "" + ran4 + "::" + user.getEmail() + "::";
        ordernummer = ordernr.substring(2, 4) + ordernr.substring(5, 7) + ordernr.substring(8, 10) + ordernr.substring(11, 13)
                + ordernr.substring(14, 16) + ordernr.substring(17, 19) + ordernr.substring(20, 23) + ordernr.substring(24, 28);

        mail = user.getEmail();
        fullname = user.getFirstname() + " " + user.getFamilyname();
        fulladdress = user.getAddress();
        postnraddress = user.getPostnr() + " " + user.getPostaddress();
        telephone = user.getTelephone();
        for (Cart k : korg) {
            productname = k.getProductname();
            count = k.getCount();
            totalprice = k.getTotalprice();
            summaprice += totalprice;
            snowBean.removeBypronameidemail(productname, k.getId(), mail);
            snowBean.sendOrder3(user, ordernr, mail, fullname, productname, count, totalprice, fulladdress, postnraddress, telephone);
            //snowBean.sendOrder(ordernr, mail, fullname, productname, count, totalprice, fulladdress, postnraddress, telephone);
        }
        String newordernr = ordernr + summaprice;
        snowBean.changeNewOrdernr(ordernr, newordernr);
        double test3 = callSumprice(mail);
        if (test3 >= 500000 && (currentUser.getStatus()).equals("customer")) {
            snowBean.changeStatus(currentUser);
        }
        showOrderDetails(ordernummer, mail);  
        return visaKorg(mail);
    }

    public List<Orderning3> callOrderbymail(String mail) {
        orders = snowBean.callOrder3(mail);
        //orders = snowBean.callOrders(mail);
        return orders;
    }

    public double callSumprice(String mail) {
        summary = snowBean.sumPrice(mail);
        return summary;
    }

    public String returnToIndex() {
        setCustomer(null);
        setDetailorders(null);
        return "return_to_admin";

    }

    public String showCustomerOrders(User3 u) {
        setCustomer(u);
        callOrderbymail(u.getEmail());

        return "show_customer_details";
    }

    public String showOrderNrs(User3 u) {
        setCustomer(u);
        ordernrs = snowBean.callUser3OrderNrs(u);
        return "show_customer_details";
    }

    public void showOrderDetails(String ordernr, String mail) {
        String teststr = "20" + ordernr.substring(0, 2) + "-" + ordernr.substring(2, 4)
                + "-" + ordernr.substring(4, 6) + "T" + ordernr.substring(6, 8) + ":" + ordernr.substring(8, 10)
                + ":" + ordernr.substring(10, 12) + "." + ordernr.substring(12, 15) + "-" + ordernr.substring(15) + "::" + mail;
        detailorders = snowBean.callOrderDetail3(teststr);
    }

    public String showOrderSumprice(String ordernr, String mail) {
        String teststr = "20" + ordernr.substring(0, 2) + "-" + ordernr.substring(2, 4)
                + "-" + ordernr.substring(4, 6) + "T" + ordernr.substring(6, 8) + ":" + ordernr.substring(8, 10)
                + ":" + ordernr.substring(10, 12) + "." + ordernr.substring(12, 15) + "-" + ordernr.substring(15) + "::" + mail;
        String sumprice = snowBean.showOrder3Sumprice(teststr);
        return sumprice;
    }
    
    public int callCount(String mail) {
        int count = snowBean.callAntalcount(mail);
        return count;
    }
}
