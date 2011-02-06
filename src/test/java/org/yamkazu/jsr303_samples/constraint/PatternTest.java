package org.yamkazu.jsr303_samples.constraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.validation.constraints.Pattern;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class PatternTest extends ValidationTestBase {

    @Pattern(regexp = "hoge")
    String hoge;
    @Pattern(regexp = "bar")
    String bar;


    public String getBar() {
        return bar;
    }

    public String getHoge() {
        return hoge;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public void setHoge(String hoge) {
        this.hoge = hoge;
    }

    @Test
    public void バリデーションしてみる() throws Exception {
        PatternTest bean = new PatternTest();
        bean.setHoge("bar"); // hogeじゃないからNG
        bean.setBar("bar"); // barだからOK
        assertThat(validator.validate(bean).size(), is(1));

        printViolations(validator.validate(bean));
    }

}
