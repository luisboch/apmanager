package com.apmanager.ui.base.crud;

import com.apmanager.domain.base.BasicEntity;
import com.apmanager.domain.base.SearchFilter;
import com.apmanager.service.base.BasicEntityService;
import com.apmanager.service.base.BasicSearchService;
import com.apmanager.service.base.BasicSearchServiceImpl;
import com.apmanager.service.base.Services;
import com.apmanager.service.base.exception.ValidationError;
import com.apmanager.service.base.exception.ValidationException;
import com.apmanager.ui.base.Platform;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author luis
 * @param <E>
 * @param <S>
 *
 */
public abstract class CrudBase<E extends BasicEntity, S extends BasicSearchService>
        extends AnchorPane implements Crud<E> {

    private static final Logger log = Logger.getLogger(CrudBase.class.getName());

    @FXML
    protected TextField txtSearch;

    @FXML
    protected Button btnSearch;

    @FXML
    protected ToggleButton btnShowAdvancedSearch;

    @FXML
    protected Button btnEdit;

    @FXML
    protected Button btnNew;

    @FXML
    protected TableView<E> tbResult;

    @FXML
    protected AnchorPane advancedSearchPane;

    @FXML
    protected AnchorPane resultsPane;

    @FXML
    protected AnchorPane editPane;

    private final Class<E> clazz;

    protected E instance;

    protected boolean newInstance;

    private AnchorPane customSearchPane;
    private CrudEdit<E> customEditPane;

    private final BasicSearchService searchService;
    private BasicEntityService entityService;
    private final S customSearchService;

    @SuppressWarnings("OverridableMethodCallDuringObjectConstruction")
    public CrudBase(Class<E> clazz, Class<S> searchService) {

        this.clazz = clazz;

        this.entityService = Services.getEntityService();
        this.searchService = Services.getService(BasicSearchServiceImpl.class);

        if (searchService != null) {
            this.customSearchService = Services.getService(searchService);
        } else {
            this.customSearchService = null;
        }

        URL fxmlUrl = getClass().getResource("/fxml/crud/CrudBase.fxml");

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
        customEditPane = getCustomEditPane();
        AnchorPane.setBottomAnchor(customEditPane, 38d);
        AnchorPane.setLeftAnchor(customEditPane, 0d);
        AnchorPane.setTopAnchor(customEditPane, 0d);
        AnchorPane.setRightAnchor(customEditPane, 0d);

        editPane.getChildren().add(customEditPane);

        customSearchPane = getCustomSearchPane();

        if (customSearchPane != null) {
            AnchorPane.setBottomAnchor(customSearchPane, 0d);
            AnchorPane.setLeftAnchor(customSearchPane, 0d);
            AnchorPane.setTopAnchor(customSearchPane, 0d);
            AnchorPane.setRightAnchor(customSearchPane, 0d);
            advancedSearchPane.getChildren().add(customSearchPane);
        }

        if (customEditPane == null) {
            throw new IllegalStateException("#getCustomEditPane must return an instance of AnchorPane (null not allowed)");
        }

        if (customSearchPane == null) {
            btnShowAdvancedSearch.setVisible(false);
        }
    }

    @Override
    public void addResultColumn(TableColumn column) {
        tbResult.getColumns().add(column);
    }

    public BasicSearchService getSearchService() {
        return searchService;
    }

    protected abstract CrudEdit<E> getCustomEditPane();

    protected abstract AnchorPane getCustomSearchPane();

    @Override
    public void setMode(CrudMode mode) {
        switch (mode) {
            case FULL_MODE:

                break;
            case EDIT_MODE:

                btnEdit.setDisable(false);
                btnNew.setDisable(false);
                btnSearch.setDisable(true);
                btnShowAdvancedSearch.setDisable(true);

                tbResult.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                break;

            case SINGLE_SELECTION_MODE:
                btnEdit.setDisable(true);
                btnNew.setDisable(true);
                btnSearch.setDisable(false);
                btnShowAdvancedSearch.setDisable(false);
                tbResult.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                break;
            case MULTIPLE_SELECTION_MODE:
                btnEdit.setDisable(true);
                btnNew.setDisable(true);
                btnSearch.setDisable(false);
                btnShowAdvancedSearch.setDisable(false);
                tbResult.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                break;
        }
    }

    public List<E> getSelecteds() {
        return tbResult.getSelectionModel().getSelectedItems();
    }

    public E getSelected() {
        return tbResult.getSelectionModel().getSelectedItem();
    }

    @Override
    public void setState(CrudState state) {
        if (state == null) {
            advancedSearchPane.setVisible(false);
            resultsPane.setVisible(false);
            editPane.setVisible(false);
            btnShowAdvancedSearch.setSelected(false);
        } else if (state == CrudState.EDIT) {
            advancedSearchPane.setVisible(false);
            resultsPane.setVisible(false);
            editPane.setVisible(true);
            btnShowAdvancedSearch.setSelected(false);
        } else if (state.equals(CrudState.RESULT)) {
            advancedSearchPane.setVisible(false);
            resultsPane.setVisible(true);
            editPane.setVisible(false);
            btnShowAdvancedSearch.setSelected(false);
        } else if (state.equals(CrudState.SEARCH)) {
            advancedSearchPane.setVisible(true);
            resultsPane.setVisible(false);
            editPane.setVisible(false);
            btnShowAdvancedSearch.setSelected(true);
        }
    }

    @FXML
    public void simpleSearch(ActionEvent e) {
        setState(CrudState.RESULT);
        final SearchFilter<E> filter = getSearchFilter();
        filter.getParams().clear();
        filter.getParams().put("search", txtSearch.getText());
        tbResult.getItems().addAll(searchService.find(clazz, filter));
    }

    @FXML
    public void cancelEdit(ActionEvent e) {
        setState(CrudState.SEARCH);
    }

    @FXML
    public void save(ActionEvent ev) {
        try {
            if (customEditPane.isValid(instance, newInstance)) {
                instance = customEditPane.buildObject(instance, newInstance);
                entityService.save(instance);
                setState(CrudState.SEARCH);
            }
        } catch (ValidationException ex) {
            for (ValidationError er : ex.getErrors()) {
                Platform.showMessage(er.getError());
            }
        } catch (Exception ex) {
            Platform.showMessage("Ops, encontramos um erro, contate suporte!");
            log.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @FXML
    public void showAdvancedSearch(ActionEvent e) {
        if (btnShowAdvancedSearch.isSelected()) {
            setState(CrudState.SEARCH);
        } else {
            setState(null);
        }
    }

    @FXML
    public void newInstance(ActionEvent e) {
        try {
            newInstance = true;
            instance = clazz.newInstance();
            setState(CrudState.EDIT);
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public abstract List<E> advancedSearch();

    protected abstract SearchFilter<E> getSearchFilter();

}
