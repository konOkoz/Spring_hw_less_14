package de.ait.first_spring_project.validation.constraints;


import de.ait.first_spring_project.validation.validators.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface CorrectDateValidator {

        String message() default "must be correct";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}
