/*

 */
package EJB;

import EntityClasses.User3;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Erik
 */
@Stateless
@LocalBean
public class UserBean {

    @PersistenceContext(unitName = "SnowboardShop-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public boolean checkIfUniqueEmail(String email) {
        boolean isUnique = true;
        Query q = em.createQuery("select o from User2 o where o.email = :email");
        q.setParameter("email", email);
        List<User3> results = q.getResultList();
        
        if(results.size()> 0) {
            isUnique = false;
        }
        return isUnique;
    }

    
   
}
