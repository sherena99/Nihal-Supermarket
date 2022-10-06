package controller;

import bo.BoFactory;
import bo.custom.ItemBO;
import dto.ItemDTO;
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
import viewCashier.tm.ItemTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDetailsFormController {
    public AnchorPane itemDetailsFormContext;
    public AnchorPane loadItemDetailsContext;
    public TableView <ItemTm> tblItemDetails;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colPrice;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        try {
            setItemDetailsToTable(itemBO.getAllItems());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setItemDetailsToTable(ArrayList<ItemDTO> item) {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        item.forEach(e -> {
            obList.add(
                    new ItemTm(e.getCode(), e.getDescription(), e.getPackSize(), e.getQtyOnHand(), e.getDiscount(),e.getUnitPrice()));
        });
        tblItemDetails.setItems(obList);

    }

    public void goBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ManagementDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) itemDetailsFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void addNewItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/RegisterNewItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadItemDetailsContext.getChildren().clear();
        loadItemDetailsContext.getChildren().add(load);
    }

    public void modifyItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ModifyItemDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadItemDetailsContext.getChildren().clear();
        loadItemDetailsContext.getChildren().add(load);
    }

    public void removeItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../viewManagement/ItemRemoveForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadItemDetailsContext.getChildren().clear();
        loadItemDetailsContext.getChildren().add(load);
    }
}
