package com.apmanager.ui.base.crud;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.service.base.BasicEntityService;
import com.apmanager.service.base.Services;
import com.apmanager.service.base.exception.ValidationException;
import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.handler.BasicHandler;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * @author luis
 * @param <E>
 */
public class DialogEdit<E extends BasicEntity> extends AnchorPane {

    private static final Logger log = Logger.getLogger(DialogEdit.class.getName());

    @FXML
    private AnchorPane editorEditPane;

    private BasicEntityService service;

    private final CrudEdit<E> editor;

    private BasicHandler<E> handler;

    @SuppressWarnings("OverridableMethodCallDuringObjectConstruction")
    public DialogEdit(CrudEdit<E> editor) {
        this(editor, null);
    }

    public DialogEdit(CrudEdit<E> editor, BasicHandler<E> handler) {
        this.handler = handler;
        this.editor = editor;
        this.service = Services.getEntityService();

        URL fxmlUrl = getClass().getResource("/fxml/crud/DialogEdit.fxml");

        FXMLLoader loader = new FXMLLoader(fxmlUrl);

        loader.setResources(ResourceBundle.getBundle("i18n/crud_labels"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        loadPanes();
    }

    private void loadPanes() {

        AnchorPane.setBottomAnchor(editorEditPane, 38d);
        AnchorPane.setLeftAnchor(editorEditPane, 0d);
        AnchorPane.setTopAnchor(editorEditPane, 0d);
        AnchorPane.setRightAnchor(editorEditPane, 0d);

        editorEditPane.getChildren().add(editor);

    }

    @FXML
    public void cancelEdit(ActionEvent e) {
        if (handler != null) {
            handler.handle(null);
        }
    }

    @FXML
    public void save(ActionEvent ev) {

        try {

            E instance = editor.createNewInstance();

            log.log(Level.INFO, "Saving instance {0}", instance);

            if (editor.isValid(instance, true)) {

                instance = editor.buildObject(instance, true);
                service.save(instance);
                Platform.showInfo("Registro salvo com sucesso!");

                if (handler != null) {
                    handler.handle(instance);
                }
            }
        } catch (ValidationException ex) {
            ex.getErrors().stream().forEach((er) -> {
                Platform.showInfo(er.getError());
            });
        } catch (Exception ex) {
            
            Platform.showInfo("Ops, encontramos um erro, contate suporte!");
            
            log.log(Level.SEVERE, ex.getMessage(), ex);

        }
    }

    public BasicHandler<E> getHandler() {
        return handler;
    }

    public void setHandler(BasicHandler<E> handler) {
        this.handler = handler;
    }
}
