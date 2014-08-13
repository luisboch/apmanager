package com.apmanager.ui.crud;

import com.apmanager.domain.entity.Shelf;
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
 * @author felipe
 */
public class ShelfEdit extends CrudEdit<Shelf> {

    private static final Logger log = Logger.getLogger(ShelfEdit.class.getName());

    @FXML
    private TextField textFieldName, textFieldDesc;

    public ShelfEdit() {
        super("/fxml/crud/shelf/ShelfEdit.fxml");
    }

    @Override
    public Shelf createNewInstance() {
        return new Shelf();
    }

    @Override
    public Shelf buildObject(Shelf obj, boolean newInstance) {
        obj.setCode(textFieldName.getText());
        obj.setDescription(textFieldDesc.getText());
        return obj;
    }

    @Override
    public boolean isValid(Shelf obj, boolean newInstance) {
        return true;
    }

    @Override
    public void load(Shelf obj) {
        if (obj != null) {
            textFieldName.setText(obj.getCode());
            textFieldDesc.setText(obj.getDescription());
        } else {
            textFieldName.setText("");
            textFieldDesc.setText("");
        }
    }

}
