package com.apmanager.ui.sale;

import com.apmanager.domain.entity.Sale;
import com.apmanager.domain.entity.SaleProduct;
import com.apmanager.service.SaleService;
import com.apmanager.service.base.BasicEntityService;
import com.apmanager.service.base.Services;
import com.apmanager.ui.base.Platform;
import com.apmanager.ui.base.annotation.Open;
import com.apmanager.ui.base.annotation.Window;
import com.apmanager.ui.base.data.AppData;
import com.apmanager.ui.base.handler.BasicHandler;
import com.apmanager.ui.base.pane.BasicAnchorPane;
import com.apmanager.ui.base.resource.i18n.I18N;
import com.apmanager.utils.NumberUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * @author Luis
 */
@Window(isDefault = true, menu = {"activities.menu", "sale.menu"}, priority = 999)
public class SalePane extends BasicAnchorPane {

    private static final Logger log = Logger.getLogger(SalePane.class.getName());

    @FXML
    private TableColumn tbColumnNumber, tbColumnProductName, tbColumnUnityPrice, tbColumnQtde;

    @FXML
    private Button btnCancel, btnFinish, btnAdd;

    @FXML
    private Label lblCode;

    @FXML
    private TableView<SaleProduct> results;

    private Sale sale;

    private SaleService saleService;

    private BasicEntityService service;

    public SalePane() {
        super("/fxml/sale/SalePane.fxml");
        setupTable();
    }

    @Open
    public void open() {
        log.info("Opening SalePane");
        saleService = Services.getService(SaleService.class);
        service = Services.getEntityService();
        
        sale = saleService.getActiveSale(AppData.getComputer());

        if (sale == null) {
            sale = new Sale();
            sale.setComputer(AppData.getComputer());

            service.save(sale);
        }

        load(sale);

    }

    @FXML
    public void showSearchPane(ActionEvent evt) {
        final Platform p = Platform.getInstance();
        final ProductSearchPane search = new ProductSearchPane();

        p.showDialog(search, I18N.Label.get("search"),
                new BasicHandler<Object>() {
                    @Override
                    public void handle(Object obj) {
                        log.info("Search finished");
                        if (search.getSelected() != null) {
                            final ProductAddPane productAddPane = new ProductAddPane(sale, search.getSelected());

                            p.showDialog(productAddPane, I18N.Label.get("add"),
                                    new BasicHandler() {
                                        @Override
                                        public void handle(Object obj) {
                                            log.log(Level.INFO, "Is adding product? {0}", productAddPane.getProduct() != null);
                                            sale.getProducts().add(productAddPane.getProduct());
                                            service.update(sale);
                                            load(sale);
                                        }
                                    });
                        }
                    }
                });
    }

    private void load(Sale sale) {
        updateTb(sale.getProducts());
        lblCode.setText(sale.getId().toString());
    }

    private void updateTb(List<SaleProduct> products) {
        results.getItems().clear();
        results.getItems().addAll(products);
    }

    private void setupTable() {
        final TableColumn<SaleProduct, Integer> clNum
                = (TableColumn<SaleProduct, Integer>) results.getColumns().get(0);
        final TableColumn<SaleProduct, String> clName
                = (TableColumn<SaleProduct, String>) results.getColumns().get(1);
        final TableColumn<SaleProduct, String> clUnitaryPrice
                = (TableColumn<SaleProduct, String>) results.getColumns().get(2);
        final TableColumn<SaleProduct, Integer> clQtde
                = (TableColumn<SaleProduct, Integer>) results.getColumns().get(3);
        
        clNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SaleProduct, Integer>, ObservableValue<Integer>>() {

            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<SaleProduct, Integer> param) {
                return new SimpleObjectProperty<>(sale.getProducts().indexOf(param.getValue()));
            }
        });
        
        clName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SaleProduct, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SaleProduct, String> param) {
                return new SimpleStringProperty(param.getValue().getProduct().getName());
            }
        });
        
        clUnitaryPrice.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SaleProduct, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SaleProduct, String> param) {
                return new SimpleStringProperty(
                        I18N.Config.get("monetary.symbol") + " "
                        + NumberUtils.format(param.getValue().getPurchuasePrice(),
                                I18N.Config.get("monetary.pattern")));
            }
        });
        
        clQtde.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SaleProduct, Integer>, ObservableValue<Integer>>() {

            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<SaleProduct, Integer> param) {
                return new SimpleObjectProperty<>(param.getValue().getQuantity());
            }
        });
        
    }

}
