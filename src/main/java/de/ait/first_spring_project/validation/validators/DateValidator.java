package de.ait.first_spring_project.validation.validators;




import de.ait.first_spring_project.validation.constraints.BeforeCurrentDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<BeforeCurrentDate, String> {
    private LocalDate creationDate;

    @Override
    public void initialize(BeforeCurrentDate constraintAnnotation) {
        this.creationDate = LocalDate.now();
    }


    @Override
    public boolean isValid(String inputDate, ConstraintValidatorContext constraintValidatorContext) {

        LocalDate dateToCheck = LocalDate.parse(inputDate);

        return !dateToCheck.isBefore(creationDate);
    }
}
