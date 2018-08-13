package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.DBConnect;

import java.io.IOException;
import java.sql.Connection;

public class Controller {
    DBConnect dao;
    Connection conn;

    @FXML
    private Button btn_client;

    @FXML
    private Button btn_order;

    @FXML
    private Button btn_shopping;

    @FXML
    private Button btn_store;

    @FXML
    void clientAction(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_order.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/clientView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Klienci");
        clientStage.show();

    }

    @FXML
    void orderAction(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_order.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/orderView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Zamówienia");
        clientStage.show();
    }

    @FXML
    void shoppingAction(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_order.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/shoppingView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Zakupy");
        clientStage.show();

    }

    @FXML
    void storeAction(ActionEvent event) throws IOException {
        Stage currentstage = (Stage) btn_order.getScene().getWindow();
        currentstage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/storeView.fxml"));
        Scene scene = new Scene(root);
        Stage clientStage = new Stage();
        clientStage.setScene(scene);
        clientStage.setTitle("Magazyn");
        clientStage.show();

    }
    public void initialize() {
        dao = new DBConnect();
        conn = dao.getCon();

    }
}

