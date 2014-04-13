/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.BasicDAO;
import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.SearchFilter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 * @param <E>
 * @param <S> */
public class BasicServiceImpl<E extends BasicEntity, S extends SearchFilter<E>> implements BasicService<E, S> {

    private static final Logger log = Logger.getLogger(BasicServiceImpl.class.getName());

    /**
     * Do use directly, use {@link #getDao() } instead
     */
    @Deprecated
    protected final BasicDAO<E, S> dao;

    EntityManagerProvider provider;

    public BasicServiceImpl(Class<? extends BasicDAO> dao) {

        try {
            this.dao = dao.newInstance();
        } catch (Exception ex) {
            Logger.getLogger(BasicServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    protected void checkInitialization() {
        if (dao == null || provider == null) {
            throw new IllegalStateException("This class does not have initialized correctly,"
                    + "please use ServiceFactory to get an instance");
        }
    }

    @Override
    public List<E> getAll() {
        checkInitialization();
        BasicDAO dao = getDao();
        try {
            return dao.getAll();
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            dao.rollbackTransaction();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<E> search(S filter) {
        checkInitialization();
        
        BasicDAO localDAO = getDao();
        
        try {
            return localDAO.search(filter);
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            localDAO.rollbackTransaction();
            throw new RuntimeException(ex);
        }
    }

    protected BasicDAO getDao() {

        dao.setEntityManager(provider.getEntityManager());

        return dao;
    }

}
