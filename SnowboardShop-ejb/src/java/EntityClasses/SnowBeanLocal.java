/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Julia
 */
@Local
public interface SnowBeanLocal {

    String log();

    Object login(String email, String code);

    void save(String firstname, String familyname, String telephone, String address, String postnr, String postaddress, String email, String code, String status);

    List<User2> callAllUsers();

    List<User2> callAllKunders(String customer, String premium);

    boolean checkIfUniqueEmail(String email);

    void saveTestUsersToDB();

    boolean checkIfUserExists(String email, String code);

    Boolean isSameEmail(String email);

    void addProduct(String productname, String email, int count, double totalprice);

    List<Cart> callProducts(String email);

    void removeBypronameidemail(String proname, long id, String email);

    void skickaOrder(String ordernr, String email, String fullname, String productname, int count, double totalprice, String fulladdress, String postnraddress, String telephone);

    List<Orderning> callOrders(String email);

    Double sumPrice(String email);

    void changeStatus(Object user);
    
}
