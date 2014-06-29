package com.apmanager.ui.base;

import com.apmanager.domain.base.Computer;
import com.apmanager.service.base.BasicEntityService;
import com.apmanager.service.base.ComputerSearchService;
import com.apmanager.service.base.Services;
import com.apmanager.ui.base.data.AppData;
import com.apmanager.ui.base.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class AppBaseListener {

    private static final Logger log = Logger.getLogger(AppBaseListener.class.getName());

    public static void initialize() {
        try {
            Integer cpId = null;

            try {
                final Properties p = FileUtils.loadProperties(
                        System.getProperty("user.home")
                        + System.getProperty("file.separator")
                        + "." + Platform.getApplicationName() + ".properties");
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
                    final Computer computer = new Computer();
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
            }

            log.info("System started...");
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}
