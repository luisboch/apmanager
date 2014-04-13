/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.service.base.test;

import com.apmanager.domain.base.BasicDAO;

/**
 *
 * @author luis
 * @param <D>
 */
public class BasicServiceTest <D extends BasicDAO>{
    protected D dao;
    public BasicServiceTest() {
    }
}
