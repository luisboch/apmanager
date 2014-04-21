/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.domain.test;

import com.apmanager.domain.base.test.BasicEntityTest;
import com.apmanager.domain.entity.User;
import com.apmanager.domain.entity.dao.UserDAO;
import java.util.Date;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author luis
 */
public class UserTest extends BasicEntityTest<User> {

    private static final Logger log
            = Logger.getLogger(UserTest.class.getName());

    public UserTest() {
        super(User.class);
    }

    @Override
    public User createNew() {
        instance = new User();
        instance.setName("Test #");
        instance.setPasswd("teste-1");
        instance.setUsername("teste-1");
        instance.setCreationDate(new Date());
        instance.setLastUpdate(new Date());
        
        managerDAO.beginTransaction();
        managerDAO.save(instance);
        managerDAO.commitTransaction();
        
        return instance;
    }

    @Test
    public void test() {
        log.info("Starting  UserTest");
        createNew();
    }
}
