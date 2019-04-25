package pac;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erik
 */
@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable {

    private List<Product> boards = new ArrayList();
    private String message;
    private String nameSuggestions;

    /**
     * Creates a new instance of ProductController
     */
    public ProductController() {
    }

    public List<Product> getBoards() {
        return boards;
    }

    public void setBoards(List<Product> boards) {
        this.boards = boards;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNameSuggestions() {
        return nameSuggestions;
    }

    public void setNameSuggestions(String nameSuggestions) {
        this.nameSuggestions = nameSuggestions;
    }

    private void createBoards() {

        boards.add(new Product("Birds+Of+A+Feather+146+2019+Snowboard.jpg", "Custom Smalls", "Burton", "Blabla", 140, 3490));
        boards.add(new Product("Birds+Of+A+Feather+146+2019+Snowboard.jpg", "Custom FV 156", "Burton", "Blabla", 140, 3490));
        boards.add(new Product("Birds+Of+A+Feather+146+2019+Snowboard.jpg", "Custom 156", "Burton", "Blabla", 140, 3490));
        boards.add(new Product("Birds+Of+A+Feather+146+2019+Snowboard.jpg", "TWC Pro 156", "Burton", "Blabla", 140, 499990));
        boards.add(new Product("Birds+Of+A+Feather+146+2019+Snowboard.jpg", "Custom Smalls", "Burton", "Blabla", 140, 3490));
        boards.add(new Product("Birds+Of+A+Feather+146+2019+Snowboard.jpg", "Custom Smalls", "Burton", "Blabla", 140, 3490));
        boards.add(new Product("Birds+Of+A+Feather+146+2019+Snowboard.jpg", "Custom Smalls", "Burton", "Blabla", 140, 3490));
        boards.add(new Product("Birds+Of+A+Feather+146+2019+Snowboard.jpg", "Custom Smalls", "Burton", "Blabla", 140, 3490));
        boards.add(new Product("Birds+Of+A+Feather+146+2019+Snowboard.jpg", "Custom Smalls", "Burton", "Blabla", 140, 3490));

    }

    public void onload() {
        createBoards();
    }

    /**
     * Auto-completes product names from the Product List
     *
     * @param text
     * @return
     */
    private String inputName;

    public List<String> nameSuggestions(String enteredValue) {
        List<String> matches = new ArrayList<>();
        //using data factory for getting suggestions
        for (Product p : boards) {
            if (p.getName().toLowerCase().startsWith(enteredValue.toLowerCase())) {
                matches.add(p.getName());
            }
        }
        return matches;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        System.out.println(inputName);
        this.inputName = inputName;
    }
}
