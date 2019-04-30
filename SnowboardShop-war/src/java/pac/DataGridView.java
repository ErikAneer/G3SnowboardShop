
package pac;
import EntityClasses.Product;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Erik
 */

@ManagedBean
@ViewScoped
public class DataGridView implements Serializable {
     
    private List<Product> boards;
     
    private Product  selectedBoard;

    public List<Product> getBoards() {
        return boards;
    }
    

    public void setBoards(List<Product> boards) {
        this.boards = boards;
    }

    public Product getSelectedBoard() {
        return selectedBoard;
    }

    public void setSelectedBoard(Product selectedBoard) {
        this.selectedBoard = selectedBoard;
    }
     
    
}