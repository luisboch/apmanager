package com.apmanager.service;

import com.apmanager.domain.entity.dao.ProductBrandDAO;
import com.apmanager.service.base.BasicSearchServiceImpl;

/**
 *
 * @author Luis
 */
public class ProductBrandSearchService extends BasicSearchServiceImpl {

    public ProductBrandSearchService() {
        super(new ProductBrandDAO());
    }

}
