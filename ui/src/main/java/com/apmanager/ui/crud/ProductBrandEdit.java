package com.apmanager.ui.crud;

import com.apmanager.domain.entity.ProductBrand;
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
 * @author Luis
 */
public class ProductBrandEdit extends CrudEdit<ProductBrand> {

    private static final Logger log = Logger.getLogger(ProductBrandEdit.class.getName());

    @FXML
    private TextField textFieldName, textFieldDesc;

    public ProductBrandEdit() {

        URL fxmlUrl = getClass().getResource("/fxml/crud/productbrand/ProductBrand.fxml");

        FXMLLoader loader = new FXMLLoader(fxmlUrl);

        loader.setResources(ResourceBundle.getBundle("i18n/label"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ProductBrand createNewInstance() {
        final ProductBrand pb = new ProductBrand();
        return pb;
    }

    @Override
    public ProductBrand buildObject(ProductBrand obj, boolean newInstance) {
        obj.setName(textFieldName.getText());
        obj.setDescription(textFieldDesc.getText());
        return obj;
    }

    @Override
    public boolean isValid(ProductBrand obj, boolean newInstance) {
        return true;
    }

    @Override
    public void load(ProductBrand obj) {
        if (obj != null) {
            textFieldName.setText(obj.getName());
            textFieldDesc.setText(obj.getDescription());
        } else {
            textFieldName.setText("");
            textFieldDesc.setText("");
        }
    }

}
