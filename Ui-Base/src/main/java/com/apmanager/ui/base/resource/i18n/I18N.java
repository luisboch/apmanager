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

    private static class Resource {

        private final ResourceBundle resource;
        private final String resourceName;

        public Resource(String resource) {

            resourceName = resource;

            try {

                this.resource = ResourceBundle.getBundle("i18n/" + resource,
                        Locale.getDefault());
                log.log(Level.INFO, "Loaded i8n {0}", resourceName);

            } catch (Exception ex) {

                log.log(Level.SEVERE, "Can't find {0}.properties in i18n path",
                        resourceName);
                log.log(Level.SEVERE, ex.getMessage(), ex);

                throw ex;
            }
        }

        public String get(String key) {

            if (resource == null) {
                return "";
            }

            if (key == null || key.isEmpty()) {
                return "";
            }
            try {
                return resource.getString(key);
            } catch (Exception ex) {
                log.severe(ex.getLocalizedMessage());
                return "???" + key + "???";
            }
        }
    }

    public static class Menu {

        private static final Resource menu;

        static {
            menu = new Resource("menu");
        }

        public static String get(String key) {
            return menu.get(key);
        }
    }

    public static class Label {

        private static final Resource label;

        static {
            label = new Resource("label");
        }

        public static String get(String key) {
            return label.get(key);
        }
    }

    public static class Config {

        private static final Resource config;

        static {
            config = new Resource("app_config");
        }

        public static String get(String key) {
            return config.get(key);
        }
    }
    
    public static class Message {

        private static final Resource message;

        static {
            message = new Resource("message");
        }

        public static String get(String key) {
            return message.get(key);
        }
    }
}
