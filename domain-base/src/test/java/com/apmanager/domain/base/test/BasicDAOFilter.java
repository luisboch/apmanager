/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.domain.base.test;

import com.apmanager.domain.base.SearchFilter;
import java.util.Map;

/**
 *
 * @author luis
 */
public class BasicDAOFilter implements SearchFilter<Entity> {

    @Override
    public Integer getFirstResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getMaxResults() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> getParams() {
        return null;
    }

    @Override
    public Boolean getActive() {
        return true;
    }

}
