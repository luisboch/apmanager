/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.domain.base.test;

import com.apmanager.domain.base.BasicDAOImpl;
import java.util.List;

/**
 *
 * @author luis
 */
public class BasicDAOImplTest extends BasicDAOImpl<Entity, BasicDAOFilter> {

    @Override
    public List<Entity> search(BasicDAOFilter filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Entity> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
