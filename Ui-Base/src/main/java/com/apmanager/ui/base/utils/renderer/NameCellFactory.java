/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.ui.base.utils.renderer;

import com.apmanager.domain.base.BasicEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author luis
 */
public class NameCellFactory implements Callback<TableColumn.CellDataFeatures<BasicEntity, String>, ObservableValue<String>> {

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<BasicEntity, String> param) {
        
        if (param == null || param.getValue() == null) {
            return new SimpleStringProperty("");
        }
        
        return new SimpleStringProperty(param.getValue().getDisplayName());
    }

}
