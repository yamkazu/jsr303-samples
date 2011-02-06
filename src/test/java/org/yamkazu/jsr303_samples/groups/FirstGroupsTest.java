package org.yamkazu.jsr303_samples.groups;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

// FORMAT:OFF
public class FirstGroupsTest extends ValidationTestBase {

    interface Group1 {}
    interface Group2 {}

    @NotNull
//  何も指定しないと暗黙的に@NotNull(groups = Default.class)
    Object aaa;

    @NotNull(groups = Group1.class)
    Object bbb;

    @Test
    public void Defaultグループをバリデーションしてみる() throws Exception {
        // 以下の二つは同じ意味
        // グループがDefaultのものだけ検証する
        // ここではaaaだけが検査対象となる
        assertThat(validator.validate(this).size(), is(1));
        assertThat(validator.validate(this, Default.class).size(), is(1));
    }
    
    @Test
    public void Group1バリデーションしてみる() throws Exception {
        // グループがGroup1のものだけが検証する
        // ここではbbbだけが検査対象となる
        assertThat(validator.validate(this, Group1.class).size(), is(1));
    }

}
