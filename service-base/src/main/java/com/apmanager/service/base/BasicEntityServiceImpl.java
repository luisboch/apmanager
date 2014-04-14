/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service.base;

import com.apmanager.domain.base.BasicManagerDAO;
import com.apmanager.domain.base.BasicManagerDAOImpl;
import com.apmanager.domain.base.BasicEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class BasicEntityServiceImpl extends BasicServiceImpl<BasicManagerDAO> implements BasicEntityService {

    private BasicManagerDAO dao = new BasicManagerDAOImpl();

    private static final Logger log = Logger.getLogger(BasicEntityServiceImpl.class.getName());

    @Override
    public String toString() {
        return "BasicServiceImpl{" + '}';
    }

    @Override
    public <E extends BasicEntity> void save(E entity) {

        validate(entity, ActionType.SAVE);

        BasicManagerDAO localDAO = getDAO();

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

        validate(entity, ActionType.UPDATE);

        BasicManagerDAO localDAO = getDAO();

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

        validate(entity, ActionType.DELETE);

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

        entities.stream().forEach((E e) -> {
            validate(e, ActionType.SAVE);
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

        entities.stream().forEach((E e) -> {
            validate(e, ActionType.UPDATE);
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

        entities.stream().forEach((E e) -> {
            validate(e, ActionType.DELETE);
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

    private <E extends BasicEntity> void validate(E entity, ActionType type) {

        checkInitialization();

        final EntityValidator<E> validator
                = Validators.getValidator(entity.getClass());

        if (type.equals(ActionType.SAVE)) {
            validator.validate(entity, null, type);
        } else {
            final E oldEntity
                    = (E) provider.getEntityManager().find(entity.getClass(), entity);
            validator.validate(entity, oldEntity, type);
        }
    }

    @Override
    public BasicManagerDAO getDAO() {

        checkInitialization();

        dao.setEntityManager(provider.getEntityManager());

        return dao;
    }
}
