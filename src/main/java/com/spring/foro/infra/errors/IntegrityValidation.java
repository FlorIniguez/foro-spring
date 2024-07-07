package com.spring.foro.infra.errors;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String s){
        super(s);
    }
}
