package com.apmanager.ui.base;

import com.apmanager.ui.base.annotation.Window;
import com.apmanager.ui.base.menu.helper.MenuWrapper;
import com.apmanager.ui.base.resource.i18n.I18N;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 *
 * @author luis
 */
public class AppManager {

    private static Platform platform;

    private static final Logger log
            = Logger.getLogger(AppManager.class.getName());

    private static final Map<Window, Class<?>> windows
            = new HashMap<>();

    private static boolean _initialized = false;

    private static MenuWrapper defaultMenu;

    public static void initialize() {
        if (!_initialized) {
            Reflections reflections = new Reflections(
                    new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forJavaClassPath())
            );

            Set<Class<?>> types
                    = reflections.getTypesAnnotatedWith(Window.class);

            try {
                for (Class<?> clazz : types) {
                    windows.put(clazz.getAnnotation(Window.class), clazz);
                }
            } catch (Exception ex) {
                log.log(Level.SEVERE, ex.getMessage(), ex);
                throw new RuntimeException(ex);
            }
            _initialized = true;
        }
    }

    public static void setMenu(MenuBar menuBar) {

        initialize();

        final List<MenuWrapper> rootMenus = new ArrayList<>();

        final Map<String, MenuWrapper> allMenus = new HashMap<>();

        for (Map.Entry<Window, Class<?>> entry : windows.entrySet()) {

            final Window w = entry.getKey();

            final Class<?> clazz = entry.getValue();

            String[] tree = w.menu();

            if (tree.length == 1) {
                final String menu = tree[0];
                tree = new String[]{"options.menu", menu};
            }

            for (int i = 0; i < tree.length; i++) {

                final String key = tree[i];

                final boolean root = i == 0;

                MenuWrapper wrapper;

                if (allMenus.get(key) == null) {
                    wrapper = new MenuWrapper();
                    wrapper.setName(I18N.Menu.get(key));
                    wrapper.setPriority(w.priority());
                    wrapper.setDefault(w.isDefault());
                    allMenus.put(key, wrapper);
                } else {
                    wrapper = allMenus.get(key);
                }

                if (root) {
                    if (!rootMenus.contains(wrapper)) {
                        rootMenus.add(wrapper);
                    }
                } else {
                    final String parent = tree[i - 1];

                    if (!allMenus.get(parent).getChildren().contains(wrapper)) {
                        allMenus.get(parent).getChildren().add(wrapper);
                    }
                    wrapper.setParent(allMenus.get(parent));
                }

                if (i + 1 == tree.length) {
                    wrapper.setTargetClass(clazz);
                }

            }
        }

        Collections.sort(rootMenus, new Comparator<MenuWrapper>() {

            @Override
            public int compare(MenuWrapper o1, MenuWrapper o2) {
                if (o1 != null && o2 != null) {
                    if (o1.getPriority() != null && o2.getPriority() != null) {
                        return o2.getPriority().compareTo(o1.getPriority());
                    }
                }
                return 0;
            }
        });

        for (MenuWrapper w : rootMenus) {
            final Menu m = (Menu) buildMenu(w, null);
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    menuBar.getMenus().add(m);
                }
            });
        }

    }

    public static void setPlatform(Platform platform) {
        AppManager.platform = platform;
    }

    private static MenuItem buildMenu(MenuWrapper wrapper, MenuWrapper parent) {

        MenuItem menu;

        if (!wrapper.getChildren().isEmpty() || parent == null) {
            menu = new Menu(wrapper.getName());
            wrapper.setNode(menu);
            menu.setUserData(wrapper);

            Collections.sort(wrapper.getChildren(), new Comparator<MenuWrapper>() {
                @Override
                public int compare(MenuWrapper o1, MenuWrapper o2) {

                    if (o1 != null && o2 != null) {
                        if (o1.getName() != null && o2.getName() != null) {
                            return o2.getName().compareTo(o1.getName());
                        }
                    }
                    return 0;
                }
            });

            Collections.sort(wrapper.getChildren(), new Comparator<MenuWrapper>() {
                @Override
                public int compare(MenuWrapper o1, MenuWrapper o2) {

                    if (o1 != null && o2 != null) {
                        if (o1.getPriority() != null && o2.getPriority() != null) {
                            return o2.getPriority().compareTo(o1.getPriority());
                        }
                    }
                    return 0;
                }
            });

            wrapper.getChildren().stream().forEach((m) -> {
                buildMenu(m, wrapper);
            });

            log.log(Level.INFO, "Creating Menu: {0}", wrapper.getName());

        } else {

            log.log(Level.INFO, "Creating MenuItem: {0}", wrapper.getName());
            menu = new MenuItem(wrapper.getName());
            menu.setUserData(wrapper);
            wrapper.setNode(menu);
        }

        if (parent != null) {
            Menu m = (Menu) parent.getNode();
            m.getItems().add(menu);
        }

        menu.setOnAction(new MenuHandler());

        if (wrapper.isDefault() && wrapper.getTargetClass() != null) {
            defaultMenu = wrapper;
        }

        return menu;
    }

    private static final class MenuHandler implements javafx.event.EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try {
                if (event.getSource() instanceof MenuItem) {
                    final MenuItem menu = (MenuItem) event.getSource();
                    MenuWrapper wrapper = (MenuWrapper) menu.getUserData();
                    selectMenu(wrapper);
                }
            } catch (Exception ex) {
                Platform.showInfo("Ops, encontramos um problema, contate suporte!");
                log.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

    }

    public static void initDefault() {
        javafx.application.Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try {
                    selectMenu(defaultMenu);
                } catch (Exception ex) {
                    log.log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private static void selectMenu(MenuWrapper wrapper) throws Exception {
        Class targetClass = wrapper.getTargetClass();

        if (targetClass != null) {

            Object object = targetClass.newInstance();

            if (object instanceof AnchorPane) {
                platform.set((AnchorPane) object, targetClass);
                platform.setTitle(wrapper.getName());
            } else {
                log.log(Level.SEVERE, "[AppManager] Can't initialize class "
                        + "{0} that must extend AnchorPane", targetClass.getSimpleName());
            }
        }
    }
}
