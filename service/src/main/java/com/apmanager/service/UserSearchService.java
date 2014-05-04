
package com.apmanager.service;

import com.apmanager.domain.entity.dao.UserDAO;
import com.apmanager.service.base.BasicSearchServiceImpl;

/**
 *
 * @author luis
 */
public class UserSearchService extends BasicSearchServiceImpl {

    public UserSearchService() {
        super(new UserDAO());
    }
}
