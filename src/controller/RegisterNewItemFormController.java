package controller;

import bo.BoFactory;
import bo.custom.ItemBO;
import dao.custom.impl.ItemDAOImpl;
import dto.ItemDTO;
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

public class RegisterNewItemFormController {
    public AnchorPane registerNewItemContext;
    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public TextField txtPackSize;
    public TextField txtDiscount;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    public void saveButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ItemDTO i1 = new ItemDTO(
                txtItemCode.getText(),txtDescription.getText(),txtPackSize.getText(),
                Integer.parseInt(txtQtyOnHand.getText()),Integer.parseInt(txtDiscount.getText()), Double.parseDouble(txtUnitPrice.getText())
        );
        txtItemCode.clear(); txtDescription.clear(); txtPackSize.clear(); txtQtyOnHand.clear();txtDiscount.clear(); txtUnitPrice.clear();
        if( itemBO.addItem(i1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ManagementDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) registerNewItemContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
