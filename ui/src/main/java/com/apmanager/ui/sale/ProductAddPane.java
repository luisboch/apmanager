package com.apmanager.ui.sale;

import com.apmanager.domain.entity.Product;
import com.apmanager.domain.entity.SaleProduct;
import com.apmanager.ui.base.pane.BasicAnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author Luis
 */
public class ProductAddPane extends BasicAnchorPane{
    
    private SaleProduct product;
    
    private Product reference;
    
    @FXML
    private TextField txtProductName;
    
    public ProductAddPane() {
        super("/fxml/sale/ProductAddPane.fxml");
    }
    
    @FXML
    public void cancel(ActionEvent evt){
        setVisible(false);
    }
    
    @FXML
    public void add(ActionEvent evt){
        product = new SaleProduct();
        product.setQuantity(1);
        setVisible(false);
    }

    public SaleProduct getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.reference = product;
        txtProductName.setText(product.getName());
    }
}
