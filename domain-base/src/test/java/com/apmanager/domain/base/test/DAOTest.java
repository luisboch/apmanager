/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.domain.base.test;

import com.apmanager.domain.base.BasicDAO;
import org.junit.Test;

/**
 *
 * @author luis
 */
public class DAOTest extends BaseTest{
    
    
    @Test
    public void test(){
        BasicDAO dao = getDAO(BasicDAOImplTest.class);
        
        dao.save(new Entity());
        dao.update(new Entity());
        dao.delete(new Entity());
    }
}
