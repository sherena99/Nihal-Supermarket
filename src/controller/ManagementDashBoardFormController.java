package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManagementDashBoardFormController {
    public AnchorPane managementDashBoardContext;

    public void incomeButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/IncomeReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managementDashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void customerIncomeButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/CustomerWiseIncome.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managementDashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void goBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/FirstPageForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managementDashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void manageItemButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ItemDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managementDashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void createNewAccButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MSignInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managementDashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void itemReportButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ItemReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managementDashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
