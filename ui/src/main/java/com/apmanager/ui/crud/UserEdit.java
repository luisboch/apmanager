package com.apmanager.ui.crud;

import com.apmanager.domain.entity.User;
import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.crud.CrudEdit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

/**
 *
 * @author luis
 */
public class UserEdit extends CrudEdit<User> {

    @FXML
    private TextField txtName, txtUserName, txtPasswd1, txtPasswd2;

    private static final Logger log = Logger.getLogger(UserEdit.class.getName());

    public UserEdit() {
        super("/fxml/crud/user/UserEdit.fxml");
    }

    @Override
    public User buildObject(User obj, boolean newInstance) {

        obj.setName(txtName.getText());
        obj.setUsername(txtUserName.getText());

        if (newInstance) {
            obj.setPasswd(txtPasswd1.getText());
        } else if (!txtPasswd1.getText().isEmpty()) {
            obj.setPasswd(txtPasswd1.getText());
        }

        return obj;
    }

    @Override
    public boolean isValid(User obj, boolean newInstance) {

        if (newInstance) {
            if (txtPasswd1.getText().isEmpty() || !txtPasswd2.getText().equals(txtPasswd1.getText())) {
                Platform.showInfo("Confira a senha!");
                javafx.application.Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        txtPasswd1.requestFocus();
                    }
                });
                return false;
            }
        } else if (!txtPasswd1.getText().isEmpty() && !txtPasswd2.getText().equals(txtPasswd1.getText())) {
            Platform.showWarn("Confira a senha!");
            return false;
        }

        return true;
    }

    @Override
    public void load(User obj) {

        txtPasswd1.setText("");
        txtPasswd2.setText("");

        if (obj == null) {
            txtName.setText("");
            txtUserName.setText("");
        } else {
            txtName.setText(obj.getName());
            txtUserName.setText(obj.getUsername());
        }
    }

    @Override
    public User createNewInstance() {
        return new User();
    }
}
