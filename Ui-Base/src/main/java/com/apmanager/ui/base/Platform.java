package com.apmanager.ui.base;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.service.base.Services;
import com.apmanager.service.base.Validators;
import com.apmanager.ui.base.annotation.AppListener;
import com.apmanager.ui.base.annotation.Close;
import com.apmanager.ui.base.annotation.Open;
import com.apmanager.ui.base.crud.CrudEdit;
import com.apmanager.ui.base.crud.DialogEdit;
import com.apmanager.ui.base.handler.BasicHandler;
import com.apmanager.utils.ReflectionUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.luis.fx.components.Messages;
import org.luis.fx.components.message.Type;

public class Platform implements Initializable {

    private static final Logger log = Logger.getLogger(Platform.class.getName());

    @FXML
    private AnchorPane actionPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label currentPane;

    @FXML
    private Messages messages;

    @FXML
    private FlowPane loaderPane;

    @FXML
    private Pane bgPane;

    private static Messages messagesInstance;

    private Class<AnchorPane> currentClass;

    private static Platform instance;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        messagesInstance = messages;
        load(new Runnable() {
            @Override
            public void run() {
                MainApp.setCloseable(false);
                Validators.initialize();
                Services.initialize();
                Services.connect();
                AppManager.setMenu(menuBar);
                AppManager.setPlatform(Platform.this);
                loadListeners();
                MainApp.setCloseable(true);
            }
        });
        instance = this;
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

    public static void showInfo(final String message) {
        messagesInstance.showMessage(message, Type.INFO);
    }

    public static void showWarn(final String message) {
        messagesInstance.showMessage(message, Type.WARNING);
    }

    public static void showError(final String message) {
        messagesInstance.showMessage(message, Type.ERROR);
    }

    public static void showSuccess(final String message) {
        messagesInstance.showMessage(message, Type.SUCCESS);
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

    public static <E extends BasicEntity> void createNewInstance(
            CrudEdit<E> editor, BasicHandler<E> handler) {

        if (editor != null) {

            final Stage stage = new Stage();

            // Initialize the Stage with type of modal
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(true);

            // Set the owner of the Stage 
            stage.initOwner(MainApp.stage);
            stage.setTitle("Atençao");

            final DialogEdit dialogEdit = new DialogEdit(editor, new BasicHandler<E>() {

                @Override
                public void handle(E obj) {

                    stage.hide();

                    if (handler != null) {
                        handler.handle(obj);
                    }
                }
            });

            final Scene scene = new Scene(dialogEdit);

            stage.setScene(scene);
            stage.show();

        }
    }

    public void load(Runnable r) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showLoader();
                final FadeTransition fadeIn = new FadeTransition(Duration.millis(200), loaderPane);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);

                fadeIn.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    r.run();
                                } catch (Exception ex) {
                                    log.log(Level.SEVERE, ex.getMessage(), ex);
                                }

                                hideLoader();
                            }
                        }, "Platform-loader");

                        t.start();

                    }
                });

                fadeIn.play();
            }
        });
    }

    private void showLoader() {
        loaderPane.setVisible(true);
        bgPane.setVisible(true);
    }

    public void hideLoader() {

        final FadeTransition f = new FadeTransition(Duration.millis(200), loaderPane);
        f.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                loaderPane.setVisible(false);
                bgPane.setVisible(false);
            }
        });
        f.setFromValue(1);
        f.setToValue(0);
        f.play();

    }

    public void loadListeners() {
        Set<Class<?>> listeners = ReflectionUtils.getTypesAnnotatedWith(AppListener.class);
        for (Class<?> c : listeners) {
            try {
                final Object instance = c.newInstance();
                final Method[] methods = c.getMethods();
                for (Method m : methods) {
                    if (m.isAnnotationPresent(Open.class)) {
                        m.invoke(instance);
                    }
                }
            } catch (IllegalAccessException |
                    IllegalArgumentException |
                    InvocationTargetException |
                    InstantiationException ex) {
                Logger.getLogger(Platform.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Platform getInstance() {
        return instance;
    }
}