package com.apmanager.service.base;

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

    public BasicSearchServiceImpl() {
        try {
            this.dao = new BasicSearchDAOImpl();
        } catch (Exception ex) {
            Logger.getLogger(BasicSearchServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    public BasicSearchServiceImpl(BasicSearchDAO dao) {
        this.dao = dao;
        if (this.dao == null) {
            throw new IllegalArgumentException("[BasicSearchService] dao can't be null!");
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

        dao.setEntityManager(getProvider().getEntityManager());

        return dao;
    }

    @Override
    public <T extends BasicEntity> List<T> genericSearch(Class<T> clazz, String search) {
        checkInitialization();
        return getDAO().genericSearch(clazz, search);
    }

}
