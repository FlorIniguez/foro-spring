package com.spring.foro.infra.errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourcedNotFoundException extends IllegalArgumentException {
    private String resource;
    private String fieldName;
    private Long value;

    public ResourcedNotFoundException(String resource, String fieldName, Long value) {
        super(String.format("%s not found: %s, '%s'", resource, fieldName, value));
        this.resource = resource;
        this.fieldName = fieldName;
        this.value = value;
    }
}
