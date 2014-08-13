/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.domain.entity.dao;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicSearchDAOImpl;
import com.apmanager.domain.entity.Shelf;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author felipe
 */
public class ShelfDAO extends BasicSearchDAOImpl {

    public ShelfDAO() {
    }

    public List<Shelf> search(String search) {
        search = search == null ? "" : search;
        String jpql = "select c from Shelf c where c.active = true "
                + "and ( lower(c.code) like lower(:search) "
                + "or lower(c.description) like lower(:search))";
        Query q = em.createQuery(jpql);
        q.setParameter("search", "%".concat(search).concat("%"));
        return q.getResultList();
    }

    @Override
    public <T extends BasicEntity> List<T> genericSearch(Class<T> clazz, String search) {

        if (clazz.equals(Shelf.class)) {
            return (List<T>) search(search);
        }

        return super.genericSearch(clazz, search);
    }

}
