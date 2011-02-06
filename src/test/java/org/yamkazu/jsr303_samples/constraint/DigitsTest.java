package org.yamkazu.jsr303_samples.constraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Digits;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class DigitsTest extends ValidationTestBase {

    @Digits(integer = 3, fraction = 1)
    String stringValue;

    @Digits(integer = 4, fraction = 0)
    int intValue;

    @Digits(integer = 4, fraction = 3)
    BigDecimal bigDecimalValue;


    public BigDecimal getBigDecimalValue() {
        return bigDecimalValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setBigDecimalValue(BigDecimal bigDecimalValue) {
        this.bigDecimalValue = bigDecimalValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Test
    public void バリデーションしてみる() throws Exception {
        DigitsTest bean = new DigitsTest();
        bean.setStringValue("101.000"); // 少数以下が3桁超えてるのでNG
        bean.setIntValue(1000000000); // 整数部が4桁超えてるのでNG
        bean.setBigDecimalValue(new BigDecimal("1111.123")); // 桁数内なのでOK
        assertThat(validator.validate(bean).size(), is(2));

        Set<ConstraintViolation<DigitsTest>> violations = validator.validate(bean);
        printViolations(violations);
    }
}
