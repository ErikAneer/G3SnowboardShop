/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.util.List;
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
        Query q = em.createQuery("select o from User2 o");

        int size = q.getResultList().size();

        return "customer " + size;
    }

    @Override
    public Object login(String email, String code) {
        Query q = em.createQuery("select o from User2 o where o.email=:email and o.code=:code");
        q.setParameter("code", code);
        q.setParameter("email", email);
        User2 u = (User2) q.getSingleResult();
        return u;
    }

    @Override
    public void save(String firstname, String familyname, String telephone,
            String address, String postnr, String postaddress,
            String email, String code, String status) {
        User2 newUser = new User2(firstname, familyname, telephone,
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
    public List<User2> callAllKunders(String customer, String premium) {
        Query q = em.createQuery("select o from User2 o where o.status=:customer or o.status=:premium");
        q.setParameter("customer", customer);
        q.setParameter("premium", premium);
        List<User2> users = q.getResultList();
        return users;
    }

    @Override
    public boolean checkIfUniqueEmail(String email) {
        Query q = em.createQuery("select o from User2 o where o.email =:email");
        q.setParameter("email", email);

        return q.getResultList().size() <= 0;
    }

    @Override
    public void saveTestUsersToDB() {
        Query q = em.createQuery("select o from User2 o");
        List<User2> users = q.getResultList();
           User2 u = new User2();

            u.setFirstname("aaaa");
            u.setFamilyname("bbbb");
            u.setEmail("aaaa@test.nu");
            u.setCode("aaaa11");
            u.setStatus("customer");
            persist(u);
        
            User2 u1 = new User2();

            u1.setFirstname("bbbb");
            u1.setFamilyname("cccc");
            u1.setEmail("bbbb@test.nu");
            u1.setCode("bbbb11");
            u1.setStatus("premium");
            persist(u1);
            User2 u2 = new User2();

            u2.setFirstname("cccc");
            u2.setFamilyname("dddd");
            u2.setEmail("cccc@test.nu");
            u2.setCode("cccc11");
            u2.setStatus("admin");
            persist(u2);
            
        
           
    }

    @Override
    public boolean checkIfUserExists(String email, String code) {
        Query q = em.createQuery("select o from User2 o where o.email=:email and o.code=:code");
        q.setParameter("code", code);
        q.setParameter("email", email);
        List<User2> u = q.getResultList();
        return u.size() > 0;
    }

}
