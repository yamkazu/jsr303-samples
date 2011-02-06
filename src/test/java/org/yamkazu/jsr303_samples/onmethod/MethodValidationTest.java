package org.yamkazu.jsr303_samples.onmethod;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class MethodValidationTest extends ValidationTestBase {

    String pass1 = "pass";
    String pass2 = "passsssss";
    int a = 2;
    int b = 4;


    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String getPass1() {
        return pass1;
    }

    public String getPass2() {
        return pass2;
    }

    @Max(value = 5, message = "合計は{value}以下じゃないとダメ")
    public int getSum() {
        return a + b;
    }

    @AssertTrue(message = "パスワードが一致しません")
    public boolean isComparePass() {
        return pass1.equals(pass2);
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    @Test
    public void メソッドについてバリデーションしてみる() throws Exception {
        MethodValidationTest bean = new MethodValidationTest();
        Set<ConstraintViolation<MethodValidationTest>> violations = validator.validate(bean);
        for (ConstraintViolation<MethodValidationTest> violation : violations) {
            System.err.println(violation.getMessage());
        }
    }
}
