/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.ui.base.utils.renderer;

import com.apmanager.domain.base.BasicEntity;
import javafx.util.StringConverter;

/**
 *
 * @author luis
 */
public class EntityConverter extends StringConverter<BasicEntity>{

    @Override
    public String toString(BasicEntity object) {
        return object.getDisplayName();
    }

    @Override
    public BasicEntity fromString(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
