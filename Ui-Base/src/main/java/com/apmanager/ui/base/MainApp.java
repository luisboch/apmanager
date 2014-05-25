package com.apmanager.ui.base;

import com.apmanager.service.base.Services;
import com.apmanager.service.base.Validators;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.ImageIcon;

public class MainApp extends Application {

    final static URL iconURL = MainApp.class.getResource("/icon/hammer-icon-128.png");

    final static URL rootResource = MainApp.class.getResource("/fxml/Scene.fxml");

    final static ImageIcon icon = new ImageIcon(iconURL);

    public static Stage stage;

    public static boolean closeable = true;

    @Override
    public void start(final Stage stage) throws Exception {

        stage.getIcons().clear();

        stage.getIcons().add(new Image(iconURL.getPath()));
        
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
