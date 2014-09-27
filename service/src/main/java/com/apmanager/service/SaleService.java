/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.service;

import com.apmanager.domain.base.Computer;
import com.apmanager.domain.entity.Sale;
import com.apmanager.domain.entity.dao.SaleDAO;
import com.apmanager.service.base.BasicSearchServiceImpl;
import javax.persistence.NoResultException;

/**
 *
 * @author luis
 */
public class SaleService extends BasicSearchServiceImpl {
    
    public SaleService() {
        super(new SaleDAO());
    }
    
    
    public Sale getActiveSale(Computer c){
        try{
            return getSaleDAO().getActiveSale(c);
        } catch (NoResultException e){
            return null;
        }
    }
    
    private SaleDAO getSaleDAO(){
        return (SaleDAO) getDAO();
    }
}
