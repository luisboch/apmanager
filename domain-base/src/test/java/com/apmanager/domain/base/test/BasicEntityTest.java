package com.apmanager.domain.base.test;

import com.apmanager.domain.base.BasicDAO;
import com.apmanager.domain.base.BasicEntity;

public abstract class BasicEntityTest<E extends BasicEntity, D extends BasicDAO> extends BaseTest {

    protected final D dao;
    protected E instance;

    public BasicEntityTest(Class<D> daoClass) {
        dao = getDAO(daoClass);
    }

    public E getEntity() {
        return getEntity(false);
    }

    public E getEntity(boolean forceNew) {
        if (forceNew || !find()) {
            return createNew();
        } else {
            return instance;
        }
    }

    public final boolean find() {

        java.util.List<E> dbObjects = dao.getAll();

        if (dbObjects.isEmpty()) {
            return false;
        }

        instance = dbObjects.get(0);

        return true;
    }

    public abstract E createNew();
}
