package de.ait.first_spring_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private final String entity;
    private final Long id;
    public NotFoundException(String entity, Long id){
        super();
        this.entity=entity;
        this.id=id;
    }

    public String getEntity() {
        return entity;
    }

    public Long getId() {
        return id;
    }
}
