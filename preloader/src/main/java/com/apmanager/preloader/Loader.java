package com.apmanager.preloader;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

public class Loader extends AnchorPane {

    @FXML
    private Label lblText;

    @FXML
    private ProgressBar progressBar;

    public Loader() {

        URL resource = getClass().getResource("/fxml/Loader.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        progressBar.setProgress(0d);

    }

    public void setPercent(Integer percent) {
        
        if (percent == null || percent < 0 || percent > 100) {
            return;
        }

        progressBar.setProgress(percent.doubleValue());
        
    }
    
    public void setMessage(String message){
        lblText.setText(message);
    }
}
