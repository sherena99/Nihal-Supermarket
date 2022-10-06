package controller;

import bo.BoFactory;
import bo.custom.ItemBO;
import dao.custom.impl.ItemDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entity.Item;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class ItemRemoveFormController {
    public TextField txtItemCode;
    public TextField txtPackSize;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public AnchorPane itemRemoveFormContext;
    public TextField txtDiscount;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    public void deleteItemButtonOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        if (itemBO.deleteItem(txtItemCode.getText())){
            txtItemCode.clear(); txtDescription.clear(); txtPackSize.clear(); txtQtyOnHand.clear();txtDiscount.clear(); txtUnitPrice.clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemCode = txtItemCode.getText();

        Item i1 = itemBO.searchItem(itemCode);
        if (i1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(i1);
        }
    }

    void setData(Item i){
        txtItemCode.setText(i.getCode());
        txtDescription.setText(i.getDescription());
        txtPackSize.setText(i.getPackSize());
        txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
        txtDiscount.setText(String.valueOf(i.getDiscount()));
        txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
    }


    public void cancelFormButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ManagementDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) itemRemoveFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}