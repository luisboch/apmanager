/**
 * BasicEntityServiceImpl.java
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicManagerDAO;
import com.apmanager.domain.base.BasicManagerDAOImpl;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class BasicEntityServiceImpl extends BasicServiceImpl<BasicManagerDAO> implements BasicEntityService {

    private final BasicManagerDAO dao = new BasicManagerDAOImpl();

    private static final Logger log = Logger.getLogger(BasicEntityServiceImpl.class.getName());

    @Override
    public String toString() {
        return "BasicServiceImpl{" + '}';
    }

    @Override
    public <E extends BasicEntity> void save(E entity) {
        save(entity, "ALL");
    }

    @Override
    public <E extends BasicEntity> void save(E entity, String context) {

        validate(entity, ActionType.SAVE, context);

        BasicManagerDAO localDAO = getDAO();

        entity.setLastUpdate(new Date());
        entity.setCreationDate(new Date());

        try {
            localDAO.beginTransaction();
            localDAO.save(entity);
            localDAO.commitTransaction();
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            localDAO.rollbackTransaction();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <E extends BasicEntity> void update(E entity) {
        update(entity, "ALL");
    }

    @Override
    public <E extends BasicEntity> void update(E entity, String context) {

        validate(entity, ActionType.UPDATE, context);

        BasicManagerDAO localDAO = getDAO();

        entity.setLastUpdate(new Date());

        try {
            localDAO.beginTransaction();
            localDAO.update(entity);
            localDAO.commitTransaction();
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            localDAO.rollbackTransaction();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <E extends BasicEntity> void delete(E entity) {
        delete(entity, "ALL");
    }

    @Override
    public <E extends BasicEntity> void delete(E entity, String context) {

        validate(entity, ActionType.DELETE, context);

        BasicManagerDAO localDAO = getDAO();

        try {
            localDAO.beginTransaction();
            localDAO.delete(entity);
            localDAO.commitTransaction();
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            localDAO.rollbackTransaction();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <E extends BasicEntity> void save(List<E> entities) {
        save(entities, "ALL");
    }

    @Override
    public <E extends BasicEntity> void save(List<E> entities, String context) {

        entities.stream().forEach((E e) -> {
            validate(e, ActionType.SAVE, context);

            e.setLastUpdate(new Date());
            e.setCreationDate(new Date());
            
        });

        BasicManagerDAO localDAO = getDAO();

        try {
            localDAO.beginTransaction();
            localDAO.save(entities);
            localDAO.commitTransaction();
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            localDAO.rollbackTransaction();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <E extends BasicEntity> void update(List<E> entities) {
        update(entities, "ALL");
    }

    @Override
    public <E extends BasicEntity> void update(List<E> entities, String context) {

        entities.stream().forEach((E e) -> {
            validate(e, ActionType.UPDATE, context);
            e.setLastUpdate(new Date());
        });

        BasicManagerDAO localDAO = getDAO();

        try {
            localDAO.beginTransaction();
            localDAO.update(entities);
            localDAO.commitTransaction();
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            localDAO.rollbackTransaction();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <E extends BasicEntity> void delete(List<E> entities) {
        delete(entities, "ALL");
    }

    @Override
    public <E extends BasicEntity> void delete(List<E> entities, String context) {

        entities.stream().forEach((E e) -> {
            validate(e, ActionType.DELETE, context);
        });

        BasicManagerDAO localDAO = getDAO();

        try {
            localDAO.beginTransaction();
            localDAO.delete(entities);
            localDAO.commitTransaction();
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            localDAO.rollbackTransaction();
            throw new RuntimeException(ex);
        }
    }

    private <E extends BasicEntity> void validate(E entity, ActionType type, String context) {

        checkInitialization();
        final Class<BasicEntity> targetClass = (Class<BasicEntity>) entity.getClass();
        final List<EntityValidator> validators
                = Validators.getValidators(targetClass, context);

        if (type.equals(ActionType.SAVE)) {
            for (EntityValidator validator : validators) {
                validator.validate(entity, null, type);
            }
        } else {
            final E oldEntity
                    = (E) provider.getEntityManager().find(entity.getClass(), entity.getId());
            for (EntityValidator validator : validators) {
                validator.validate(entity, oldEntity, type);
            }
        }
    }

    @Override
    public BasicManagerDAO getDAO() {

        checkInitialization();

        dao.setEntityManager(provider.getEntityManager());

        return dao;
    }
}
