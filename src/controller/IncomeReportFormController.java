package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class IncomeReportFormController {
    public AnchorPane incomeReportContext;
    public TableView tblOrder;
    public TableColumn colOrderId;
    public TableColumn colCustomerId;
    public TableColumn colOrderDate;
    public TableColumn colOrderTime;
    public TableColumn colCost;
    public TableView tblOrder1;
    public TableColumn colOrderId1;
    public TableColumn colCustomerId1;
    public TableColumn colOrderDate1;
    public TableColumn colOrderTime1;
    public TableColumn colCost1;
    public TableView tblOrder11;
    public TableColumn colOrderId11;
    public TableColumn colCustomerId11;
    public TableColumn colOrderDate11;
    public TableColumn colOrderTime11;
    public TableColumn colCost11;

    public void goBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ManagementDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) incomeReportContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
