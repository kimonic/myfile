package com.tudoujf;

import android.graphics.Matrix;

import com.tudoujf.utils.StringUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testStringUtils(){
        assertEquals("111.00", StringUtils.getCommaDecimalsStr("111."));
        assertEquals("111.00", StringUtils.getCommaDecimalsStr("111"));
        assertEquals("11.00", StringUtils.getCommaDecimalsStr("11"));
        assertEquals("1.00", StringUtils.getCommaDecimalsStr("1"));
        assertEquals("111.00", StringUtils.getCommaDecimalsStr("111.000"));
        assertEquals("111.00", StringUtils.getCommaDecimalsStr("111.0"));
        assertEquals("111.00", StringUtils.getCommaDecimalsStr("111.00"));
        assertEquals("1,111.00", StringUtils.getCommaDecimalsStr("1111"));
        assertEquals("11,111.00", StringUtils.getCommaDecimalsStr("11111"));
        assertEquals("111,111.00", StringUtils.getCommaDecimalsStr("111111"));
        assertEquals("1,111,111.00", StringUtils.getCommaDecimalsStr("1111111"));
        assertEquals("11,111,111.00", StringUtils.getCommaDecimalsStr("11111111"));
        assertEquals("111,111,111.00", StringUtils.getCommaDecimalsStr("111111111"));
        assertEquals("1,111,111,111.00", StringUtils.getCommaDecimalsStr("1111111111"));
        assertEquals("11,111,111,111.00", StringUtils.getCommaDecimalsStr("11111111111"));
        Matrix matrix=new Matrix();
        float[] temp=new float[9];
        matrix.getValues(temp);
        System.out.println("hahahhahahh"+temp);
    }
}