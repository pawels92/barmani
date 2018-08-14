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
import model.Order;
import model.ShoppingList;
import service.DBConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderController {

    //obiekty globalne
    DBConnect db;
    PreparedStatement ps;
    private ObservableList<Order> orderList = FXCollections.observableArrayList();

    @FXML
    private TableView<Order> tbl_orders;

    @FXML
    private TableColumn<Order, String> col_data;

    @FXML
    private TableColumn<Order, String> col_time;

    @FXML
    private TableColumn<Order, Integer> col_guests;

    @FXML
    private TableColumn<Order, String>col_bar;

    @FXML
    private TableColumn<Order, String> col_additional;

    @FXML
    private TableColumn<Order, String> col_address;

    @FXML
    private TableColumn<Order, String> col_klient;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_modify;

    @FXML
    private TextField tf_bar;

    @FXML
    private TextField tf_additionals;

    @FXML
    private TextField tf_client;

    @FXML
    private TextField tf_guests;

    @FXML
    private MenuItem mi_client;

    @FXML
    private MenuItem mi_shopping;

    @FXML
    private MenuItem mi_store;

    @FXML
    private DatePicker dp_date;

    @FXML
    private TextField tf_time;

    @FXML
    private TextArea ta_address;

    @FXML
    private Button btn_confirmation;

    @FXML
    void GoToClient(ActionEvent event) throws IOException {
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
    void addAction(ActionEvent event) {     dp_date.setDisable(false);
        tf_time.setDisable(false);
        tf_client.setDisable(false);
        tf_additionals.setDisable(false);
        tf_bar.setDisable(false);
        tf_guests.setDisable(false);
        ta_address.setDisable(false);
    }

    @FXML
    void confirmAction(ActionEvent event) throws SQLException {
        db = new DBConnect();
        Connection conn = db.getCon();
        String date = new String();
        date = String.valueOf(dp_date.getValue());

        //dopis do tabelki course
        ps = conn.prepareStatement(
                "INSERT into zamowienia(date ,godzina_w, liczba_gosci, bar, Dodatkowo, Adres, klienci_id_k) " +
                        "VALUES (?, ? ,?, ?, ? ,?, (SELECT id_k from klienci WHERE nazwisko_k = ?))");

        ps.setString(1, date);
        ps.setString(2, tf_time.getText());
        ps.setInt(3, Integer.parseInt(tf_guests.getText()));
        ps.setString(4, tf_bar.getText());
        ps.setString(5, tf_additionals.getText());
        ps.setString(6, ta_address.getText());
        ps.setString(7, tf_client.getText());
        ps.executeUpdate();
        conn.commit();

        tf_client.clear();
        tf_additionals.clear();
        tf_bar.clear();
        tf_guests.clear();
        tf_time.clear();
        ta_address.clear();

        dp_date.setDisable(true);
        tf_time.setDisable(true);
        tf_client.setDisable(true);
        tf_additionals.setDisable(true);
        tf_bar.setDisable(true);
        tf_guests.setDisable(true);
        ta_address.setDisable(true);
        globalOrderSelect();
    }


    @FXML
    void deleteOrderAction(ActionEvent event) throws SQLException {
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("DELETE FROM zamowienia WHERE date =?");
        ps.setString(1,tbl_orders.getSelectionModel().getSelectedItem().getDate());
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Potwierdzenie usunięcie zamówienia");
        confirmDialog.setHeaderText("Czy na pewno chcesz usunąć wybrane zamówienie?");
        confirmDialog.setContentText("Jeśli chcesz usunąć zamówienie wybierz OK, jeśli nie - anuluj");
        Optional<ButtonType> decision = confirmDialog.showAndWait();
        if(decision.get() == ButtonType.OK) {
            ps.executeUpdate();
            conn.commit();
            globalOrderSelect();
        }

    }

    @FXML
    void goToShopping(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_add.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/shoppingView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Zakupy");
        clientStage.show();
    }

    @FXML
    void goToStore(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_add.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/storeView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Magazyn");
        clientStage.show();

    }

    @FXML
    void modifyAction(ActionEvent event) {

    }

    private void globalOrderSelect() throws SQLException {
        orderList.clear();
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("SELECT zamowienia.date, zamowienia.godzina_w, zamowienia.liczba_gosci, zamowienia.bar, zamowienia.Dodatkowo, " +
                "zamowienia.adres, klienci.nazwisko_k FROM zamowienia JOIN klienci on (klienci.ID_k=zamowienia.klienci_id_k);");

        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Order order = new Order(result.getString("date"), result.getString("godzina_w"),
                    result.getInt("liczba_gosci"), result.getString("bar"), result.getString("dodatkowo"),
                    result.getString("adres"),result.getString("nazwisko_k"));
            orderList.add(order);
        }
        //uzupelnienie zawartosci  tableColumn
        col_data.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        col_time.setCellValueFactory(new PropertyValueFactory<Order, String>("time"));
        col_guests.setCellValueFactory(new PropertyValueFactory<Order, Integer>("guests"));
        col_bar.setCellValueFactory(new PropertyValueFactory<Order, String>("bar"));
        col_additional.setCellValueFactory(new PropertyValueFactory<Order, String>("additional"));
        col_address.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
        col_klient.setCellValueFactory(new PropertyValueFactory<Order, String>("client"));
        tbl_orders.setItems(orderList);
    }



    public void initialize() throws SQLException {
        globalOrderSelect();
    }

}
