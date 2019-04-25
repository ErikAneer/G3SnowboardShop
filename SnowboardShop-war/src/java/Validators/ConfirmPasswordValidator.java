
package Validators;

import java.util.Map;
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
@FacesValidator("confirmPasswordValidatator")
public class ConfirmPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (!continueValidation()) {
            return;
        }
        String confirmPassword = value.toString();

        Map map = context.getExternalContext().getRequestParameterMap();
        String password = (String) map.get(("registerForm:password"));

        if (!password.equals(confirmPassword)) {
            String message = "Lösenorden matchar inte! De måste vara exakt samma.";
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
