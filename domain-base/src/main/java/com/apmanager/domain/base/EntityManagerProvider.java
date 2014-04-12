package com.apmanager.domain.base;

import javax.persistence.EntityManagerFactory;

/**
 * @author luis
 */
public interface EntityManagerProvider
        extends Comparable<EntityManagerProvider> {

    EntityManagerFactory getFactory();

    boolean isActive();

    int getPriority();

    @Override
    public default int compareTo(EntityManagerProvider o) {
        return Integer.valueOf(getPriority()).compareTo(o.getPriority());
    }

}
