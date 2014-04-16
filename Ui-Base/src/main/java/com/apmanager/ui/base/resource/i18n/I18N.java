/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.ui.base.resource.i18n;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class I18N {

    private static final Logger log = Logger.getLogger(I18N.class.getName());

    public static class Menu {

        private static final ResourceBundle menus
                = ResourceBundle.getBundle("i18n/menu");

        public static String get(String key) {
            if (key == null || key.isEmpty()) {
                return "";
            }
            try {
                return menus.getString(key);
            } catch (Exception ex) {
                log.severe(ex.getLocalizedMessage());
                return "???" + key + "???";
            }
        }
    }
}
