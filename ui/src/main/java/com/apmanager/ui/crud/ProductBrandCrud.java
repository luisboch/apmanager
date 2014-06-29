package com.apmanager.ui.crud;

import com.apmanager.domain.base.SearchFilter;
import com.apmanager.domain.entity.ProductBrand;
import com.apmanager.domain.entity.dao.filter.ProductBrandFilter;
import com.apmanager.service.ProductBrandSearchService;
import com.apmanager.ui.base.annotation.Window;
import com.apmanager.ui.base.crud.CrudBase;
import com.apmanager.ui.base.crud.CrudEdit;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 *
 * @author Luis
 */

@Window(menu = {"records.menu","product.brand.menu"}, priority = 700)
public class ProductBrandCrud extends CrudBase<ProductBrand, ProductBrandSearchService>{

    public ProductBrandCrud() {
        
        super(ProductBrand.class, ProductBrandSearchService.class);

        TableColumn<ProductBrand, String> tbName = new TableColumn<>();
        
        tbName.setText("Nome");
        
        tbName.setMinWidth(200d);

        tbName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductBrand, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductBrand, String> param) {
                return new SimpleStringProperty(param.getValue().getName());
            }
        });
        
        TableColumn<ProductBrand, String> tbDescription  = new TableColumn<>();
        
        tbDescription.setText("Descrição");
        
        tbDescription.setMinWidth(200d);

        tbDescription.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductBrand, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductBrand, String> param) {
                return new SimpleStringProperty(param.getValue().getDescription());
            }
        });
        
        addResultColumn(tbName);
        addResultColumn(tbDescription);
    }

    
    
    @Override
    protected CrudEdit<ProductBrand> getCustomEditPane() {
        return new ProductBrandEdit();
    }

    @Override
    protected AnchorPane getCustomSearchPane() {
        return null;
    }

    @Override
    public List<ProductBrand> advancedSearch() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected SearchFilter<ProductBrand> getSearchFilter() {
        return new ProductBrandFilter();
    }
}
