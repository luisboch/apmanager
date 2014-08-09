/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.domain.test;

import com.apmanager.domain.base.test.BasicEntityTest;
import com.apmanager.domain.entity.Product;
import com.apmanager.domain.entity.dao.ProductDAO;
import com.apmanager.domain.entity.dao.filter.ProductFilter;
import org.junit.Test;

/**
 *
 * @author luis
 */
public class ProductTest extends BasicEntityTest<Product> {

    private final ProductDAO dao = new ProductDAO();

    public ProductTest() {
        super(Product.class);
        dao.setEntityManager(getEntityManager());
    }

    @Override
    public Product createNew() {
        Product p = new Product();
        p.setBarcode("00001");
        return p;
    }

    @Test
    public void searchTest() {
        final ProductFilter filter = new ProductFilter();
        filter.setActive(true);
        
        filter.setOrder(new String[]{"name desc", "barcode"});
        filter.setSearch("p a");

        dao.search(filter);
    }

}
