package com.apmanager.ui.crud.provider;

import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.annotation.AppListener;
import com.apmanager.ui.base.annotation.Open;
import java.util.logging.Logger;

/**
 * @author luis
 */
@AppListener
public class ApplicationListener {

    private static final Logger log = Logger.getLogger(ApplicationListener.class.getName());

    @Open
    public void initialize() throws Exception {
        Platform.setApplicationName("com.apmanager");
    }
}
