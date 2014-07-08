package com.apmanager.ui.sale;

import com.apmanager.domain.entity.SaleProduct;
import com.apmanager.ui.base.pane.BasicAnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author Luis
 */
public class ProductAddPane extends BasicAnchorPane{
    
    SaleProduct product;
    
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

    public void setProduct(SaleProduct product) {
        this.product = product;
    }
}
