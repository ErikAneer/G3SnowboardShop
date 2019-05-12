package EJB;

import EntityClasses.Cart;
import EntityClasses.Orderning;
import EntityClasses.Orderning3;
import EntityClasses.Product;
import EntityClasses.User2;
import EntityClasses.User3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Julia gu
 */
@Stateless
public class SnowBean implements SnowBeanLocal {

    @PersistenceContext(unitName = "SnowboardShop-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public String log() {
        Query q = em.createQuery("select o from User3 o");

        int size = q.getResultList().size();

        return "customer " + size;
    }

    @Override
    public Object login(String email, String code) {
        Query q = em.createQuery("select o from User3 o where o.email=:email and o.code=:code");
        q.setParameter("code", code);
        q.setParameter("email", email);
        User3 u = (User3) q.getSingleResult();
        return u;
    }

    @Override
    public void save(String firstname, String familyname, String telephone,
            String address, String postnr, String postaddress,
            String email, String code, String status) {
        User3 newUser = new User3(firstname, familyname, telephone,
                address, postnr, postaddress, email, code, status);
        persist(newUser);
    }

    @Override
    public List<User2> callAllUsers() {
        Query q = em.createQuery("select o from User2 o");
        List<User2> users = q.getResultList();
        return users;
    }

    @Override
    public List<User3> callAllUser3() {
        Query q = em.createQuery("select o from User3 o");
        List<User3> users = q.getResultList();
        return users;
    }

    @Override
    public List<User2> callAllCustomers(String customer, String premium) {
        Query q = em.createQuery("select o from User2 o where o.status=:customer or o.status=:premium");
        q.setParameter("customer", customer);
        q.setParameter("premium", premium);
        List<User2> users = q.getResultList();
        return users;
    }

    @Override
    public List<User3> callAllCustomer3(String customer, String premium) {
        Query q = em.createQuery("select o from User3 o where o.status=:customer or o.status=:premium");
        q.setParameter("customer", customer);
        q.setParameter("premium", premium);
        List<User3> users = q.getResultList();
        return users;
    }

    @Override
    public boolean checkIfUniqueEmail(String email) {
        Query q = em.createQuery("select o from User3 o where o.email =:email");
        q.setParameter("email", email);

        return q.getResultList().size() <= 0;
    }

    @Override
    public void saveTestUsersToDB() {
        Query q = em.createQuery("select o from User2 o");
        List<User2> users = q.getResultList();
        User3 u = new User3();

        u.setFirstname("aaaa");
        u.setFamilyname("bbbb");
        u.setEmail("aaaa@test.nu");
        u.setCode("aaaa11");
        u.setStatus("customer");
        persist(u);

        User3 u1 = new User3();

        u1.setFirstname("bbbb");
        u1.setFamilyname("cccc");
        u1.setEmail("bbbb@test.nu");
        u1.setCode("bbbb11");
        u1.setStatus("premium");
        persist(u1);
        User3 u2 = new User3();

        u2.setFirstname("cccc");
        u2.setFamilyname("dddd");
        u2.setEmail("cccc@test.nu");
        u2.setCode("cccc11");
        u2.setStatus("admin");
        persist(u2);

    }

    @Override
    public boolean checkIfUserExists(String email, String code) {
        Query q = em.createQuery("select o from User3 o where o.email=:email and o.code=:code");
        q.setParameter("code", code);
        q.setParameter("email", email);
        List<User3> u = q.getResultList();
        return u.size() > 0;
    }

    @Override
    public Boolean isSameEmail(String email) {
        boolean same = false;
        Query q = em.createQuery("select o from User3 o");
        List<User3> users = q.getResultList();
        for (User3 u : users) {
            if (email.equalsIgnoreCase(u.getEmail())) {
                same = true;
                break;
            }
        }

        return same;
    }

    @Override
    public void addProduct(String productname, String email, int count, double totalprice) {
        Cart k = new Cart(productname, email, count, totalprice);
        em.persist(k);
    }

    @Override
    public void addProduct3(String productname, String email, int count, double totalprice, double price) {
        int test = 0;
        Query q = em.createQuery("select o from Cart o where o.email=:email");
        q.setParameter("email", email);
        List<Cart> products = q.getResultList();
        if(products.size()<1){
            Cart k = new Cart(productname, email, count, totalprice, price);
            em.persist(k);
        }else{
            for(Cart p: products){
                if(p.getProductname().equals(productname)){
                    int t1 = p.getCount()+count;
                    double d1 = p.getTotalprice()+totalprice;
                    p.setCount(t1);
                    p.setTotalprice(d1);
                    em.merge(p);
                }else{
                    test++;
                    if(test==products.size()){
                        Cart k = new Cart(productname, email, count, totalprice, price);
                        em.persist(k);
                    }
                }
            }
        }
    }
    
    @Override
    public List<Cart> callProducts(String email) {
        List<Cart> pros = new ArrayList();
        Query q = em.createQuery("select o from Cart o");
        List<Cart> products = q.getResultList();
        for (Cart k : products) {
            if (email.equalsIgnoreCase(k.getEmail())) {
                pros.add(k);
            }
        }

        return pros;
    }

    @Override
    public void removeBypronameidemail(String proname, long id, String email) {
        Query q = em.createQuery("select o from Cart o");
        List<Cart> products = q.getResultList();
        for (Cart k : products) {
            if (proname.equalsIgnoreCase(k.getProductname()) && id == k.getId() && email.equalsIgnoreCase(k.getEmail())) {
                em.remove(k);
            }
        }
    }

