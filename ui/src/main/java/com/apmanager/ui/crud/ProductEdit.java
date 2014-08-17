package com.apmanager.ui.crud;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.entity.Product;
import com.apmanager.domain.entity.ProductBrand;
import com.apmanager.domain.entity.Shelf;
import com.apmanager.service.ProductBrandSearchService;
import com.apmanager.service.ShelfSearchService;
import com.apmanager.service.base.Services;
import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.crud.CrudEdit;
import com.apmanager.ui.base.handler.BasicHandler;
import com.apmanager.ui.base.utils.renderer.EntityConverter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.luis.fx.components.input.CurrencyField;
import org.luis.fx.components.input.NumberField;

/**
 *
 * @author felipe
 */
public class ProductEdit extends CrudEdit<Product> {

    private static final Logger log = Logger.getLogger(ProductEdit.class.getName());

    private final ProductBrandSearchService brandService
            = Services.getService(ProductBrandSearchService.class);
    private final ShelfSearchService shelfService
            = Services.getService(ShelfSearchService.class);
    @FXML
    private TextField txtName, txtDesc, txtCode, txtBarcode;

    @FXML
    private CurrencyField txtPurchuasePrice, txtSellPrice;

    @FXML
    private NumberField txtQty, txtMinQty;

    @FXML
    private TextArea txtKeyWords;

    @FXML
    private ComboBox<BasicEntity> cmbBrand, cmbShelf;

    public ProductEdit() {
        super("/fxml/crud/product/ProductEdit.fxml");
        setupRenderers();
        loadObjects();
    }

    @Override
    public Product createNewInstance() {
        return new Product();
    }

    @Override
    public Product buildObject(Product obj, boolean newInstance) {

        obj.setName(txtName.getText());
        obj.setDescription(txtDesc.getText());
        obj.setBarcode(txtBarcode.getText());
        obj.setCode(txtCode.getText());
        obj.setMinQuantity(txtMinQty.getValue());
        obj.setQuantity(txtQty.getValue());
        obj.setUserKeyWords(txtKeyWords.getText());
        obj.setBrand((ProductBrand) cmbBrand.getSelectionModel().getSelectedItem());
        obj.setShelf((Shelf) cmbShelf.getSelectionModel().getSelectedItem());

        Double purchuasePrice = txtPurchuasePrice.getValue();
        Double sellPrice = txtSellPrice.getValue();

        obj.setPurchuasePrice(purchuasePrice == null ? null : purchuasePrice.floatValue());
        obj.setSellPrice(sellPrice == null ? null : sellPrice.floatValue());

        return obj;
    }

    @Override
    public boolean isValid(Product obj, boolean newInstance) {
        return true;
    }

    @Override
    public void load(Product obj) {
        if (obj == null) {
            clear();
        } else {
            txtName.setText(obj.getName());
            txtDesc.setText(obj.getDescription());
            txtCode.setText(obj.getCode());
            txtBarcode.setText(obj.getBarcode());
            txtKeyWords.setText(obj.getUserKeyWords());
            txtMinQty.setValue(obj.getMinQuantity());
            txtQty.setValue(obj.getQuantity());
            txtPurchuasePrice.setValue(obj.getPurchuasePrice() == null ? null : obj.getPurchuasePrice().doubleValue());
            txtSellPrice.setValue(obj.getSellPrice() == null ? null : obj.getSellPrice().doubleValue());
            cmbBrand.getSelectionModel().select(obj.getBrand());
            cmbShelf.getSelectionModel().select(obj.getShelf());
        }
    }

    private void clear() {
        txtName.setText("");
        txtDesc.setText("");
        txtCode.setText("");
        txtBarcode.setText("");
        txtKeyWords.setText("");
        txtMinQty.setValue(0);
        txtQty.setValue(0);
        txtPurchuasePrice.setValue(0d);
        txtSellPrice.setValue(0d);
        cmbBrand.getSelectionModel().clearSelection();
        cmbShelf.getSelectionModel().clearSelection();
    }

    private void setupRenderers() {

        final EntityConverter converter = new EntityConverter();

        cmbBrand.setConverter(converter);
        cmbShelf.setConverter(converter);
    }

    private void loadObjects() {
        cmbBrand.getItems().addAll(brandService.genericSearch(ProductBrand.class, ""));
        cmbShelf.getItems().addAll(shelfService.genericSearch(Shelf.class, ""));
    }
    
    public void addBrand(){
        ProductBrandEdit editor = new ProductBrandEdit();
        Platform.getInstance().createNewInstance(editor, new BasicHandler<ProductBrand>() {

            @Override
            public void handle(ProductBrand obj) {
                if(obj != null){
                    final List<BasicEntity> brands = new ArrayList<>(cmbBrand.getItems());
                    
                    brands.add(obj);
                    
                    Collections.sort(brands);
                    
                    cmbBrand.getItems().clear();
                    cmbBrand.getItems().addAll(brands);
                    
                    cmbBrand.getSelectionModel().clearSelection();
                    cmbBrand.getSelectionModel().select(obj);
                }
            }
        });
    }
    
    public void addShelf(){
        ShelfEdit editor = new ShelfEdit();
        Platform.getInstance().createNewInstance(editor, new BasicHandler<Shelf>() {

            @Override
            public void handle(Shelf obj) {
                if(obj != null){
                    final List<BasicEntity> brands = new ArrayList<>(cmbShelf.getItems());
                    
                    brands.add(obj);
                    
                    Collections.sort(brands);
                    
                    cmbShelf.getItems().clear();
                    cmbShelf.getItems().addAll(brands);
                    
                    cmbShelf.getSelectionModel().clearSelection();
                    cmbShelf.getSelectionModel().select(obj);
                }
            }
        });
    }
}
