package ec.com.dinersclub.dddmodules.application.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TokenHeadersValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTokenHeaders {
    String message() default "Headers inválidos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 