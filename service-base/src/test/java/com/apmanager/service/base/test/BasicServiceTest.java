/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apmanager.service.base.test;

import com.apmanager.domain.base.BasicManagerDAO;

/**
 *
 * @author luis
 * @param <D>
 */
public class BasicServiceTest <D extends BasicManagerDAO>{
    protected D dao;
    public BasicServiceTest() {
    }
}
