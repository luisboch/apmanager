package com.apmanager.ui.sale;

import com.apmanager.domain.entity.Product;
import com.apmanager.domain.entity.dao.filter.ProductFilter;
import com.apmanager.service.ProductSearchService;
import com.apmanager.service.base.Services;
import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.pane.BasicAnchorPane;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Luis
 */
public class ProductSearchPane extends BasicAnchorPane {

    @FXML
    private TableView tbResults;

    @FXML
    private TextField txtSearch;

    private final ProductSearchService productService = Services.getService(ProductSearchService.class);

    public ProductSearchPane() {
        super("/fxml/sale/ProductSearch.fxml");

        setupTbResult();

    }

    private void setupTbResult() {

        final ProductSearchPane mySelf = this;

        tbResults.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public Product getSelected() {
        return (Product) tbResults.getSelectionModel().getSelectedItem();
    }

    /**
     * Called from fxml on user click in search button
     *
     * @param e
     */
    @FXML
    private void search(ActionEvent e) {

        final List<Product> result = productService.genericSearch(Product.class, txtSearch.getText());

        tbResults.getItems().clear();
        tbResults.getItems().addAll(result);

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
}
