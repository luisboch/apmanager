package com.apmanager.ui.sale;

import com.apmanager.ui.base.pane.BasicAnchorPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 *
 * @author Luis
 */
public class ProductSearchPane extends BasicAnchorPane {

    @FXML
    private TableView tbResults;
    
    public ProductSearchPane() {
        super("/fxml/sale/ProductSearch.fxml");
        
        setupTbResult();
    }

    private void setupTbResult() {
        final ProductSearchPane mySelf = this;
        tbResults.getItems().add(new Object());
        
        tbResults.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    mySelf.setVisible(false);
            }
        });
        
    }
}
