package com.apmanager.ui.sale;

import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.annotation.Window;
import com.apmanager.ui.base.handler.BasicHandler;
import com.apmanager.ui.base.pane.BasicAnchorPane;
import com.apmanager.ui.base.resource.i18n.I18N;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * @author Luis
 */
@Window(isDefault = true, menu = {"activities.menu", "sale.menu"}, priority = 999)
public class SalePane extends BasicAnchorPane {

    private static final Logger log = Logger.getLogger(SalePane.class.getName());

    @FXML
    private TableColumn tbColumnNumber, tbColumnProductName, tbColumnUnityPrice, tbColumnQtde;

    @FXML
    private Button btnCancel, btnFinish, btnAdd;

    private final ProductSearchPane search;

    @FXML
    private TableView results;

    public SalePane() {
        super("/fxml/sale/SalePane.fxml");
        search = new ProductSearchPane();
    }

    @FXML
    public void showSearchPane(ActionEvent evt) {
        final Platform p = Platform.getInstance();
        p.showDialog(search, I18N.Label.get("search"), new BasicHandler<Object>() {
            @Override
            public void handle(Object obj) {
                log.info("Search finished");
                final ProductAddPane productAddPane = new ProductAddPane();
                p.showDialog(productAddPane, I18N.Label.get("add"), new BasicHandler() {
                    @Override
                    public void handle(Object obj) {
                        log.log(Level.INFO, "Is adding product? {0}", productAddPane.getProduct() != null);
                    }
                });
            }
        });
    }

}
