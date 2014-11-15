package com.apmanager.ui.sale;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.entity.Product;
import com.apmanager.service.ProductSearchService;
import com.apmanager.service.base.Services;
import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.pane.BasicAnchorPane;
import com.apmanager.ui.base.utils.renderer.NameCellFactory;
import com.apmanager.ui.renderer.ProductPriceCellFactory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 *
 * @author Luis
 */
public class ProductSearchPane extends BasicAnchorPane {

    private static final Logger log = Logger.getLogger(ProductSearchPane.class.getName());

    @FXML
    private TableView<Product> tbResults;

    @FXML
    private TableColumn<Product, String> cnPrice;
    @FXML
    private TableColumn<BasicEntity, String> cnProduct;

    @FXML
    private TableColumn<Product, Number> cnStock;

    @FXML
    private TextField txtSearch;

    private final ProductSearchService productService = Services.getService(ProductSearchService.class);

    private boolean searching = false;

    public ProductSearchPane() {
        super("/fxml/sale/ProductSearch.fxml");

        setupTbResult();
        txtSearch.addEventHandler(KeyEvent.KEY_RELEASED, new SearchKeyHandler());

    }

    private void setupTbResult() {

        tbResults.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        cnPrice.setCellValueFactory(new ProductPriceCellFactory());

        cnProduct.setCellValueFactory(new NameCellFactory());

        cnStock.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Product, Number>, ObservableValue<Number>>() {

                    @Override
                    public ObservableValue<Number> call(TableColumn.CellDataFeatures<Product, Number> param) {
                        return new SimpleIntegerProperty(param.getValue().getQuantity());
                    }
                });
    }

    public Product getSelected() {
        return (Product) tbResults.getSelectionModel().getSelectedItem();
    }

    private void search(KeyEvent e) {
        try {
            if ((txtSearch.getText().length() > 2
                    || e.getCode() == KeyCode.ENTER) && !searching) {
                searching = true;

                final List<Product> result
                        = productService.genericSearch(
                                Product.class, txtSearch.getText());
                tbResults.getItems().clear();
                tbResults.getItems().addAll(result);

                searching = false;
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            searching = false;
        }

    }

    @FXML
    private void cancel(ActionEvent e) {
        tbResults.getSelectionModel().clearSelection();
        setVisible(false);
    }

    @FXML
    private void confirm(ActionEvent e) {
        if (tbResults.getSelectionModel().getSelectedItem() == null) {
            Platform.showError("Você não selecionou nenhum produto");
        } else {
            setVisible(false);
        }
    }

    private class SearchKeyHandler
            implements javafx.event.EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            search(event);
        }
    }
}
