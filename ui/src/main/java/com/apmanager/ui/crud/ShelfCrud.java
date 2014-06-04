package com.apmanager.ui.crud;

import com.apmanager.domain.base.SearchFilter;
import com.apmanager.domain.entity.Shelf;
import com.apmanager.domain.entity.dao.filter.ShelfFilter;
import com.apmanager.service.ShelfSearchService;
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
 * @author felipe
 */
@Window(menu = {"records.menu", "shelf.menu"})
public class ShelfCrud extends CrudBase<Shelf, ShelfSearchService> {

    public ShelfCrud() {
        super(Shelf.class, ShelfSearchService.class);

        TableColumn<Shelf, String> tbName = new TableColumn<>();
        tbName.setText("Descrição");
        tbName.setMinWidth(200d);

        tbName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Shelf, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Shelf, String> param) {
                return new SimpleStringProperty(param.getValue().getDescription());
            }
        });
        
        addResultColumn(tbName);
    }

    @Override
    protected CrudEdit<Shelf> getCustomEditPane() {
        return new ShelfEdit();
    }

    @Override
    protected AnchorPane getCustomSearchPane() {
        return null;
    }

    @Override
    public List<Shelf> advancedSearch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected SearchFilter<Shelf> getSearchFilter() {
        return new ShelfFilter();
    }

}
