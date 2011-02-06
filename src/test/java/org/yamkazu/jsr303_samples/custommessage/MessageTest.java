package org.yamkazu.jsr303_samples.custommessage;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Size;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class MessageTest extends ValidationTestBase {

    // 全部10桁以下でNG
    @Size(min = 10)
    String defaultMessage = "hoge";

    @Size(min = 10, message = "サイズが{min}と{max}の間ではないよ")
    String directMessage = "hoge";

    @Size(min = 10, message = "{mykey}")
    String fromPropMessage = "hoge";


    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getDirectMessage() {
        return directMessage;
    }

    public String getFromPropMessage() {
        return fromPropMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public void setDirectMessage(String directMessage) {
        this.directMessage = directMessage;
    }

    public void setFromPropMessage(String fromPropMessage) {
        this.fromPropMessage = fromPropMessage;
    }

    @Test
    public void エラーにしてメッセージをだす() throws Exception {
        Set<ConstraintViolation<MessageTest>> violations = validator.validate(this);
        for (ConstraintViolation<MessageTest> violation : violations) {
            System.err.println(violation.getMessage());
        }
    }
}
