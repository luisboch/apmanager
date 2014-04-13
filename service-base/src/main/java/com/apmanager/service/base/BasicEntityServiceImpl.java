/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.BasicDAO;
import com.apmanager.domain.base.BasicDAOImpl;
import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.SearchFilter;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 * @param <E>
 * @param <S>
 */
public class BasicEntityServiceImpl<E extends BasicEntity, S extends SearchFilter<E>>
        extends BasicServiceImpl<E, S>
        implements EntityService<E, S> {

    private static final Logger log = Logger.getLogger(BasicEntityServiceImpl.class.getName());

    public BasicEntityServiceImpl() {
        super(BasicDAOImpl.class);
    }

    /**
     * @param provider
     */
    void setProvider(EntityManagerProvider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "BasicServiceImpl{" + '}';
    }

    @Override
    public void save(E entity) {

        checkInitialization();
        validate(entity, ActionType.SAVE);

        BasicDAO localDAO = getDao();

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
    public void update(E entity) {

        checkInitialization();

        validate(entity, ActionType.UPDATE);
        
        BasicDAO localDAO = getDao();

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
    public void delete(E entity) {

        checkInitialization();

        validate(entity, ActionType.DELETE);
        
        BasicDAO localDAO = getDao();

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
    public void save(List<E> entities) {

        checkInitialization();
        
        entities.stream().forEach((E e) -> {
            validate(e, ActionType.SAVE);
        });
        
        BasicDAO localDAO = getDao();

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
    public void update(List<E> entities) {

        checkInitialization();

        entities.stream().forEach((E e) -> {
            validate(e, ActionType.UPDATE);
        });
        
        BasicDAO localDAO = getDao();

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
    public void delete(List<E> entities) {

        checkInitialization();
        
        entities.stream().forEach((E e) -> {
            validate(e, ActionType.DELETE);
        });
        
        BasicDAO localDAO = getDao();

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

    private void validate(E entity, ActionType type) {

        final EntityValidator<E> validator
                = Validators.getValidator(entity.getClass());

        final E oldEntity
                = (E) provider.getEntityManager().find(entity.getClass(), entity);

        validator.validate(entity, oldEntity, type);
    }
}
