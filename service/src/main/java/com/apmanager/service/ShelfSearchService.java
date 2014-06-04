package com.apmanager.service;

import com.apmanager.domain.base.BasicSearchDAOImpl;
import com.apmanager.domain.entity.dao.ShelfDAO;
import com.apmanager.service.base.BasicSearchServiceImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author felipe
 */
public class ShelfSearchService extends BasicSearchServiceImpl{

    public ShelfSearchService() {
        super(new ShelfDAO());
    }
    
    
}
