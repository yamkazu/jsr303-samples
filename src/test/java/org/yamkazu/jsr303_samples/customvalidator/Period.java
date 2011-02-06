package org.yamkazu.jsr303_samples.customvalidator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.yamkazu.jsr303_samples.customvalidator.Period.PeriodValidator;

// FORMAT:OFF
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PeriodValidator.class})
public @interface Period {

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Period[] value();
    }

    class PeriodValidator implements ConstraintValidator<Period, Date> {

        int day;
        
        @Override
        public void initialize(Period period) {
            day = period.day();
        }

        @Override
        public boolean isValid(Date date, ConstraintValidatorContext context) {
            if (date == null) {
                return true;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, day);
            return date.before(calendar.getTime()); 
        }
        
    }
    
    int day() ;

    Class<?>[] groups() default {};

    String message() default "{day}日以内でないとだめです";

    Class<? extends Payload>[] payload() default {};
}
