
package com.apmanager.ui;

import com.apmanager.domain.entity.User;
import com.apmanager.ui.base.Crud;
import com.apmanager.ui.base.CrudBase;
import com.apmanager.ui.base.CrudMode;
import com.apmanager.ui.base.CrudState;
import com.apmanager.ui.base.annotation.Window;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author luis
 */
@Window(menu = {"records.menu", "users.menu"})
public class UserCrud extends AnchorPane implements Crud<User>{
    
    
    @FXML
    protected TextField txtSearch;
    
    @FXML
    protected Button btnSearch;
    
    @FXML
    protected ToggleButton btnShowAdvancedSearch;
    
    @FXML
    protected Button btnEdit;
    
    @FXML
    protected Button btnNew;
    
    @FXML 
    protected TableView<User> tbResult;
    
    @FXML 
    protected AnchorPane advancedSearchPane;
    
    @FXML 
    protected AnchorPane resultsPane; 
    
    @FXML
    protected AnchorPane ediPane; 
    
    
    public UserCrud() {
        
        URL fxml = getClass().getResource("/fxml/UserCrud.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        loader.setResources(ResourceBundle.getBundle("i18n/label"));
        loader.setController(this);
        loader.setRoot(this);
        
        try{
            loader.load();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void addResultColumn(TableColumn column) {
        tbResult.getColumns().add(column);
    }

    @Override
    public void addEditField(String label, Node input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSearchField(String label, Node input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> search(Map<String, Object> params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMode(CrudMode mode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setState(CrudState state) {
        if(state == null){
            advancedSearchPane.setVisible(false);
            resultsPane.setVisible(false);
            ediPane.setVisible(false);
            btnShowAdvancedSearch.setSelected(false);
        } else if (state == CrudState.EDIT){
            advancedSearchPane.setVisible(false);
            resultsPane.setVisible(false);
            ediPane.setVisible(true);
            btnShowAdvancedSearch.setSelected(false);
        } else if (state.equals(CrudState.RESULT)){
            advancedSearchPane.setVisible(false);
            resultsPane.setVisible(true);
            ediPane.setVisible(false);
            btnShowAdvancedSearch.setSelected(false);
        } else if (state.equals(CrudState.SEARCH)){
            advancedSearchPane.setVisible(true);
            resultsPane.setVisible(false);
            ediPane.setVisible(false);
            btnShowAdvancedSearch.setSelected(true);
        }
    }
    
    @FXML
    public void simpleSearch(ActionEvent e){
        setState(CrudState.RESULT);
    }
    
    @FXML
    public void showAdvancedSearch(ActionEvent e){
        if(btnShowAdvancedSearch.isSelected()){
            setState(CrudState.SEARCH);
        } else {
            setState(null);
        }
    }
    
    public void newInstance(ActionEvent e){
        setState(CrudState.EDIT);
    }

    
}
