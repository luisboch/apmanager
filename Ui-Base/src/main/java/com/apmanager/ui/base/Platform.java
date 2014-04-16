package com.apmanager.ui.base;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

public class Platform implements Initializable {

    @FXML
    private AnchorPane actionPane;
    @FXML
    private MenuBar menuBar;

    private Class<AnchorPane> currentClass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AppManager.loadMenu();
        AppManager.setMenu(menuBar);
        AppManager.setPlatform(this);

    }

    public synchronized void set(AnchorPane pane, Class<AnchorPane> clazz) {

        if (currentClass == null || clazz == null || !clazz.equals(currentClass)) {
            
            currentClass = clazz;
            
            if (!actionPane.getChildren().isEmpty()) {

                for (Node n : actionPane.getChildren()) {
                    close(n);
                }

                actionPane.getChildren().clear();
            }

            if (pane != null) {
                open(pane);

                AnchorPane.setTopAnchor(pane, 0d);
                AnchorPane.setLeftAnchor(pane, 0d);
                AnchorPane.setRightAnchor(pane, 0d);
                AnchorPane.setBottomAnchor(pane, 0d);

                pane.setVisible(true);

                actionPane.getChildren().add(pane);
            }
        }
    }

    private void close(Node n) {
    }

    private void open(AnchorPane pane) {
    }

}
