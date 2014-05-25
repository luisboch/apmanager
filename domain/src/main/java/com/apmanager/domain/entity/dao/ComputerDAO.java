/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.domain.entity.dao;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.BasicSearchDAOImpl;
import com.apmanager.domain.entity.Computer;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author luis
 */
public class ComputerDAO extends BasicSearchDAOImpl{

    public ComputerDAO() {
    }
    
    
    public List<Computer> search(String search) {
        search = search == null ? "" : search;
        String jpql = "select c from Computer c where c.status = true "
                + "and ( lower(c.name) like lower(:search) "
                + "or c.ipv4 like :search or c.ipv6 like :search)  ";
        Query q = em.createQuery(jpql);
        q.setParameter("search", "%".concat(search).concat("%"));
        return q.getResultList();
    }
    
    
    @Override
    public <T extends BasicEntity> List<T> genericSearch(Class<T> clazz, String search) {
        
        if(clazz.equals(Computer.class)){
            return (List<T>) search(search);
        }
        
        return super.genericSearch(clazz, search);
    }
}
