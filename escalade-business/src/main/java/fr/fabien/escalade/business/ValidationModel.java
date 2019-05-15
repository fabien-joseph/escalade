package fr.fabien.escalade.business;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ValidationModel {
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    public ValidationModel() {
    }

    public ValidationModel(ValidatorFactory factory, Validator validator) {
        this.factory = factory;
        this.validator = validator;
    }

    public List<String> verifyValidity(Object object) {
        List<String> errors = new ArrayList<>();
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        for (ConstraintViolation<Object> violation : violations) {
            errors.add(violation.getMessage());
        }
        return errors;
    }
}
