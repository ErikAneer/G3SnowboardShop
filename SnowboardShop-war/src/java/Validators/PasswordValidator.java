/*

 */
package Validators;

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
@FacesValidator("passwordValidatator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (!continueValidation()) {
            return;
        }
        String pass = (String) value;
        char[] chars = pass.toCharArray();
        boolean text = false;
        boolean numbers = false;
        for (char c : chars) {
            if (Character.isDigit(c)) {
                numbers = true;
            }
            if (Character.isLetter(c)) {
                text = true;
            }
        }

        if (pass.length() < 6 || pass.length() > 12 || !text || !numbers) {
            String message = "Lösenordet måste vara 6-12 tecken och innehålla både bokstäver och siffror.";
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
