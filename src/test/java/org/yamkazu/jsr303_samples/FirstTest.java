package org.yamkazu.jsr303_samples;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class FirstTest {

    @Test
    public void 最初の一歩() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Bean bean = new Bean();
        bean.setAaa(false);
        Set<ConstraintViolation<Bean>> violations = validator.validate(bean);
        assertThat(violations.size(), is(1));

        for (ConstraintViolation<Bean> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

}
