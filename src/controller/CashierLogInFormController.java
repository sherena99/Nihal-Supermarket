package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierLogInFormController {
    public AnchorPane cashierLogInContext;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;

    public void logInButtonOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String userName=txtUserName.getText();
        String password=txtPassword.getText();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM UserCashier WHERE userId=? and userPassword=?";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1,userName);
        stm.setString(2,password);
        ResultSet rst = stm.executeQuery();

        txtUserName.clear(); txtPassword.clear();

        if(!rst.next()){
            new Alert(Alert.AlertType.WARNING, "Incorrect User Name Or Password..").show();
        }else{
            Parent parent = FXMLLoader.load(this.getClass().getResource("../viewCashier/CashierDashBoardForm.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) this.cashierLogInContext.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        }
    }



    public void goBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/FirstPageForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierLogInContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
