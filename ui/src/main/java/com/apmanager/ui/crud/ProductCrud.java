package com.apmanager.ui.crud;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.SearchFilter;
import com.apmanager.domain.entity.Product;
import com.apmanager.domain.entity.ProductBrand;
import com.apmanager.domain.entity.dao.filter.ProductBrandFilter;
import com.apmanager.domain.entity.dao.filter.ProductFilter;
import com.apmanager.service.ProductBrandSearchService;
import com.apmanager.service.ProductSearchService;
import com.apmanager.ui.base.annotation.Window;
import com.apmanager.ui.base.crud.CrudBase;
import com.apmanager.ui.base.crud.CrudEdit;
import com.apmanager.ui.base.resource.i18n.I18N;
import com.apmanager.ui.base.utils.renderer.NameCellFactory;
import com.apmanager.ui.renderer.ProductPriceCellFactory;
import com.apmanager.utils.NumberUtils;
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
@Window(menu = {"records.menu", "product.menu"}, priority = 800)
public class ProductCrud extends CrudBase<Product, ProductSearchService> {

    public ProductCrud() {
        super(Product.class, ProductSearchService.class);
        setupTbResult();
    }

    @Override
    protected CrudEdit<Product> getCustomEditPane() {
        return new ProductEdit();
    }

    @Override
    protected AnchorPane getCustomSearchPane() {
        return null;
    }

    @Override
    public List<Product> advancedSearch() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected SearchFilter<Product> getSearchFilter() {
        return new ProductFilter();
    }

    private void setupTbResult() {
        
        final TableColumn<BasicEntity, String> tbName = new TableColumn<>("Nome");
        final TableColumn<Product, String> tbBrand = new TableColumn<>("Marca");
        final TableColumn<Product, String> tbSellPrice = new TableColumn<>("Preço venda");
        final TableColumn<Product, String> tbPurchuasePrice = new TableColumn<>("Preço compra");
        final TableColumn<Product, String> tbQty = new TableColumn<>("Qtde Disp");

        tbName.setCellValueFactory(new NameCellFactory());
        tbName.setMinWidth(120d);
        
        tbBrand.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
                if (param == null || param.getValue() == null) {
                    return new SimpleStringProperty("");
                }
                return new SimpleStringProperty(param.getValue().getBrand().getName());
            }
        });

        tbSellPrice.setCellValueFactory(new ProductPriceCellFactory());

        tbPurchuasePrice.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
                return new SimpleStringProperty(
                        I18N.Config.get("monetary.symbol") + " "
                        + NumberUtils.format(param.getValue().getPurchuasePrice(),
                                I18N.Config.get("monetary.pattern")));
            }
        });

        addResultColumn(tbName);
        addResultColumn(tbBrand);
        addResultColumn(tbSellPrice);
        addResultColumn(tbPurchuasePrice);
        addResultColumn(tbQty);

    }
}
