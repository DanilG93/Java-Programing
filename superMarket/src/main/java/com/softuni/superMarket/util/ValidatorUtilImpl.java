package com.softuni.superMarket.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class ValidatorUtilImpl implements ValidatorUtil {
    private final Validator validator;

    public ValidatorUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean isValid(T entity) {
        return validator.validate(entity).isEmpty();
    }


    @Override
    public <T> Set<ConstraintViolation<T>> getViolations(T entity) {
        return validator.validate(entity);
    }
}
