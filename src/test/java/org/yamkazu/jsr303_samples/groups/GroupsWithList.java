package org.yamkazu.jsr303_samples.groups;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.validation.constraints.Max;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

// FORMAT:OFF
public class GroupsWithList extends ValidationTestBase {

    interface NormalUser {
    }

    interface PremiumUser{
    }
    
    @Max.List({
        @Max(value = 10, groups = NormalUser.class),
        @Max(value = 100, groups = PremiumUser.class)
    })
    int diskSize;

    @Test
    public void NormalUserでバリデーションしてみる() throws Exception {
        diskSize = 9;
        assertThat(validator.validate(this, NormalUser.class).size(), is(0));
        
        diskSize = 50;
        assertThat("一般ユーザなので10超えててNG", validator.validate(this, NormalUser.class).size(), is(1));
    }
    
    @Test
    public void PremiumUserでバリデーションしてみる() throws Exception {
        diskSize = 9;
        assertThat(validator.validate(this, PremiumUser.class).size(), is(0));
        
        diskSize = 50;
        assertThat("プレミアムユーザなので100までOK", validator.validate(this, PremiumUser.class).size(), is(0));
    }
}
