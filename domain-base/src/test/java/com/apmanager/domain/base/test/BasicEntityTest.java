package com.apmanager.domain.base.test;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicManagerDAO;
import com.apmanager.domain.base.BasicManagerDAOImpl;
import com.apmanager.domain.base.BasicSearchDAO;
import com.apmanager.domain.base.BasicSearchDAOImpl;
import java.util.List;

public abstract class BasicEntityTest<E extends BasicEntity> extends BaseTest {

    protected final BasicSearchDAO searchDAO;

    protected final Class<E> targetClass;
    protected final BasicManagerDAO managerDAO;

    protected E instance;

    public BasicEntityTest(Class<E> targetEntityClass) {
        targetClass = targetEntityClass;
        searchDAO = getDAO(BasicSearchDAOImpl.class);
        managerDAO = getDAO(BasicManagerDAOImpl.class);
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

        List<E> dbObjects = searchDAO.getAll(targetClass);

        if (dbObjects.isEmpty()) {
            return false;
        }

        instance = dbObjects.get(0);

        return true;
    }

    public abstract E createNew();
}
