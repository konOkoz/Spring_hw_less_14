package de.ait.first_spring_project.validation.validators;

import de.ait.first_spring_project.dto.tasksdto.NewTaskDto;
import de.ait.first_spring_project.validation.constraints.CorrectDateValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<CorrectDateValidator, NewTaskDto> {


    @Override
    public boolean isValid(NewTaskDto newTaskDto, ConstraintValidatorContext constraintValidatorContext) {
        try {
            LocalDate startDate = LocalDate.parse(newTaskDto.getStartDate());
            LocalDate finishDate = LocalDate.parse(newTaskDto.getFinishDate());

            if (!startDate.isBefore(LocalDate.now())) {
                return !finishDate.isBefore(startDate);
            }else return false;

        } catch (RuntimeException e) {
            return false;
        }

    }
}
