package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import dto.CustomerDTO;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewCashier.tm.CustomerTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDetailsFormController {
    public AnchorPane customerDetailsFormContext;
    public AnchorPane loadCustomerDetailsContext;
    public TableView <CustomerTm> tblCustomerDetails;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;

    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));

        try {
            setCustomerDetailsToTable(customerBO.getAllCustomer());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setCustomerDetailsToTable(ArrayList<CustomerDTO> customer){
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        customer.forEach(e->{
            obList.add(
                    new CustomerTm(e.getId(),e.getTitle(),e.getName(),e.getAddress(),e.getCity(),e.getProvince()));
        });
        tblCustomerDetails.setItems(obList);

    }

    public void goBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewCashier/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) customerDetailsFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewCashier/AddNewCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadCustomerDetailsContext.getChildren().clear();
        loadCustomerDetailsContext.getChildren().add(load);
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewCashier/UpdateCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadCustomerDetailsContext.getChildren().clear();
        loadCustomerDetailsContext.getChildren().add(load);
    }

    public void removeCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewCashier/DeleteCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadCustomerDetailsContext.getChildren().clear();
        loadCustomerDetailsContext.getChildren().add(load);
    }

    public void allCustomerOnAction(ActionEvent actionEvent) {
    }
}
