package org.yamkazu.jsr303_samples.constraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class MaxAndMinTest extends ValidationTestBase {

    @Max(10)
    String stringValue;

    @Min(-10)
    int intValue;

    @Min(3)
    @Max(4)
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
        MaxAndMinTest bean = new MaxAndMinTest();
        bean.setStringValue("100"); // 10より大きいからNG
        bean.setIntValue(-9); // -10より大きいからOK
        bean.setBigDecimalValue(new BigDecimal("3")); // 範囲内だからOK
        assertThat(validator.validate(bean).size(), is(1));

        printViolations(validator.validate(bean));
    }

    @Test
    public void バリデーションしてみる_02() throws Exception {
        MaxAndMinTest bean = new MaxAndMinTest();
        bean.setStringValue("aaa"); // 10より大きいからNG
        bean.setIntValue(-9); // -10より大きいからOK
        bean.setBigDecimalValue(new BigDecimal("3.5")); // 範囲内だからOK
        assertThat(validator.validate(bean).size(), is(1));

        printViolations(validator.validate(bean));
    }
}
