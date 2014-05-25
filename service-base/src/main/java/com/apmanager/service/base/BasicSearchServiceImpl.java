package com.apmanager.service.base;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicSearchDAO;
import com.apmanager.domain.base.BasicSearchDAOImpl;
import com.apmanager.domain.base.SearchFilter;
import com.apmanager.domain.base.SimpleSearchFilter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

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
    
    public <T extends BasicEntity> T getByID(Class<T> clazz, Integer id){
        
        checkInitialization();
        
        SimpleSearchFilter filter = new SimpleSearchFilter();
        
        filter.setId(id);
        List<T> results = find(clazz, filter);
        
        if(results.isEmpty()){
            throw new NoResultException(
                    "Class: "+clazz.getSimpleName()+" not found with id: "+id);
        } else if (results.size() > 1){
            throw new NonUniqueResultException(
                    "Found more than one result to class: "+clazz.getSimpleName()+" and id: "+id);
        }
        
        return results.get(0);
    }

}
