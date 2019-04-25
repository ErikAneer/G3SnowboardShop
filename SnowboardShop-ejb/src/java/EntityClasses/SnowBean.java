/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

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

        return "customer " + size;    }
}
