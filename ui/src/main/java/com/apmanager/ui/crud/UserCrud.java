package com.apmanager.ui.crud;

import com.apmanager.domain.base.SearchFilter;
import com.apmanager.domain.entity.User;
import com.apmanager.domain.entity.dao.filter.UserFilter;
import com.apmanager.service.UserSearchService;
import com.apmanager.ui.base.crud.CrudBase;
import com.apmanager.ui.base.annotation.Window;
import com.apmanager.ui.base.crud.CrudEdit;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author luis
 */
@Window(menu = {"records.menu", "users.menu"})
public class UserCrud extends CrudBase<User, UserSearchService> {


    @FXML
    protected VBox vboxSearchPane;

    public UserCrud() {
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
        return new UserEdit();
    }

    private void setupResult() {
    
        TableColumn<User,String> tbName = new TableColumn<>();
        tbName.setText("Nome");
        
        
        tbName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
             @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getName());
            }
        });
        
        TableColumn<User,String> tbUserName = new TableColumn<>();
        tbUserName.setText("Login");
        
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
