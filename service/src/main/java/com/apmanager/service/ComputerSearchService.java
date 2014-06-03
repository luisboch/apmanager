/*
 * Computer.java
 */

package com.apmanager.service;

import com.apmanager.domain.entity.dao.ComputerDAO;
import com.apmanager.service.base.BasicSearchServiceImpl;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ComputerSearchService extends BasicSearchServiceImpl{

    public ComputerSearchService() {
        super(new ComputerDAO());
    }
    
}
