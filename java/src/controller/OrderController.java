package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import model.ShoppingList;
import service.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    void GoToClient(ActionEvent event) {

    }

    @FXML
    void addAction(ActionEvent event) {

    }

    @FXML
    void confirmAction(ActionEvent event) {

    }

    @FXML
    void deleteOrderAction(ActionEvent event) {

    }

    @FXML
    void goToShopping(ActionEvent event) {

    }

    @FXML
    void goToStore(ActionEvent event) {

    }

    @FXML
    void modifyAction(ActionEvent event) {

    }

    private void globalOrderSelect() throws SQLException {
        orderList.clear();
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("SELECT date,godzina_w,liczba_gosci,bar,dodatkowo,adres FROM zamowienia");
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            Order order = new Order(result.getString("date"), result.getString("godzina_w"),
                    result.getInt("liczba_gosci"), result.getString("bar"), result.getString("dodatkowo"), result.getString("adres"));
            orderList.add(order);
        }
        //uzupelnienie zawartosci  tableColumn
        col_data.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        col_time.setCellValueFactory(new PropertyValueFactory<Order, String>("time"));
        col_guests.setCellValueFactory(new PropertyValueFactory<Order, Integer>("guests"));
        col_bar.setCellValueFactory(new PropertyValueFactory<Order, String>("bar"));
        col_additional.setCellValueFactory(new PropertyValueFactory<Order, String>("additional"));
        col_address.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
        tbl_orders.setItems(orderList);
    }

    public void initialize() throws SQLException {
        globalOrderSelect();
    }

}
