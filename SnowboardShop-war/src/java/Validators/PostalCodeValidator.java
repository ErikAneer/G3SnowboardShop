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
@FacesValidator("postalcodeValidator")
public class PostalCodeValidator implements Validator, Serializable {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (!continueValidation()) {
            return;
        }
        String emailStr = (String) value;

        if (emailStr.length() > 30 || !Pattern.compile("^[1-9]{1}[0-9]{2}(?:[ ]{1})?[0-9]{2}$", Pattern.CASE_INSENSITIVE).matcher(emailStr).find()) {
            String message = "Anggivet postummer verkar inte stämma. Postnummer ska skrivas in med 5 siffror.";
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
