package org.yamkazu.jsr303_samples.customvalidator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class UserIdTest extends ValidationTestBase {

    @UserId
    String userId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Test
    public void カスタムバリデーションを使う() throws Exception {
        UserIdTest bean = new UserIdTest();
        bean.setUserId("bar");
        Set<ConstraintViolation<UserIdTest>> violations = validator.validate(bean);
        for (ConstraintViolation<UserIdTest> violation : violations) {
            System.err.println(violation.getMessage());
        }
    }
}
