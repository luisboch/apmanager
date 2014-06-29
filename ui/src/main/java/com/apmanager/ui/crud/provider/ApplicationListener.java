    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.ui.crud.provider;

import com.apmanager.domain.base.Computer;
import com.apmanager.service.base.ComputerSearchService;
import com.apmanager.service.base.BasicEntityService;
import com.apmanager.service.base.Services;
import com.apmanager.ui.base.annotation.AppListener;
import com.apmanager.ui.base.annotation.Open;
import com.apmanager.ui.base.utils.FileUtils;
import com.apmanager.ui.base.data.AppData;
import java.io.File;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author luis
 */
@AppListener
public class ApplicationListener {

    private static final Logger log = Logger.getLogger(ApplicationListener.class.getName());

    @Open
    public void loadComputer() throws Exception {

    }
}
