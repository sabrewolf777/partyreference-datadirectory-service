package ec.com.dinersclub.dddmodules.application.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PRDataDirectoryRequestValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPRDataDirectoryRequest {
    String message() default "Invalid token request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}