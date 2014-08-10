package com.apmanager.utils;


import java.text.DecimalFormat;

/**
 *
 * @author luis
 */
public class NumberUtils {
    
    public static String format(Number n, String pattern){
        
        final DecimalFormat formatter =  new DecimalFormat(pattern);
        
        return formatter.format(n);
    }
    
}
