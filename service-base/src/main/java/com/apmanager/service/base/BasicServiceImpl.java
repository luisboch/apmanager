/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.BasicDAO;

public abstract class BasicServiceImpl<E extends BasicDAO> implements BasicService<E> {

    EntityManagerProvider provider;

    /**
     * @param provider
     */
    @Override
    public void setProvider(EntityManagerProvider provider) {
        
        if (provider == null) {
            throw new IllegalArgumentException("provider can't be null");
        }
        
        this.provider = provider;
    }
    
    
    protected void checkInitialization() {
        if (provider == null) {
            throw new IllegalStateException("This class does not have initialized correctly,"
                    + "please use ServiceFactory to get an instance");
        }
    }
}
