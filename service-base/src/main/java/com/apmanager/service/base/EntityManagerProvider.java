/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.service.base;

import javax.persistence.EntityManager;

/**
 *
 * @author luis
 */
class EntityManagerProvider {
    
    public EntityManager getEntityManager(){
        return Services.getEntityManager();
    }
}
