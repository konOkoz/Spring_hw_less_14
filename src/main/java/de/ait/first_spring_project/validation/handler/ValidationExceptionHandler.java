package de.ait.first_spring_project.validation.handler;

import de.ait.first_spring_project.validation.dto.ValidationErrorDto;
import de.ait.first_spring_project.validation.dto.ValidationErrorsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDto> handleException(MethodArgumentNotValidException e) {

        List<ValidationErrorDto> validationErrors = e.getBindingResult().getAllErrors().stream()
                .filter(error -> error instanceof FieldError)
                .map(error -> (FieldError) error)
                .map(error -> {
                    ValidationErrorDto errorDto = ValidationErrorDto.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build();

                if (error.getRejectedValue() != null){
                    errorDto.setRejectedValue(error.getRejectedValue().toString());
                }

                return errorDto;
                })
                .collect(Collectors.toList());

        return ResponseEntity
                .badRequest()
                .body(ValidationErrorsDto.builder()
                        .errors(validationErrors)
                        .build());
    }
}
