package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ShoppingList;
import model.Store;
import service.DBConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StoreController {
    DBConnect db;
    PreparedStatement ps;
    private ObservableList<Store> storeList = FXCollections.observableArrayList();


    @FXML
    private TableView<Store> tbl_store;

    @FXML
    private TableColumn<Store, String> col_product;

    @FXML
    private TableColumn<Store, String> col_unit;

    @FXML
    private TableColumn<Store, Integer> col_amount;

    @FXML
    private Button btn_add;

    @FXML
    private MenuItem mi_orders;

    @FXML
    private MenuItem mi_shopping;

    @FXML
    private MenuItem mi_clients;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_confirm;


    @FXML
    private TextField tf_unit;

    @FXML
    private TextField tf_amount;

    @FXML
    private TextField tf_product;

    @FXML
    void addAction(ActionEvent event) {
        tf_product.setDisable(false);
        tf_amount.setDisable(false);
        tf_unit.setDisable(false);
    }

    @FXML
    void delete_action(ActionEvent event) throws SQLException {
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("DELETE FROM magazyn WHERE produkt =?");
        ps.setString(1,tbl_store.getSelectionModel().getSelectedItem().getProduct());
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Potwierdzenie usunięcie produktu");
        confirmDialog.setHeaderText("Czy na pewno chcesz usunąć wybrany produkt?");
        confirmDialog.setContentText("Jeśli chcesz usunąć produkt wybierz OK, jeśli nie - anuluj");
        Optional<ButtonType> decision = confirmDialog.showAndWait();
        if(decision.get() == ButtonType.OK) {
            ps.executeUpdate();
            conn.commit();
            globalStoreSelect();
        }

    }


    @FXML
    void goToClients(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_add.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/clientView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Klienci");
        clientStage.show();
    }


    @FXML
    void addAction_confirm(ActionEvent event) throws SQLException {
        db = new DBConnect();
        Connection conn = db.getCon();
        //dopis do tabelki course
        ps = conn.prepareStatement(
                "INSERT into magazyn(produkt ,jednostka, ilość) " +
                        "VALUES (?, ? ,?)");

        ps.setString(1, tf_product.getText());
        ps.setString(2, tf_unit.getText());
        ps.setInt(3, Integer.parseInt(tf_amount.getText()));
        ps.executeUpdate();
        conn.commit();
        tf_amount.clear();
        tf_product.clear();
        tf_unit.clear();
        tf_product.setDisable(true);
        tf_amount.setDisable(true);
        tf_unit.setDisable(true);
        globalStoreSelect();
    }


    @FXML
    void goToOrders(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_add.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Zamówienia");
        clientStage.show();
    }

    @FXML
    void goToShopping(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_add.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/clientView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Zakupy");
        clientStage.show();

    }



    public void globalStoreSelect() throws SQLException {
        storeList.clear();
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("SELECT produkt,jednostka,ilość FROM magazyn");
        ResultSet result = ps.executeQuery();
        while(result.next()){
            Store store = new Store(result.getString("produkt"),result.getString("jednostka"),
                    result.getInt("ilość"));
            storeList.add(store);
        }
        //uzupelnienie zawartosci  tableColumn
        col_product.setCellValueFactory(new PropertyValueFactory<Store, String>("product"));
        col_unit.setCellValueFactory(new PropertyValueFactory<Store, String>("unit"));
        col_amount.setCellValueFactory(new PropertyValueFactory<Store, Integer>("amount"));

        tbl_store.setItems(storeList);
    }


    public void initialize() throws SQLException {
        globalStoreSelect();

    }

}
