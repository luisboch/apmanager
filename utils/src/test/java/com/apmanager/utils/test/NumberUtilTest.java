package com.apmanager.utils.test;

import com.apmanager.utils.NumberUtils;
import org.junit.Test;

/**
 *
 * @author luis
 */
public class NumberUtilTest {
    @Test
    public void test(){
        format(123123112101.1099d, "###,###.00");
    }
    
    private void format(Number n, String pattern){
        System.out.println("Pattern: "+pattern+"\t\t Result: "+NumberUtils.format(n, pattern));
    }
    
}
