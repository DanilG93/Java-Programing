package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Constraint(validatedBy = UniqueNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueName {

    String message() default "Name already exists in the database";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
