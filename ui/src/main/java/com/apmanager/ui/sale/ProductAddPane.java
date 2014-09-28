package com.apmanager.ui.sale;

import com.apmanager.domain.entity.Product;
import com.apmanager.domain.entity.Sale;
import com.apmanager.domain.entity.SaleProduct;
import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.pane.BasicAnchorPane;
import java.util.Date;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.luis.fx.components.input.CurrencyField;
import org.luis.fx.components.input.NumberField;

/**
 *
 * @author Luis
 */
public class ProductAddPane extends BasicAnchorPane {

    private SaleProduct product;

    private Product reference;

    private final Sale sale;

    @FXML
    private TextField txtProductName;

    @FXML
    private NumberField txtStock, txtQty;

    @FXML
    private CurrencyField purchuasePrice, unitaryPrice, total;

    public ProductAddPane(Sale sale, Product product) {
        super("/fxml/sale/ProductAddPane.fxml");
        this.sale = sale;
        this.reference = product;
        txtQty.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                Number n = txtQty.getValue();

                if (n == null) {
                    n = 0;
                }

                total.setValue(n.doubleValue() * unitaryPrice.getValue());
            }
        });
        updateInfo();
    }

    @FXML
    public void cancel(ActionEvent evt) {
        setVisible(false);
    }

    @FXML
    public void add(ActionEvent evt) {

        if (txtQty.getValue() == null || txtQty.getValue().equals(0)) {

            Platform.showError("Selecione a quantidade para adicionar!");
            return;

        }

        product = new SaleProduct();
        product.setQuantity(txtQty.getValue());
        product.setProduct(reference);
        product.setSale(sale);
        product.setSellPrice(unitaryPrice.getValue().floatValue());
        product.setPurchuasePrice(product.getProduct().getPurchuasePrice());
        product.setSellPrice(unitaryPrice.getValue().floatValue());
        product.setCreationDate(new Date());
        product.setLastUpdate(new Date());

        setVisible(false);
    }

    public SaleProduct getProduct() {
        return product;
    }

    private void updateInfo() {

        this.txtProductName.setText(reference.getDisplayName());
        this.purchuasePrice.setValue(reference.getPurchuasePrice().doubleValue());
        this.unitaryPrice.setValue(reference.getSellPrice().doubleValue());
        this.txtStock.setValue(reference.getQuantity());
        this.total.setValue(reference.getSellPrice().doubleValue());

    }
}
