package de.ait.first_spring_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenOperationException extends RuntimeException{

    private final String field;

    private final String newValue;
    public ForbiddenOperationException(String field, String newValue) {
        super();
        this.field=field;
        this.newValue=newValue;
    }

    public String getField() {
        return field;
    }

    public String getNewValue() {
        return newValue;
    }
}
