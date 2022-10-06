package controller;

import dao.custom.OrderDetailsDAO;
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.OrderDetailsDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entity.OrderDetail;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class OrderModifyFormController {
    public TextField txtOrderId;
    public TextField txtItemCode;
    public TextField txtOrderQty;
    public TextField txtOrderDiscount;
    public AnchorPane orderModifyFormContext;



    public void searchOrderButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orderId = txtOrderId.getText();

        OrderDetail o1 = new OrderDetailsDAOImpl().search(orderId);
        if (o1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(o1);
        }
        txtOrderId.requestFocus();


    }

    void setData(OrderDetail o1){
        txtOrderId.setText(o1.getOrderId());
        txtItemCode.setText(o1.getItemCode());
        txtOrderQty.setText(String.valueOf(o1.getOrderQty()));
        txtOrderDiscount.setText(String.valueOf(o1.getDiscount()));

    }



    public void modifyButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        OrderDetail o1= new OrderDetail(
                txtOrderId.getText(),txtItemCode.getText(),Integer.parseInt(txtOrderQty.getText()),
                Double.parseDouble(txtOrderDiscount.getText())

        );
        if (new OrderDetailsDAOImpl().update(o1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }


    }

    public void canceButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewCashier/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) orderModifyFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
