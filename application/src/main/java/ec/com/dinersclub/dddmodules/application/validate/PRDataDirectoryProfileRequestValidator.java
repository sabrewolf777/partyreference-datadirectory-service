package ec.com.dinersclub.dddmodules.application.validate;

import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateCorporateProfileRq;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PRDataDirectoryProfileRequestValidator implements ConstraintValidator<ValidPRDataDirectoryProfileRequest, RecEvaluateCorporateProfileRq> {

    @Override
    public boolean isValid(RecEvaluateCorporateProfileRq request, ConstraintValidatorContext context) {
        if (request == null) {
            return false;
        }
       
        if(request.getPartyReference() == null || request.getPartyReference().getIdentifications() == null) {
              addConstraintViolation(context, "El parametro Identifications es requerido");
              return false;
        }
        
        return true;
    }
    
    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addConstraintViolation();
    }
}