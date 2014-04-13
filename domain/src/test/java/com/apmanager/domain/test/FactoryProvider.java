/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.domain.test;

import com.apmanager.domain.base.EntityManagerFactoryProvider;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author luis
 */
public class FactoryProvider
        implements EntityManagerFactoryProvider {

    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("dao-test-pu");
    }

    @Override
    public int getPriority() {
        return 15;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public EntityManagerFactory getFactory() {
        return factory;
    }

}
