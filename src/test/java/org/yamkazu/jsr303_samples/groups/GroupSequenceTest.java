package org.yamkazu.jsr303_samples.groups;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class GroupSequenceTest extends ValidationTestBase {

    @GroupSequence({ Default.class, Group1.class })
    interface All {
    }

    interface Group1 {
    }


    @NotNull
    Object aaa;

    @NotNull(groups = { Group1.class, Default.class })
    Object bbb;

    @NotNull(groups = Group1.class)
    Object ccc;


    @Test
    public void バリデーションしてみる() throws Exception {
        // 最初にDefaultが実施される
        validateThis(All.class);
        assertThat(validator.validate(this, All.class).size(), is(2));

        aaa = new Object();
        bbb = new Object();
        // Defaultが問題なけれがGroup1が検証される
        assertThat(validator.validate(this, All.class).size(), is(1));
    }

}