    @Override
    public void removeAllpro(String email) {
        Query q = em.createQuery("select o from Cart o");
        List<Cart> products = q.getResultList();
        for (Cart k : products) {
            if (k.getEmail().equals(email)) {
                em.remove(k);
            }
        }
    }

    @Override
    public void sendOrder(String ordernr, String email, String fullname, String productname,
            int count, double totalprice, String fulladdress, String postnraddress, String telephone) {
        Orderning order = new Orderning(ordernr, email, fullname, productname, count, totalprice, fulladdress, postnraddress, telephone);
        em.persist(order);
    }

    @Override
    public void sendOrder3(Object user, String ordernr, String email, String fullname, String productname,
            int count, double totalprice, String fulladdress, String postnraddress, String telephone) {
        User3 u3 = (User3) user;
        Orderning3 order = new Orderning3(ordernr, email, fullname, productname, count, totalprice, fulladdress, postnraddress, telephone, u3);
        List<Orderning3> orders = u3.getOrders();
        orders.add(order);
        u3.setOrders(orders);
        em.persist(order);
    }

    @Override
    public List<Orderning> callOrders(String email) {
        Query q = em.createQuery("select o from Orderning o where o.email=:email order by o.ordernr");
        q.setParameter("email", email);
        List<Orderning> orders = q.getResultList();

        return orders;
    }

    @Override
    public List<Orderning3> callOrder3(String email) {
        Query q = em.createQuery("select o from Orderning3 o where o.email=:email order by o.ordernr");
        q.setParameter("email", email);
        List<Orderning3> orders = q.getResultList();

        return orders;
    }

    @Override
    public Double sumPrice(String email) {
        double total = 0;
        Query q1 = em.createQuery("select o from Orderning3 o where o.email=:email");
        q1.setParameter("email", email);
        List<Orderning3> orders = q1.getResultList();
        if(!orders.isEmpty()){
            Query q = em.createQuery("select sum(o.totalprice) from Orderning3 o where o.email=:email");
            q.setParameter("email", email);
            total = (double)q.getSingleResult();  
        }
        return total;
    }

    @Override
    public void changeStatus(Object user) {
        User3 u = (User3) user;
        u.setStatus("premium");
        em.merge(u);
    }

    @Override
    public List<String> callUser3OrderNrs(Object user) {
        User3 u = (User3)user;
        Query q = em.createQuery("select o from Orderning3 o where o.user3=:user order by o.ordernr");
        q.setParameter("user", u);
        List<Orderning3> orders = q.getResultList();
        Set<String> test = new HashSet();
        for(Orderning3 od: orders){
            String str1 = od.getOrdernr();      
            String str2 = str1.substring(2, 4)+str1.substring(5, 7)+str1.substring(8, 10)+
                    str1.substring(11, 13)+str1.substring(14, 16)+str1.substring(17, 19) + str1.substring(20, 23)+
                    str1.substring(24, 28);

            test.add(str2);
        }
        List<String> orderNrs = new ArrayList();
        for(String ss: test){
            orderNrs.add(ss);
        }
        Collections.sort(orderNrs);
        return orderNrs;
    }

    @Override
    public List<Orderning3> callOrderDetail3(String ordermail) {
        Query q = em.createQuery("select o from Orderning3 o");
        List<Orderning3> orders = q.getResultList();
        List<Orderning3> details = new ArrayList();
        for(Orderning3 odr: orders){
            if((odr.getOrdernr()).startsWith(ordermail)){
                details.add(odr);
            }
        }       
        return details;
    }

    @Override
    public String showOrder3Sumprice(String ordermail) {
        Query q = em.createQuery("select o from Orderning3 o");
        List<Orderning3> orders = q.getResultList();
        String sumprice = "0";
        int index = ordermail.length()+2;
        for(Orderning3 odr: orders){
            if((odr.getOrdernr()).startsWith(ordermail)){
                sumprice = odr.getOrdernr().substring(index);
                break;
            }
        }     
        return sumprice;
    }
    
    @Override
    public void changeNewOrdernr(String oldnr, String newnr) {
        Query q = em.createQuery("select o from Orderning3 o where o.ordernr=:ordernr");
        q.setParameter("ordernr", oldnr);
        List<Orderning3> orders = q.getResultList();
        for(Orderning3 oo: orders){
            oo.setOrdernr(newnr);
            em.merge(oo);
        }   
    }

    @Override
    public Integer callAntalcount(String email) {
        int total = 0;
        Query q1 = em.createQuery("select o from Orderning3 o where o.email=:email");
        q1.setParameter("email", email);
        List<Orderning3> orders = q1.getResultList();
        if(!orders.isEmpty()){
            Query q = em.createQuery("select sum(o.count) from Orderning3 o where o.email=:email");
            q.setParameter("email", email);
            total = Integer.parseInt((q.getSingleResult()).toString());           
        }       
        return total;
    }

    @Override
    public Double callSummaryPrice(String email) {
        double total = 0;
        Query q1 = em.createQuery("select o from Cart o where o.email=:email");
        q1.setParameter("email", email);
        List<Cart> orders = q1.getResultList();
        if(!orders.isEmpty()){
            Query q = em.createQuery("select sum(o.totalprice) from Cart o where o.email=:email");
            q.setParameter("email", email);
            total = (double)q.getSingleResult();           
        }
        return total;  
    }

    @Override
    public String callProductBrand(String productname) {
        Query q = em.createQuery("select p from Product p");
        List<Product> products = q.getResultList();
        for(Product p: products){
            if(p.getName().equals(productname)){
                return p.getBrand();
            }
        }
        return "";
    }







    
}
