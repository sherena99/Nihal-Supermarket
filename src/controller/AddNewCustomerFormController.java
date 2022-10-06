package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import dao.custom.impl.CustomerDAOImpl;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entity.Customer;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddNewCustomerFormController {
    public AnchorPane saveCustomerFormContext;

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtTitle;

    LinkedHashMap<TextField, Pattern> map=new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(C-)[0-9]{3,4}$");
    Pattern titlePattern = Pattern.compile("^(Ms.|Mrs.|Mr.|Miss.|miss.|ms.|mrs.|mr.)$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern cityPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern provincePattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");


    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    public JFXButton btnSaveData;

    public void initialize(){

        btnSaveData.setDisable(true);
        storeValidations();

    }




    public void saveCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO c1 = new CustomerDTO(
                txtId.getText(),txtTitle.getText(),txtName.getText(),
                txtAddress.getText(), txtCity.getText(),txtProvince.getText()
        );

        txtId.clear();txtTitle.clear();txtName.clear();txtAddress.clear();txtCity.clear();txtProvince.clear();

        if(customerBO.addCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    public void goBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewCashier/CustomerDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) saveCustomerFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    private void storeValidations() {
        map.put(txtId, idPattern);
        map.put(txtTitle, titlePattern);
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtCity, cityPattern);
        map.put(txtProvince, provincePattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnSaveData);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
            }
        }
    }
}
