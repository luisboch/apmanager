package com.apmanager.preloader;

import com.apmanager.service.base.Services;
import com.apmanager.service.base.Validators;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luis
 */
public class Preloader extends javafx.application.Preloader {

    private Stage stage;
    private LoaderPane loader;

    private Scene createPreloaderScene() {
        loader = new LoaderPane();
        return new Scene(loader, 300, 150);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(createPreloaderScene());
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        loader.setPercent(Double.valueOf(pn.getProgress()).intValue());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            loader.setMessage("Loading Validators...");
            loader.setPercent(90);
            Validators.initialize();
            
            loader.setMessage("Loading Services...");
            loader.setPercent(92);
            Services.initialize();

            loader.setMessage("Starting Connection...");
            loader.setPercent(97);
            Services.connect();

            loader.setMessage("Validating...");
            loader.setPercent(100);

            loader.setVisible(false);
            stage.hide();
        }
    }
}
