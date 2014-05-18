package com.apmanager.ui.base;

import com.apmanager.preloader.SwingLoader;
import com.apmanager.service.base.Services;
import com.apmanager.service.base.Validators;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.ImageIcon;

public class MainApp extends Application {

    final static URL iconURL = MainApp.class.getResource("/icon/hammer-icon-128.png");

    final static URL rootResource = MainApp.class.getResource("/fxml/Scene.fxml");

    final static ImageIcon icon = new ImageIcon(iconURL);

    final static SwingLoader loader = new SwingLoader();

    public static Stage stage;

    @Override
    public void start(final Stage stage) throws Exception {

        loader.setMessage("Loading Validators...");
        loader.setPercent(0);
        Validators.initialize();

        loader.setMessage("Loading Services...");
        loader.setPercent(10);
        Services.initialize();

        loader.setMessage("Loading UI...");
        loader.setPercent(20);
        AppManager.initialize();

        loader.setMessage("Starting Connection...");
        loader.setPercent(20);
        Services.connect();

        loader.setMessage("Validating...");
        loader.setPercent(90);

        loader.setVisible(false);

        javafx.application.Platform.runLater(() -> {
            try {

                Parent root = FXMLLoader.load(rootResource);

                final Scene applicationScene = new Scene(root);

                applicationScene.getStylesheets().add("/styles/Styles.css");

                root.setVisible(true);

                stage.getIcons().clear();

                stage.getIcons().add(new Image(iconURL.getPath()));

                stage.centerOnScreen();

                stage.setScene(applicationScene);

                stage.setResizable(true);
                
                stage.setOnHiding((e) -> {
                    System.exit(0);
                });

                stage.show();
                stage.requestFocus();

                MainApp.stage = stage;
            } catch (Exception ex) {
                Logger.getLogger(MainApp.class.getSimpleName()).log(Level.SEVERE, ex.getMessage(), ex);
                System.exit(0);
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        loader.setIconImage(icon.getImage());

        loader.setVisible(true);
        launch(args);

    }

}
