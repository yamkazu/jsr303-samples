package org.yamkazu.jsr303_samples.customvalidator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class PeriodTest extends ValidationTestBase {

    @Period(day = 1)
    Date date;


    @Test
    public void バリデーションしてみる() throws Exception {
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        assertThat("今日は大丈夫", validator.validate(this).size(), is(0));

        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
        assertThat("明日まで大丈夫", validator.validate(this).size(), is(0));

        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
        assertThat("明後日はだめ", validator.validate(this).size(), is(1));
    }
}
