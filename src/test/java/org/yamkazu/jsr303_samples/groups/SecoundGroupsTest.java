package org.yamkazu.jsr303_samples.groups;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

// FORMAT:OFF
public class SecoundGroupsTest extends ValidationTestBase {

    interface Group1 {}

    @NotNull
    Object aaa;

    @NotNull(groups = { Group1.class, Default.class })
    Object bbb;
    
    @NotNull(groups = Group1.class)
    Object ccc;
    

    @Test
    public void Defaultグループをバリデーションしてみる() throws Exception {
        // グループがDefaultのものだけ検証する
        // ここではaaa,bbbだけが検査対象となる
        assertThat(validator.validate(this).size(), is(2));
    }
    
    @Test
    public void Group1バリデーションしてみる() throws Exception {
        // グループがGroup1のものだけが検証する
        // ここではbbb, cccだけが検査対象となる
        assertThat(validator.validate(this, Group1.class).size(), is(2));
    }
    
    @Test
    public void ぜんぶバリデーションしてみる() throws Exception {
        // すべてのグループを検証する
        // ここではaaa, bbb, cccすべてが対象となる
        assertThat(validator.validate(this, Default.class, Group1.class).size(), is(3));
    }

}
