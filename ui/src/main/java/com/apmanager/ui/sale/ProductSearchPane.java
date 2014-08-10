package com.apmanager.ui.sale;

import com.apmanager.utils.NumberUtils;
import com.apmanager.domain.entity.Product;
import com.apmanager.service.ProductSearchService;
import com.apmanager.service.base.Services;
import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.pane.BasicAnchorPane;
import com.apmanager.ui.base.resource.i18n.I18N;
import java.text.DecimalFormat;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 *
 * @author Luis
 */
public class ProductSearchPane extends BasicAnchorPane {

    @FXML
    private TableView<Product> tbResults;

    @FXML
    private TableColumn<Product, String> cnProduct, cnPrice;

    @FXML
    private TableColumn<Product, Number> cnStock;

    private static final String monetarySymbol = I18N.Config.get("monetary.symbol");
    private static final String monetaryPattern = I18N.Config.get("monetary.pattern");

    @FXML
    private TextField txtSearch;

    private final ProductSearchService productService = Services.getService(ProductSearchService.class);

    private boolean searching = false;

    public ProductSearchPane() {
        super("/fxml/sale/ProductSearch.fxml");

        setupTbResult();

    }

    private void setupTbResult() {

        final ProductSearchPane mySelf = this;

        tbResults.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        cnPrice.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<Product, String> param) {
                                return new SimpleStringProperty(monetarySymbol + " "
                                        + NumberUtils.format(param.getValue().getSellPrice(),
                                                monetaryPattern));
                            }
                });

        cnProduct.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
                        return new SimpleStringProperty(param.getValue().getName());
                    }
                });

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

    /**
     * Called from fxml on user click in search button
     *
     * @param e
     */
    @FXML
    private void search(KeyEvent e) {
        if (txtSearch.getText().length() > 2 && !searching) {
            searching = true;

            final List<Product> result = productService.genericSearch(Product.class, txtSearch.getText());
            tbResults.getItems().clear();
            tbResults.getItems().addAll(result);

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
}
