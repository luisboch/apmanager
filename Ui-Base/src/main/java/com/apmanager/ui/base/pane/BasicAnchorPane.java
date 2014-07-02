package com.apmanager.ui.base.pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Luis
 */
public abstract class BasicAnchorPane extends AnchorPane {
    
    private static final Logger log = Logger.getLogger(BasicAnchorPane.class.getName());
    
    protected BasicAnchorPane(String fxml) {
    
        URL fxmlUrl = getClass().getResource(fxml);
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        loader.setResources(ResourceBundle.getBundle("i18n/label"));
        loader.setController(this);
        loader.setRoot(this);
        
        try {
            loader.load();
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        
    }

}
