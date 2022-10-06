package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FirstPageFormController {
    public AnchorPane firstPageContext;
    public AnchorPane logInContext;

    public void cashierLogInOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) firstPageContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void managementLogInOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagementLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) firstPageContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
