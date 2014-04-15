package com.apmanager.ui.base;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author luis
 */
public class BasicPane extends AnchorPane {

    public BasicPane() {
        
        URL resource = getClass().getResource("/fxml/BasicPane.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
