package com.tudoujf;

import android.graphics.Matrix;
import android.test.mock.MockContentProvider;
import android.test.mock.MockContext;

import com.tudoujf.utils.StringUtils;

import org.junit.Test;

import java.text.DecimalFormat;

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

    }

    @Test
    public void testStringFormat(){
        assertEquals("0.0",StringUtils.formatString(null));
        assertEquals("0.0",StringUtils.formatString("0"));
        assertEquals("0.0",StringUtils.formatString("0."));
        assertEquals("0.1",StringUtils.formatString("0.123"));
        assertEquals("0.1",StringUtils.formatString("0.1"));
        assertEquals("0.1",StringUtils.formatString("0.12"));
        assertEquals("1230.1",StringUtils.formatString("1230.11112"));
    }

    @Test
    public void testStringToFloat(){
        assertEquals(7106.79,StringUtils.string2Float("7,106.79".replace(",","")),0.01);
        assertEquals(17106.79,StringUtils.string2Float("17,106.79".replace(",","")),0.01);
        assertEquals(117106.79,StringUtils.string2Float("117,106.79".replace(",","")),0.01);
        assertEquals(1117106.79,StringUtils.string2Float("1,117,106.79".replace(",","")),0.01);
    }
}