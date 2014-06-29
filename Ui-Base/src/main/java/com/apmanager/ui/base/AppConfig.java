/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.ui.base;

import com.apmanager.ui.base.data.AppData;
import com.apmanager.ui.base.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class AppConfig {

    private static final Logger log = Logger.getLogger(AppConfig.class.getName());

    private static Properties properties;

    private static boolean _initialized = false;

    public synchronized static void initialize() {
        if (!_initialized) {
            try {
                properties = FileUtils.loadProperties(
                        System.getProperty("user.home")
                        + System.getProperty("file.separator")
                        + "." + Platform.getApplicationName() + ".properties");
                _initialized = true;
            } catch (Exception ex) {
                log.log(Level.SEVERE, ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
        }
    }

    public static Properties getProperties() {
        initialize();
        return properties;
    }

    public static void set(String key, String value) {
        
        properties.put(key, value);

        final Enumeration elements = properties.elements();

        StringBuilder textToWrite = new StringBuilder();

        while (elements.hasMoreElements()) {
            Object k = elements.nextElement();
            Object v = properties.get(k);
            textToWrite.append(k).append(" = ").append(v).append("\n");
        }
        
        try {
            FileUtils.writeToFile(new File(System.getProperty("user.home")
                    + System.getProperty("file.separator") + ".apmanager.properties"), textToWrite.toString());
        } catch (IOException ex) {
            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
