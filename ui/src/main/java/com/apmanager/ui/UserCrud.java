/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.ui;

import com.apmanager.ui.base.annotation.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author luis
 */
@Window(menu = {"records.menu", "users.menu"})
public class UserCrud extends AnchorPane {

    public UserCrud() {
        
        URL fxml = getClass().getResource("/fxml/UserCrud.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        loader.setController(this);
        loader.setRoot(this);
        
        try{
            loader.load();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
        
    }
    
}
