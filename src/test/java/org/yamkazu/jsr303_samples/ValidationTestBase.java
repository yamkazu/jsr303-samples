package org.yamkazu.jsr303_samples;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;

public abstract class ValidationTestBase {

    protected Validator validator;


    @Before
    public void before() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    protected <T> void printViolations(Set<ConstraintViolation<T>> violations) {
        for (ConstraintViolation<T> violation : violations) {
            StringBuilder sb = new StringBuilder();
            sb.append("[Path]" + violation.getPropertyPath() + " ");
            sb.append("[Value]" + violation.getInvalidValue() + " ");
            sb.append("[Message]" + violation.getMessage());
            System.err.println(sb.toString());
        }
    }

    protected <T> void validate(Object object, Class<?>... groups) {
        printViolations(validator.validate(object, groups));
    }

    protected <T> void validateThis(Class<?>... groups) {
        validate(this, groups);
    }

}
