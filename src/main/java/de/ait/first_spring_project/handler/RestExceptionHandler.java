package de.ait.first_spring_project.handler;

import de.ait.first_spring_project.dto.ErrorDto;
import de.ait.first_spring_project.exceptions.ForbiddenOperationException;
import de.ait.first_spring_project.exceptions.IncorrectUserIdException;
import de.ait.first_spring_project.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(IncorrectUserIdException.class)
    public ResponseEntity<ErrorDto> handlerIncorrectUserIdException(IncorrectUserIdException e){
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ErrorDto.builder()
                        .message("Id of user <" + e.getId() + "> is incorrect")
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(NotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .message(e.getEntity() + " with id <" + e.getId() + "> not found")
                        .build());
    }

    @ExceptionHandler(ForbiddenOperationException.class)
        public ResponseEntity<ErrorDto> handleException(ForbiddenOperationException e){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ErrorDto.builder()
                        .message("Cannot set <" + e.getField() + "> as <" + e.getNewValue() + ">")
                        .build());
    }
}
