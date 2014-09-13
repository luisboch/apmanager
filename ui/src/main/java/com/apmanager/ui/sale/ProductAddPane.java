package com.apmanager.ui.sale;

import com.apmanager.domain.entity.Product;
import com.apmanager.domain.entity.Sale;
import com.apmanager.domain.entity.SaleProduct;
import com.apmanager.ui.base.pane.BasicAnchorPane;
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
        updateInfo();

    }

    @FXML
    public void cancel(ActionEvent evt) {
        setVisible(false);
    }

    @FXML
    public void add(ActionEvent evt) {

        product = new SaleProduct();
        product.setQuantity(txtQty.getValue());
        product.setProduct(reference);
        product.setSale(sale);
        product.setSellPrice(unitaryPrice.getValue().floatValue());
        product.setPurchuasePrice(product.getPurchuasePrice());
        product.setSellPrice(unitaryPrice.getValue().floatValue());

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
