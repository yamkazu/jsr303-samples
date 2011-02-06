package org.yamkazu.jsr303_samples.constraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class NullAndNotNullTest extends ValidationTestBase {

    @Null
    Object thisIsNull;

    @NotNull
    Object thisIsNotNull;


    public Object getThisIsNotNull() {
        return thisIsNotNull;
    }

    public Object getThisIsNull() {
        return thisIsNull;
    }

    public void setThisIsNotNull(Object thisIsNotNull) {
        this.thisIsNotNull = thisIsNotNull;
    }

    public void setThisIsNull(Object thisIsNull) {
        this.thisIsNull = thisIsNull;
    }

    @Test
    public void バリデーションしてみる() throws Exception {
        NullAndNotNullTest bean = new NullAndNotNullTest();
        bean.setThisIsNotNull(null); // nullなのでNG
        bean.setThisIsNull(new Object()); // nullじゃないのでNG
        assertThat(validator.validate(bean).size(), is(2));

        printViolations(validator.validate(bean));
    }
}
