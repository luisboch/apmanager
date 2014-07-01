package com.apmanager.ui.init;

import com.apmanager.ui.base.annotation.Window;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Luis
 */
@Window( menu = {"home.menu"}, priority = 0)
public class InitiPane extends AnchorPane{

    public InitiPane() {
        getChildren().add(new Label("You are Welcome"));
    }
    
}
