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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Client;
import service.DBConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ClientController {
    //obiekty globalne
    DBConnect db;
    PreparedStatement ps;
    private ObservableList<Client> clientList = FXCollections.observableArrayList();

    @FXML
    private TableView<Client> tbl_clients;

    @FXML
    private TableColumn<Client, String> name;

    @FXML
    private TableColumn<Client, String> lastname;

    @FXML
    private TableColumn<Client, Integer> phone_number;

    @FXML
    private TableColumn<Client, String> e_mail;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_modify;

    @FXML
    private TextField tf_clientName;

    @FXML
    private TextField tf_clientLastname;

    @FXML
    private TextField tf_clientPhone;

    @FXML
    private TextField tf_email;

    @FXML
    private Button btn_submit;

    @FXML
    private MenuItem mi_orders;


    @FXML
    private MenuItem mi_shopping;

    @FXML
    private MenuItem mi_store;

    @FXML
    void ShoppingAction(ActionEvent event) throws IOException {
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
    void add_activation(MouseEvent event) {
        tf_clientName.setDisable(false);
        tf_clientLastname.setDisable(false);
        tf_clientPhone.setDisable(false);
        tf_email.setDisable(false);

    }

    @FXML
    void addClientAction(MouseEvent event) throws SQLException {
                db = new DBConnect();
                Connection conn = db.getCon();
                //dopis do tabelki course
                ps = conn.prepareStatement(
                        "INSERT into klienci(Imie_k ,Nazwisko_k, Telefon_k, email_k) " +
                                "VALUES (?, ? ,?, ?)");

                ps.setString(1, tf_clientName.getText());
                ps.setString(2, tf_clientLastname.getText());
                ps.setInt(3, Integer.parseInt(tf_clientPhone.getText()));
                ps.setString(4, tf_email.getText());
                ps.executeUpdate();
                conn.commit();
                tf_clientName.clear();
                tf_clientLastname.clear();
                tf_clientPhone.clear();
                tf_email.clear();
                tf_clientName.setDisable(true);
                tf_clientLastname.setDisable(true);
                tf_clientPhone.setDisable(true);
                tf_email.setDisable(true);
                globalSelect();
                }

    @FXML
    void deleteClientAction(ActionEvent event) throws SQLException {
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("DELETE FROM klienci WHERE nazwisko_k =?");
        ps.setString(1,tbl_clients.getSelectionModel().getSelectedItem().getLastname());
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Potwierdzenie usunięcie użytkownika");
        confirmDialog.setHeaderText("Czy na pewno chcesz usunąć wybranego użytkownika?");
        confirmDialog.setContentText("Jeśli chcesz usunąć użytkownika wybierz OK, jeśli nie - anuluj");
        Optional<ButtonType> decision = confirmDialog.showAndWait();
        if(decision.get() == ButtonType.OK) {
            ps.executeUpdate();
            conn.commit();
            globalSelect();
        }

    }


    @FXML
    void modifyClientAction(ActionEvent event) {

    }

    @FXML
    void orderAction(ActionEvent event) throws IOException {
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
    void storeAction(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_add.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/storeView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Magazyn");
        clientStage.show();

    }

    private void globalSelect() throws SQLException {
        clientList.clear();
        tbl_clients.refresh();
        db = new DBConnect();
        Connection conn = db.getCon();
        ps = conn.prepareStatement("SELECT Imie_k,Nazwisko_k,Telefon_k,email_k FROM klienci");
        ResultSet result = ps.executeQuery();
        //wypełnienie zawartości w userList
        while(result.next()){
            Client client = new Client(result.getString("imie_k"),result.getString("Nazwisko_k"),
                    result.getInt("Telefon_k"),result.getString("email_k"));
            clientList.add(client);
        }
        //uzupelnienie zawartosci  tableColumn
        name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));
        phone_number.setCellValueFactory(new PropertyValueFactory<Client, Integer>("phoneNumber"));
        e_mail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));

        //wprowadzenie obiektów klasy modelu do tableVIEW
        tbl_clients.setItems(clientList);
    }

    public void initialize() throws SQLException {
        globalSelect();
    }
}