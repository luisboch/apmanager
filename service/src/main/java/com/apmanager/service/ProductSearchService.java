package com.apmanager.service;

import com.apmanager.domain.entity.dao.ProductDAO;
import com.apmanager.service.base.BasicSearchServiceImpl;

/**
 *
 * @author Luis
 */
public class ProductSearchService extends BasicSearchServiceImpl {

    public ProductSearchService() {
        super(new ProductDAO());
    }

}
