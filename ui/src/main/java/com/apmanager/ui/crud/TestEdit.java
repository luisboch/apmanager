package com.apmanager.ui.crud;

import com.apmanager.domain.entity.User;
import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.crud.CrudEdit;
import com.apmanager.ui.base.handler.BasicHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Luis
 */
public class TestEdit extends CrudEdit<User> {

    public TestEdit() {

        URL fxmlUrl = getClass().getResource("/fxml/Test.fxml");

        FXMLLoader loader = new FXMLLoader(fxmlUrl);

        loader.setResources(ResourceBundle.getBundle("i18n/label"));
        loader.setController(this);
        loader.setRoot(this);
        
        try {

            loader.load();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void add(ActionEvent e) {

        Platform.getInstance().createNewInstance(new UserEdit(), new BasicHandler<User>() {
            @Override
            public void handle(User obj) {
                if (obj == null) {
                    System.out.println("Canceled");
                } else {
                    System.out.println("User:" + obj.getUsername());
                }
            }
        });
    }

    @Override
    public User buildObject(User obj, boolean newInstance) {
        return obj;
    }

    @Override
    public void load(User obj) {
    }

    @Override
    public boolean isValid(User obj, boolean newInstance) {
        return true;
    }

    @Override
    public User createNewInstance() {
        return new User();
    }
    
}