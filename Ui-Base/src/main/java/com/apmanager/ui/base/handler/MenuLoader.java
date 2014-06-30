
package com.apmanager.ui.base.handler;

import com.apmanager.ui.base.menu.helper.MenuWrapper;
import java.util.List;
import javafx.event.ActionEvent;

/**
 *
 * @author Luis
 */
public interface MenuLoader {
    public List<MenuWrapper> loadMenu(MenuWrapper selection, ActionEvent evt);
}
