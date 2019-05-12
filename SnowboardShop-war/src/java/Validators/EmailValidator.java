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

@FacesValidator("emailValidator")
public class EmailValidator implements Validator, Serializable {
   
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (!continueValidation()) {
            return;
        }
        String emailStr = (String) value;
        
        if (emailStr.length() > 30 || !Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(emailStr).find()) {
            String message = "Du verkar inte ha matat in en korrekt mailadress. Försök igen!";
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
