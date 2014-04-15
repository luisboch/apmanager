package com.apmanager.service;

import com.apmanager.domain.entity.User;
import com.apmanager.service.base.BasicEntityService;
import com.apmanager.service.base.BasicEntityServiceImpl;
import com.apmanager.service.base.Services;
import com.apmanager.service.base.ValidationException;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author luis
 */
public class UserServiceTest{
    private static final Logger log = Logger.getLogger(UserServiceTest.class.getName());
    
    @Test
    public void test(){
        
        BasicEntityService service = Services.getService(BasicEntityServiceImpl.class);
        
        User user = new User();
        
        try{
            service.save(user);
        } catch (ValidationException ex){
            // Expected
        }
        
        user.setName("User");
        user.setPasswd("blah");
        user.setUsername("Username");
        
        service.save(user);
        
    }
}
