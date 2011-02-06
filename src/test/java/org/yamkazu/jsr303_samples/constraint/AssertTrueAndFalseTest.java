package org.yamkazu.jsr303_samples.constraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class AssertTrueAndFalseTest extends ValidationTestBase {

    @AssertTrue
    boolean mustTrue;
    @AssertFalse
    boolean mustFalse;


    public boolean isMustFalse() {
        return mustFalse;
    }

    public boolean isMustTrue() {
        return mustTrue;
    }

    public void setMustFalse(boolean mustFalse) {
        this.mustFalse = mustFalse;
    }

    public void setMustTrue(boolean mustTrue) {
        this.mustTrue = mustTrue;
    }

    @Test
    public void バリデーションしてみる() throws Exception {
        AssertTrueAndFalseTest bean = new AssertTrueAndFalseTest();
        bean.setMustFalse(true);
        bean.setMustTrue(false);
        assertThat(validator.validate(bean).size(), is(2));
    }

}
