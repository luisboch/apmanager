/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.service.base;

import com.apmanager.domain.base.BasicDAO;

/**
 *
 * @author luis
 */
interface BasicService<E extends BasicDAO>{
    
    E getDAO();
    
    /**
     * @param provider
     */
    void setProvider(EntityManagerProvider provider);
}
