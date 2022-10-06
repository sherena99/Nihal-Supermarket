package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CashierDashBoardFormController {
    public AnchorPane cashierDashBordContext;
    

    public void goBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/FirstPageForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierDashBordContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }


    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewCashier/PlaceOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierDashBordContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void customerDetailsButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewCashier/CustomerDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierDashBordContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void createNewAccButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CSignInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierDashBordContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void manageOrdersButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewCashier/OrderModifyForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierDashBordContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
