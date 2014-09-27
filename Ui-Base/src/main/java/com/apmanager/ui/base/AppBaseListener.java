package com.apmanager.ui.base;

import com.apmanager.domain.base.Computer;
import com.apmanager.service.base.BasicEntityService;
import com.apmanager.service.base.ComputerSearchService;
import com.apmanager.service.base.Services;
import com.apmanager.ui.base.data.AppData;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
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
        loadComputerParameter();
        loadLocale();
        log.info("System started...");
    }

    private static void loadComputerParameter() {
        try {
            Integer cpId = null;

            final Properties p = AppConfig.getProperties();
            if (p.getProperty("computer.id") != null) {
                cpId = Integer.valueOf(p.getProperty("computer.id"));
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

                AppConfig.set("computer.id", AppData.getComputer().getId().toString());

            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    private static void loadLocale() {
        String config = (String) AppConfig.getProperties().get("locale");

        if (config == null) {
            log.warning("Ignoring locale config, using system locale...");
        } else {
            Locale.setDefault(new Locale(config));
            log.log(Level.INFO, "Using locale: {0}", config);
            log.log(Level.INFO, "Using locale: {0}", Locale.getDefault());
        }
    }
}
