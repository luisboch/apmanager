/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.domain.base;

import javax.persistence.EntityManager;

/**
 *
 * @author luis
 */
public class BasicDAOImpl implements BasicDAO{
    
    protected EntityManager em;

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}