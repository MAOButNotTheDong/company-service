package com.maoha.companyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Integer id) {
        super("Company with id " + id + " not found");
    }

    public CompanyNotFoundException(List<Integer> ids) {
        super("Companies with ids " + ids + " not found");
    }
}
