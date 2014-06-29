package com.apmanager.ui.crud;

import com.apmanager.domain.base.SearchFilter;
import com.apmanager.domain.entity.User;
import com.apmanager.domain.entity.dao.filter.UserFilter;
import com.apmanager.service.UserSearchService;
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
@Window(menu = {"records.menu", "test.menu"}, priority = 0)
public class Test extends CrudBase<User, UserSearchService> {

    public Test() {
        super(User.class, UserSearchService.class);
        setupResult();
    }

    @Override
    protected AnchorPane getCustomSearchPane() {
        return null;
    }

    @Override
    protected SearchFilter<User> getSearchFilter() {
        return new UserFilter();
    }

    @Override
    public List<User> advancedSearch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected CrudEdit<User> getCustomEditPane() {
        return new TestEdit();
    }

    private void setupResult() {

        TableColumn<User, String> tbName = new TableColumn<>();
        tbName.setText("Nome");
        tbName.setMinWidth(200d);

        tbName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getName());
            }
        });

        TableColumn<User, String> tbUserName = new TableColumn<>();
        tbUserName.setText("Login");
        tbUserName.setMinWidth(100d);

        tbUserName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getUsername());
            }
        });

        addResultColumn(tbName);
        addResultColumn(tbUserName);
    }

}
