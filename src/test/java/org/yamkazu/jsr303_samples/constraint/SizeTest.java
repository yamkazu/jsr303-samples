package org.yamkazu.jsr303_samples.constraint;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Size;

import org.junit.Test;
import org.yamkazu.jsr303_samples.ValidationTestBase;

public class SizeTest extends ValidationTestBase {

    @Size(min = 10)
    String str;

    @Size(min = 3, max = 6)
    List<Integer> list;

    @Size
    Map<String, Integer> map;

    @Size(max = 100)
    int[] array;


    public int[] getArray() {
        return array;
    }

    public List<Integer> getList() {
        return list;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public String getStr() {
        return str;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Test
    public void バリデーションしてみる() throws Exception {
        SizeTest bean = new SizeTest();
        bean.setArray(new int[] { 1, 2, 3 }); // 100こ以下だからOK
        bean.setList(Arrays.asList(1, 2, 3, 4)); // 3から6こ以内だからOK
        bean.setMap(new HashMap<String, Integer>()); // minが0と同じでOK
        bean.setStr("hoge"); // 10桁以下だからNG
        assertThat(validator.validate(bean).size(), is(1));

        printViolations(validator.validate(bean));
    }
}
