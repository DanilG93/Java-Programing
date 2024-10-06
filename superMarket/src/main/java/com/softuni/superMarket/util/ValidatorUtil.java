package com.softuni.superMarket.util;


import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ValidatorUtil {

    <T> boolean isValid(T entity);
    <T> Set<ConstraintViolation<T>> getViolations(T entity);

}
