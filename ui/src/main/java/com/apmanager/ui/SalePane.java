package com.apmanager.ui;

import com.apmanager.ui.base.annotation.Window;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

/**
 * @author Luis
 */

@Window(isDefault = true, menu = {"activities.menu", "sale.menu"}, priority = 999)
public class SalePane extends AnchorPane{
    
    private static final Logger log = Logger.getLogger(SalePane.class.getName());
    
    @FXML
    private TableColumn tbColumnNumber, tbColumnProductName, tbColumnUnityPrice, tbColumnQtde;
    
    @FXML
    private Button btnCancel, btnFinish;
    
    
    public SalePane() {
        URL fxmlUrl = getClass().getResource("/fxml/sale/SalePane.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        loader.setResources(ResourceBundle.getBundle("i18n/label"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }
    
    
}
