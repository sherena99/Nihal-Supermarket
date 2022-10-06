package controller;

import bo.BoFactory;
import bo.custom.PurchaseOrderBO;
import bo.custom.impl.PurchaseOrderBOImpl;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.OrderDAOImpl;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.ItemDetailsDTO;
import dto.OrderDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import entity.*;
import entity.ItemDetails;
import viewCashier.tm.CartTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaceOrderFormController {
    public AnchorPane placeOrderFormContext;
    public ComboBox <String> cmbCustomerIds;
    public ComboBox <String> cmbItemIds;
    public Label lblOrderId;
    public Label lblDate;
    public Label lblTime;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public TableView <CartTm> tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQTY;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Label txtTtl;
    public TextField txtTitle;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtPackSize;
    public TableColumn colPackSize;
    public Label lblDiscount;
    public Label lblTotal;
    public Label lblGrossTotal;
    public TableColumn colDiscountPercentage;
    public TableColumn colGrossTotal;
    public TableColumn colDiscount;
    public TextField txtDis;
    public Label lblTotalDiscount;

    int cartSelectedRowForRemove = -1;

    private final PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PURCHASE_ORDER);
    public void initialize(){

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qtyForCustomer"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscountPercentage.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        colGrossTotal.setCellValueFactory(new PropertyValueFactory<>("grossTotal"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));



        loadDateAndTime();
        setOrderId();

        try {
            loadCustomerIds();
            loadItemCodes();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /////////////////////////////////////
        cmbCustomerIds.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData(newValue);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                });

        cmbItemIds.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setItemData(newValue);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                });

        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

    }

    private void setOrderId() {
        try {
            lblOrderId.setText(purchaseOrderBO.generateNewOrderId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        ItemDTO i1 = purchaseOrderBO.searchItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPackSize());
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtDis.setText(String.valueOf(i1.getDiscount()));
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
        }
    }

    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        CustomerDTO c1=purchaseOrderBO.searchCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtTitle.setText(c1.getTitle());
            txtName.setText(c1.getName());
            txtAddress.setText(c1.getAddress());
            txtCity.setText(c1.getCity());
            txtProvince.setText(c1.getProvince());
        }
    }


    private void loadItemCodes() throws SQLException, ClassNotFoundException {
     /*   ArrayList<ItemDTO> itemIds = purchaseOrderBO.getAllItems();
        cmbItemIds.getItems().addAll(String.valueOf(itemIds));*/

        try {
            ArrayList<ItemDTO> all = purchaseOrderBO.getAllItems();
            for (ItemDTO dto : all) {
                cmbItemIds.getItems().add(dto.getCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
       /* ArrayList<CustomerDTO> customerIds = purchaseOrderBO.getAllCustomers();
        cmbCustomerIds.getItems().addAll(String.valueOf(customerIds));*/

        try {
            ArrayList<CustomerDTO> all = purchaseOrderBO.getAllCustomers();
            for (CustomerDTO dto : all) {
                cmbCustomerIds.getItems().add(dto.getId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    ObservableList<CartTm> obList= FXCollections.observableArrayList();
    public void addToCartOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyForCustomer = Integer.parseInt(txtQty.getText());
        int discountPercentage=Integer.parseInt(txtDis.getText());
        double grossTotal = qtyForCustomer * unitPrice;
        double discount= grossTotal * discountPercentage/100;
        double total= grossTotal - discount;



        if (qtyOnHand < qtyForCustomer) {
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            return;
        }

        CartTm tm = new CartTm(
                cmbItemIds.getValue(),
                description,
                packSize,
                qtyForCustomer,
                unitPrice,
                discountPercentage,
                grossTotal,
                discount,
                total

        );

        int rowNumber = isExists(tm);

        if (rowNumber == -1) {// new Add
            obList.add(tm);
        } else {
            // update
            CartTm temp = obList.get(rowNumber);
            CartTm newTm = new CartTm(
                    temp.getCode(),
                    temp.getDescription(),
                    temp.getPackSize(),
                    temp.getQtyForCustomer()+qtyForCustomer,
                    temp.getUnitPrice(),
                    temp.getDiscountPercentage(),
                    temp.getGrossTotal()+grossTotal,
                    temp.getDiscount()+discount,
                    temp.getTotal()+total

            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblCart.setItems(obList);
        calculateTotalCosts();
        calculateTotalDiscount();


    }


    private int isExists(CartTm tm){
            for (int i = 0; i < obList.size(); i++) {
                if (tm.getCode().equals(obList.get(i).getCode())){
                    return i;
                }
            }
            return -1;
        }


    public void calculateTotalCosts(){ // calculate costs in one method

        double ttl=0;
        for (CartTm tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        lblTotal.setText(ttl+" /=");
    }

    void calculateTotalDiscount(){
        double discount=0;
        for (CartTm tm:obList
        ) {
            discount+=tm.getDiscount();
        }
        lblTotalDiscount.setText(discount+" %");
    }

    public void clearOnAction (ActionEvent actionEvent){
            if (cartSelectedRowForRemove==-1){
                new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
            }else{
                obList.remove(cartSelectedRowForRemove);
                calculateTotalCosts();
                calculateTotalDiscount();

                tblCart.refresh();
            }
        }


    private void loadDateAndTime () {
            // load Date
            Date date = new Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            lblDate.setText(f.format(date));

            // load Time
            Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                LocalTime currentTime = LocalTime.now();
                lblTime.setText(
                        currentTime.getHour() + " : " + currentTime.getMinute() +
                                " : " + currentTime.getSecond()
                );
            }),
                    new KeyFrame(Duration.seconds(1))
            );
            time.setCycleCount(Animation.INDEFINITE);
            time.play();
        }


    public void goBackButtonOnAction (ActionEvent actionEvent) throws IOException {
            URL resource = getClass().getResource("../viewCashier/CashierDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) placeOrderFormContext.getScene().getWindow();
            window.setScene(new Scene(load));
        }


    public void placeOrderOnAction (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<dto.ItemDetailsDTO> items = new ArrayList<>();
        double total = 0;
        for (CartTm tempTm : obList
        ) {
            total += tempTm.getTotal();
            items.add(new ItemDetailsDTO(tempTm.getCode(), tempTm.getQtyForCustomer(),
                    tempTm.getUnitPrice(), tempTm.getDiscount()));
        }

        txtTitle.clear();txtName.clear();txtAddress.clear();txtCity.clear();txtProvince.clear();

        txtDescription.clear();txtPackSize.clear();txtQtyOnHand.clear();txtUnitPrice.clear();txtQty.clear();
        txtDis.clear();



        OrderDTO order= new OrderDTO(
                lblOrderId.getText(),
                cmbCustomerIds.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                items
        );
        if (purchaseOrderBO.purchaseOrder(order)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
             setOrderId();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }


        OrderDetail orderDetails= new OrderDetail(

        );

        }
    }

