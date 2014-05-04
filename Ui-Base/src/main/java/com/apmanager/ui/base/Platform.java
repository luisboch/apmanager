package com.apmanager.ui.base;

import com.apmanager.ui.base.annotation.Close;
import com.apmanager.ui.base.annotation.Open;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Platform implements Initializable {

    @FXML
    private AnchorPane actionPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label currentPane;

    private Class<AnchorPane> currentClass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AppManager.setMenu(menuBar);
        AppManager.setPlatform(this);

    }

    public void setTitle(String title) {
        if (MainApp.stage != null) {
            MainApp.stage.setTitle("APManager - " + title);
            currentPane.setText(title);
        }
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
        if (n != null) {
            final Class<? extends Node> nodeClass = n.getClass();
            final Method[] methods = nodeClass.getMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(Close.class)) {
                    try {
                        m.invoke(n);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(Platform.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void open(Node n) {
        if (n != null) {
            final Class<? extends Node> nodeClass = n.getClass();
            final Method[] methods = nodeClass.getMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(Open.class)) {
                    try {
                        m.invoke(n);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(Platform.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public static void showMessage(final String message) {
        
        Button btn = new Button();

        final Stage stage = new Stage();
        //Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
        //Set the owner of the Stage 
        stage.initOwner(MainApp.stage);
        stage.setTitle("Atençao");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
            }
        });

        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("OK");
        
        VBox vBox = new VBox();
        
        Text text = new Text(message);
        
        VBox.setMargin(text, new Insets(20, 15, 20, 15));
        VBox.setMargin(btn, new Insets(-1d, 15, 20, -1d));
        
        vBox.getChildren().add(text);
        
        vBox.getChildren().add(btn);
        
        root.getChildren().add(vBox);
        stage.setScene(scene);
        stage.show();
    }
}
