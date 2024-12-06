package ec.com.dinersclub.dddmodules.application.validate;

import ec.com.dinersclub.dddmodules.domain.model.user.RecRegisterPartyReferenceDataDirectoryEntryRq;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PRDataDirectoryUserRequestValidator implements ConstraintValidator<ValidPRDataDirectoryUserRequest, RecRegisterPartyReferenceDataDirectoryEntryRq> {

    @Override
    public boolean isValid(RecRegisterPartyReferenceDataDirectoryEntryRq request, ConstraintValidatorContext context) {
        if (request == null) {
            return false;
        }
        
        if(request.getPartyReferenceDataDirectoryEntry()== null || request.getPartyReferenceDataDirectoryEntry().getPartyReference() == null || 
          	  request.getPartyReferenceDataDirectoryEntry().getPartyReference().getPartyType() == null || request.getPartyReferenceDataDirectoryEntry().getPartyReference().getPartyType().trim().equals("")) {
                addConstraintViolation(context, "El parametro PartyType es requerido");
                return false;
          }
       
        if(request.getUsername() == null || request.getUsername().trim().equals("")) {
              addConstraintViolation(context, "El parametro Username es requerido");
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