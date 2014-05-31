package com.apmanager.ui.base;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

    final static URL rootResource = MainApp.class.getResource("/fxml/Scene.fxml");

    public static Stage stage;

    public static boolean closeable = true;

    @Override
    public void start(final Stage stage) throws Exception {

        stage.getIcons().add(new Image("file:resources/icon/hammer-icon-128.png"));
        
        MainApp.stage = stage;
        
        javafx.application.Platform.runLater(() -> {
            try {

                javafx.application.Platform.setImplicitExit(false);

                AnchorPane root = FXMLLoader.load(rootResource);

                final Scene applicationScene = new Scene(root);

                applicationScene.getStylesheets().add("/styles/Styles.css");

                stage.centerOnScreen();

                stage.setScene(applicationScene);
                stage.setMinWidth(root.getMinWidth());
                stage.setResizable(true);
                stage.setMaximized(true);
                stage.show();
                stage.requestFocus();

                stage.setOnHiding(new EventHandler<WindowEvent>() {

                    @Override
                    public void handle(WindowEvent event) {
                        if (closeable) {
                            System.exit(0);
                        } else {
                            stage.show();
                        }
                    }
                });
            } catch (Exception ex) {
                Logger.getLogger(MainApp.class.getSimpleName()).log(Level.SEVERE, ex.getMessage(), ex);
                System.exit(0);
            }
        });

    }

    public static boolean isCloseable() {
        return closeable;
    }

    public static void setCloseable(boolean closeable) {
        MainApp.closeable = closeable;
    }
}
