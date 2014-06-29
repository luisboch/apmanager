/**
 * AppData.java
 */

package com.apmanager.ui.base.data;

import com.apmanager.domain.base.Computer;

/**
 * @author luis
 * @since May 25, 2014
 */
public class AppData {
    private static Computer computer;

    public static Computer getComputer() {
        return computer;
    }

    public static void setComputer(Computer computer) {
        AppData.computer = computer;
    }
}
