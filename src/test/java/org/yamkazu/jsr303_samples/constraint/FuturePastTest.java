package org.yamkazu.jsr303_samples.constraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class FuturePastTest extends ValidationTestBase {

    @Past
    Date date;

    @Future
    Calendar calendar;


    public Calendar getCalendar() {
        return calendar;
    }

    public Date getDate() {
        return date;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Test
    public void バリデーションしてみる() throws Exception {
        FuturePastTest bean = new FuturePastTest();
        bean.setDate(new Date(System.currentTimeMillis() - 10000)); // 過去時間なのでOK
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2); // 未来じゃないのでのNG
        bean.setCalendar(calendar);
        assertThat(validator.validate(bean).size(), is(1));

        printViolations(validator.validate(bean));
    }
}
