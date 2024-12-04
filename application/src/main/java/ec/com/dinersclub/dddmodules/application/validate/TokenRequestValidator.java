package ec.com.dinersclub.dddmodules.application.validate;


import ec.com.dinersclub.dddmodules.domain.model.profile.RecEvaluateTokenRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TokenRequestValidator implements ConstraintValidator<ValidTokenRequest, RecEvaluateTokenRequest> {

    @Override
    public boolean isValid(RecEvaluateTokenRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return false;
        }
       
        if((request.getPartyAuthenticationAssessment() == null || request.getPartyAuthenticationAssessment().getPartyReference() == null|| 
        	  request.getPartyAuthenticationAssessment().getPartyReference().getPartyType() == null ) ||  
        	  request.getPartyAuthenticationAssessment().getPartyReference().getPartyType().trim().isEmpty()) {
              addConstraintViolation(context, "El PartyReference es requerido");
              return false;
        }
        
        if(request.getUsername() == null ||  request.getUsername().trim().isEmpty()) {
              addConstraintViolation(context, "El Username es requerido");
              return false;
        }
    
        if(request.getTokenAssignment() == null ||  request.getTokenAssignment().getTokenStoredValue() == null ||
        	request.getTokenAssignment().getTokenStoredValue().trim().isEmpty()) {
            addConstraintViolation(context, "El TokenStoredValue es requerido");
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