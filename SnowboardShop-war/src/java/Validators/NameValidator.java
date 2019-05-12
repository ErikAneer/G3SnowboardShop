/*

 */
package Validators;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Erik
 */
@FacesValidator("nameValidator")
public class NameValidator implements Validator, Serializable {

      @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (!continueValidation()) {
            return;
        }
        String nameStr = (String) value; 
        
           if( ! Pattern.compile("[A-Öa-ö]+([ '-][a-öA-Ö]+)*{1,30}$", Pattern.CASE_INSENSITIVE).matcher(nameStr).find()){ //nameStr.length() < 2 || nameStr.length() > 30 || 
                String message = "Fältet får bara innehålla bokstäver och ska vara 2 - 30 tecken.";
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
           }

           
    }

    protected boolean continueValidation() {
        String skipValidator = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("skipValidator");
        if (skipValidator != null && skipValidator.equalsIgnoreCase("true")) {
            return false;
        }
        return true;
    }
}