    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apmanager.ui.crud.provider;

import com.apmanager.domain.entity.Computer;
import com.apmanager.service.ComputerSearchService;
import com.apmanager.service.base.BasicEntityService;
import com.apmanager.service.base.BasicEntityServiceImpl;
import com.apmanager.service.base.Services;
import com.apmanager.ui.base.annotation.AppListener;
import com.apmanager.ui.base.annotation.Open;
import com.apmanager.ui.base.utils.FileUtils;
import com.apmanager.ui.data.AppData;
import java.io.File;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
@AppListener
public class ApplicationListener {

    private static final Logger log = Logger.getLogger(ApplicationListener.class.getName());

    @Open
    public void loadComputer() throws Exception {
        Integer cpId = null;
        try {
            Properties p = FileUtils.loadProperties(System.getProperty("user.home") + System.getProperty("file.separator") + ".apmanager.properties");
            if (p.getProperty("computer.id") != null) {
                cpId = Integer.valueOf(p.getProperty("computer.id"));
            }
        } catch (IOException ex) {
            // Do Nothing;
        }

        if (cpId != null) {
            final ComputerSearchService service = Services.getService(ComputerSearchService.class);
            final Computer computer = service.getByID(Computer.class, cpId);
            AppData.setComputer(computer);
        } else {
            try {
                Computer computer = new Computer();
                InetAddress ipv4 = InetAddress.getLocalHost();
                InetAddress ipv6 = Inet6Address.getLocalHost();

                computer.setIpv4(ipv4.getHostAddress());
                computer.setName(ipv4.getHostName());
                computer.setIpv6(ipv6.getHostAddress());
                final BasicEntityService service = Services.getEntityService();
                service.save(computer);
                AppData.setComputer(computer);
            } catch (UnknownHostException ex) {
                log.severe(ex.getMessage());
            }
            
            String aux = "computer.id = " + AppData.getComputer().getId();
            FileUtils.writeToFile(new File(System.getProperty("user.home")
                    + System.getProperty("file.separator") + ".apmanager.properties"), aux);
        };
        
        log.info("System started...");

    }
}