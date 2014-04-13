package com.apmanager.domain.base;

import javax.persistence.EntityManagerFactory;

/**
 * @author luis
 */
public interface EntityManagerFactoryProvider
        extends Comparable<EntityManagerFactoryProvider> {

    EntityManagerFactory getFactory();

    boolean isActive();

    int getPriority();

    @Override
    public default int compareTo(EntityManagerFactoryProvider o) {
        return Integer.valueOf(getPriority()).compareTo(o.getPriority());
    }

}
