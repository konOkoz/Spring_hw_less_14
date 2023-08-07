package de.ait.first_spring_project.validation.constraints;


import de.ait.first_spring_project.validation.validators.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface BeforeCurrentDate {

    String message() default "must be correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
