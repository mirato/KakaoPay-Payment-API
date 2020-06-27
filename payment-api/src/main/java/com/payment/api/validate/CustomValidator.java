package com.payment.api.validate;

import java.util.Collection;

import javax.validation.Validation;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@Controller
public class CustomValidator implements Validator {

    private SpringValidatorAdapter validator;

    public CustomValidator() {
        this.validator = new SpringValidatorAdapter(
            Validation.buildDefaultValidatorFactory().getValidator()
        );
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validator.validate(target, errors);
    }
}