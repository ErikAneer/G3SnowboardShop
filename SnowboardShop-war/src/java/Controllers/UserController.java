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

/**
 *
 * @author Erik
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
     * familyName, String email, String password, String status)
     */
    public UserController() {
        loggedInStatus = "Logga in";
        cartItems = 0;
    }

    public String login() {
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
        } else {
            User3 u = (User3) snowBean.login(email, code);
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
        if (sameEmailmsg.equals("ok")) {
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

    public String addVaror(User3 u, Product p, String str) {
        if ((u.getStatus()).equals("customer")) {
            snowBean.addProduct(p.getName(), str, 1, p.getPrice());
            String test1 = visaKorg(str);
            cartItems++;
        }
        if ((u.getStatus()).equals("premium")) {
            snowBean.addProduct(p.getName(), str, 1, p.getPremiumPrice());
            String test2 = visaKorg(str);
            cartItems++;
        }
        return "ok";
    }

    public String addVarorPremium(Product p, String str) {
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

    public double callSummaryPrice(String mail) {
        sumprice = snowBean.callSummaryPrice(mail);
        return sumprice;
    }

    public String buyAll(User3 user, List<Cart> korg) {
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

    public int callCount(String mail) {
        int count = snowBean.callAntalcount(mail);
        return count;
    }
}