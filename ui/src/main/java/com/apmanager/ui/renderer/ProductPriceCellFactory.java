package com.apmanager.ui.renderer;

import com.apmanager.domain.entity.Product;
import com.apmanager.ui.base.resource.i18n.I18N;
import com.apmanager.utils.NumberUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author luis
 */
public class ProductPriceCellFactory
        implements Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>> {

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
        return new SimpleStringProperty(
                I18N.Config.get("monetary.symbol") + " "
                + NumberUtils.format(param.getValue().getSellPrice(),
                        I18N.Config.get("monetary.pattern")));
    }

}
