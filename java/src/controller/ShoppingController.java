package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Client;
import model.ShoppingList;
import service.DBConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingController {
    //obiekty globalne
    DBConnect db;
    PreparedStatement ps;
    private ObservableList<ShoppingList> shoppingList = FXCollections.observableArrayList();

    @FXML
    private TableView<ShoppingList> tbl_first_option;

    @FXML
    private TableColumn<ShoppingList, String> col_prod1;

    @FXML
    private TableColumn<ShoppingList, String> col_unit1;

    @FXML
    private TableColumn<ShoppingList, Integer> col_amount1;

    @FXML
    private TableColumn<ShoppingList, Double> col_price1;

    @FXML
    private TableColumn<ShoppingList, Double> col_summary1;


    @FXML
    private TableColumn<ShoppingList, String> col_mall1;


    private ObservableList<ShoppingList> shoppingList2 = FXCollections.observableArrayList();

    @FXML
    private TableView<ShoppingList> tbl_foption2;

    @FXML
    private TableColumn<ShoppingList, String> col_prod2;

    @FXML
    private TableColumn<ShoppingList, String> col_unit2;

    @FXML
    private TableColumn<ShoppingList, Integer> col_amount2;

    @FXML
    private TableColumn<ShoppingList, Double> col_price2;

    @FXML
    private TableColumn<ShoppingList, Double> col_summary2;


    @FXML
    private TableColumn<ShoppingList, String> col_mall2;

    private ObservableList<ShoppingList> shoppingList3 = FXCollections.observableArrayList();

    @FXML
    private TableView<ShoppingList> tbl_foption3;

    @FXML
    private TableColumn<ShoppingList, String> col_prod3;

    @FXML
    private TableColumn<ShoppingList, String> col_unit3;

    @FXML
    private TableColumn<ShoppingList, Integer> col_amount3;

    @FXML
    private TableColumn<ShoppingList, Double> col_col_price3;

    @FXML
    private TableColumn<ShoppingList, Double> col_summary3;

    @FXML
    private TableColumn<ShoppingList, String> col_mall3;

    private ObservableList<ShoppingList> shoppingList4 = FXCollections.observableArrayList();

    @FXML
    private TableView<ShoppingList> tbl_foption4;

    @FXML
    private TableColumn<ShoppingList, String> col_prod4;

    @FXML
    private TableColumn<ShoppingList, String> col_unit4;

    @FXML
    private TableColumn<ShoppingList, Integer> col_amount4;

    @FXML
    private TableColumn<ShoppingList, Double> col_col_price4;

    @FXML
    private TableColumn<ShoppingList, Double> col_summary4;

    @FXML
    private TableColumn<ShoppingList, String> col_mall4;

    @FXML
    private Button btn_add_prod;

    @FXML
    private TextField tf_product_name;

    @FXML
    private TextField tf_product_unit;

    @FXML
    private TextField tf_product_amount;

    @FXML
    private TextField tf_product_price;

    @FXML
    private MenuItem mi_orders;

    @FXML
    private MenuItem mi_clients;

    @FXML
    private MenuItem mi_store;

    @FXML
    private Button btn_delete_product;



    @FXML
    private Button btn_modify_product;

    @FXML
    private TextField tf_new_value;

    @FXML
    private TextField tf_product_mall;

    @FXML
    void adding_confirmation(MouseEvent event) {

    }

    @FXML
    void goToClients(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_add_prod.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/clientView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Klienci");
        clientStage.show();

    }

    @FXML
    void goToOrders(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_add_prod.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Zamówienia");
        clientStage.show();

    }

    @FXML
    void goToStore(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_add_prod.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/storeView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Magazyn");
        clientStage.show();
    }

    @FXML
    void modify_confirmation(MouseEvent event) {

    }

    @FXML
    void prod_fields_activation(MouseEvent event) {

    }


    private void globalShoppingSelect() throws SQLException {
        shoppingList.clear();
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("SELECT produkt,jednostka,ilość,cena,suma,sklep FROM zakupy100_150");
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            ShoppingList shopList = new ShoppingList(result.getString("produkt"), result.getString("jednostka"),
                    result.getInt("ilość"), result.getDouble("cena"), result.getDouble("suma"), result.getString("sklep"));
            shoppingList.add(shopList);
        }
        //uzupelnienie zawartosci  tableColumn
        col_prod1.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("product"));
        col_unit1.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("unit"));
        col_amount1.setCellValueFactory(new PropertyValueFactory<ShoppingList, Integer>("amount"));
        col_price1.setCellValueFactory(new PropertyValueFactory<ShoppingList, Double>("price"));
        col_summary1.setCellValueFactory(new PropertyValueFactory<ShoppingList, Double>("summary"));
        col_mall1.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("mall"));

        tbl_first_option.setItems(shoppingList);
    }

    public void globalShoppingSelecto2() throws SQLException {
    shoppingList2.clear();
    db = new DBConnect();
    Connection conn = db.getCon();
    ps = conn.prepareStatement("SELECT produkt,jednostka,ilość,cena,suma,sklep FROM zakupy150_200");
    ResultSet result = ps.executeQuery();
        while(result.next()){
        ShoppingList shopList2 = new ShoppingList(result.getString("produkt"),result.getString("jednostka"),
                result.getInt("ilość"),result.getDouble("cena"),result.getDouble("suma"),result.getString("sklep"));
        shoppingList2.add(shopList2);
    }
    //uzupelnienie zawartosci  tableColumn
        col_prod2.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("product"));
        col_unit2.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("unit"));
        col_amount2.setCellValueFactory(new PropertyValueFactory<ShoppingList, Integer>("amount"));
        col_price2.setCellValueFactory(new PropertyValueFactory<ShoppingList, Double>("price"));
        col_summary2.setCellValueFactory(new PropertyValueFactory<ShoppingList, Double>("summary"));
        col_mall2.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("mall"));

        tbl_foption2.setItems(shoppingList2);
    }

    public void globalShoppingSelecto3() throws SQLException {
        shoppingList3.clear();
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("SELECT produkt,jednostka,ilość,cena,suma,sklep FROM zakupy200_250");
        ResultSet result = ps.executeQuery();
        while(result.next()){
            ShoppingList shopList3 = new ShoppingList(result.getString("produkt"),result.getString("jednostka"),
                    result.getInt("ilość"),result.getDouble("cena"),result.getDouble("suma"),result.getString("sklep"));
            shoppingList3.add(shopList3);
        }
        //uzupelnienie zawartosci  tableColumn
        col_prod3.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("product"));
        col_unit3.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("unit"));
        col_amount3.setCellValueFactory(new PropertyValueFactory<ShoppingList, Integer>("amount"));
        col_col_price3.setCellValueFactory(new PropertyValueFactory<ShoppingList, Double>("price"));
        col_summary3.setCellValueFactory(new PropertyValueFactory<ShoppingList, Double>("summary"));
        col_mall3.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("mall"));

        tbl_foption3.setItems(shoppingList3);
    }

    public void globalShoppingSelecto4() throws SQLException {
        shoppingList4.clear();
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("SELECT produkt,jednostka,ilość,cena,suma,sklep FROM zakupy300_350");
        ResultSet result = ps.executeQuery();
        while(result.next()){
            ShoppingList shopList4 = new ShoppingList(result.getString("produkt"),result.getString("jednostka"),
                    result.getInt("ilość"),result.getDouble("cena"),result.getDouble("suma"),result.getString("sklep"));
            shoppingList4.add(shopList4);
        }
        //uzupelnienie zawartosci  tableColumn
        col_prod4.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("product"));
        col_unit4.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("unit"));
        col_amount4.setCellValueFactory(new PropertyValueFactory<ShoppingList, Integer>("amount"));
        col_col_price4.setCellValueFactory(new PropertyValueFactory<ShoppingList, Double>("price"));
        col_summary4.setCellValueFactory(new PropertyValueFactory<ShoppingList, Double>("summary"));
        col_mall4.setCellValueFactory(new PropertyValueFactory<ShoppingList, String>("mall"));

        tbl_foption4.setItems(shoppingList4);
    }


    public void initialize() throws SQLException {
        globalShoppingSelect();
        globalShoppingSelecto2();
        globalShoppingSelecto3();
        globalShoppingSelecto4();
    }
}
