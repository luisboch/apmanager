/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.BasicDAO;
import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicSearchDAO;
import com.apmanager.domain.base.BasicSearchDAOImpl;
import com.apmanager.domain.base.SearchFilter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author luis
 */
public class BasicSearchServiceImpl
        extends BasicServiceImpl<BasicSearchDAO>
        implements BasicSearchService {

    private static final Logger log = Logger.getLogger(BasicSearchServiceImpl.class.getName());

    /**
     * Do use directly, use {@link #getDao() } instead
     */
    private final BasicSearchDAO dao;

    EntityManagerProvider provider;

    public BasicSearchServiceImpl() {
        try {
            this.dao = new BasicSearchDAOImpl();
        } catch (Exception ex) {
            Logger.getLogger(BasicSearchServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T extends BasicEntity> List<T> find(Class<T> clazz, SearchFilter<? extends T> filter) {
        return getDAO().find(clazz, filter);
    }

    @Override
    public <T extends BasicEntity> List<T> getAll(Class<T> clazz) {
        return getDAO().getAll(clazz);
    }

    @Override
    public BasicSearchDAO getDAO() {

        checkInitialization();

        dao.setEntityManager(provider.getEntityManager());

        return dao;
    }

}
