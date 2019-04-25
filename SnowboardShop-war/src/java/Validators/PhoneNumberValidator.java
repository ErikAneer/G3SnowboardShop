/*

 */
package Validators;

import java.io.Serializable;
import java.util.regex.Matcher;
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
@FacesValidator("phoneNumberValidator")
public class PhoneNumberValidator implements Validator, Serializable {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (!continueValidation()) {
            return;
        }
        String emailStr = (String) value;

        if (emailStr.length() > 30 || !Pattern.compile("^[0]{1}[0-9]{7,9}$", Pattern.CASE_INSENSITIVE).matcher(emailStr).find()) {
            String message = "Anggivet telefonnummer verkar inte stämma. Det ska bara innehålla siffror och börja med 0.";
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
