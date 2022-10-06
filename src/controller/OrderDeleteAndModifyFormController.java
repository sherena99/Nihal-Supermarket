package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class OrderDeleteAndModifyFormController {
    public AnchorPane deleteAndModifyContext;
    public TableView tblOrder;
    public TableColumn colOrderId;
    public TableColumn colCustomerId;
    public TableColumn colOrderDate;
    public TableColumn colOrderTime;
    public TableColumn colCost;
    public TableView tblOrderDetails;
    public TableColumn colItem;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public ComboBox cmbOrderId;

    public void deleteButtonOnAction(ActionEvent actionEvent) {
    }

    public void modifyButtonOnAction(ActionEvent actionEvent) {
    }

    public void goBackButtonOnAction(ActionEvent actionEvent) {
    }
}
