/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.ui.crud.provider;

import com.apmanager.domain.base.EntityManagerFactoryProvider;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author luis
 */
public class EntityManagerProvider implements EntityManagerFactoryProvider {

    private static EntityManagerFactory factory;

    @Override
    public synchronized EntityManagerFactory getFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("apmanager-pu");
        }
        return factory;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public int compareTo(EntityManagerFactoryProvider o) {
        if (o == null) {
            return 1;
        } else {
            return Integer.valueOf(getPriority()).compareTo(o.getPriority());
        }
    }

}
