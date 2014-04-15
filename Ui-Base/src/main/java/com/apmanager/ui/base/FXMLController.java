package com.apmanager.ui.base;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLController implements Initializable {
    
    @FXML
    private AnchorPane actionPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BasicPane pane = new BasicPane();
        AnchorPane.setTopAnchor(pane, 0d);
        AnchorPane.setLeftAnchor(pane, 0d);
        AnchorPane.setRightAnchor(pane, 0d);
        AnchorPane.setBottomAnchor(pane, 0d);
        
        pane.setVisible(true);
        
        actionPane.getChildren().add(pane);
    }    
}
