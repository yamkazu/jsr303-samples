package org.yamkazu.jsr303_samples.list;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.validation.constraints.Pattern;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

// FORMAT:OFF
public class ListTest extends ValidationTestBase{

    @Pattern.List({
        @Pattern(regexp = "^aaa.*"),
        @Pattern(regexp = ".*bbb$")
    })
    String str;

    @Test
    public void バリデーションしてみる() throws Exception {
        str = "ccc";
        assertThat(validator.validate(this).size(), is(2));
        str = "aaa";
        assertThat(validator.validate(this).size(), is(1));
        str = "bbb";
        assertThat(validator.validate(this).size(), is(1));
        str = "aaabbb";
        assertThat(validator.validate(this).size(), is(0));
    }

}
