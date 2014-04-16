package com.apmanager.ui.base;

import com.apmanager.ui.base.annotation.Window;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author luis
 */
@Window(menu = {"admin.menu", "advanced.menu", "blahhh"})
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
