package pac;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Erik
 */
@Named(value = "megaMenuView")
@SessionScoped
public class MegaMenuView implements Serializable {

   private String orientation = "horizontal";
 
    public String getOrientation() {
        return orientation;
    }
 
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    
}
