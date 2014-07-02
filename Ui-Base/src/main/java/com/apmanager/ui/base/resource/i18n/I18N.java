/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.ui.base.resource.i18n;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class I18N {

    private static final Logger log = Logger.getLogger(I18N.class.getName());

    public static class Menu {

        private static ResourceBundle menu;

        static {
            try {
                menu = ResourceBundle.getBundle("i18n/menu", Locale.getDefault());
                log.info("Loaded i8n menu");
            } catch (Exception ex) {
                log.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        public static String get(String key) {
            
            if(menu == null){
                log.log(Level.SEVERE, "Can't find menus.properties in i18n path");
                return "";
            }
            
            if (key == null || key.isEmpty()) {
                return "";
            }
            try {
                return menu.getString(key);
            } catch (Exception ex) {
                log.severe(ex.getLocalizedMessage());
                return "???" + key + "???";
            }
        }
    }
    
    
    public static class Label {

        private static ResourceBundle label;

        static {
            try {
                label = ResourceBundle.getBundle("i18n/label", Locale.getDefault());
                log.info("Loaded i8n menu");
            } catch (Exception ex) {
                log.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        public static String get(String key) {
            
            if(label == null){
                log.log(Level.SEVERE, "Can't find menus.properties in i18n path");
                return "";
            }
            
            if (key == null || key.isEmpty()) {
                return "";
            }
            try {
                return label.getString(key);
            } catch (Exception ex) {
                log.severe(ex.getLocalizedMessage());
                return "???" + key + "???";
            }
        }
    }
}
