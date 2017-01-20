package bsr.client.ui;

import bsr.client.Client;
import bsr.server.model.Bill;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Maciej on 2017-01-01.
 */
public class HomeController implements Initializable, ChangeListener<Number> {

    @FXML
    private ChoiceBox<Bill> billsChoiceBox;

    @FXML
    private Label balanceLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        billsChoiceBox.getSelectionModel().selectedIndexProperty().addListener(this);
    }

    private void refresh() {
        billsChoiceBox.getItems().clear();
        Client client = Client.getInstance();
        client.fetchBills();
        billsChoiceBox.getItems().addAll(client.getBills());
    }

    @FXML
    public void onTransferClick(ActionEvent event) throws IOException {
        showView(event, "transfer.fxml");
    }

    @FXML
    public void onPaymentClick(ActionEvent event) throws IOException {
        showView(event, "payment.fxml");
    }

    @FXML
    public void onHistoryClick(ActionEvent event) throws IOException {
        showView(event, "history.fxml");
    }

    @FXML
    public void onAddBillClick(ActionEvent event) throws IOException {
        Client client = Client.getInstance();
        client.createBill();
        client.fetchBills();
        refresh();
    }

    private void showView(ActionEvent event, String fxml) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("../fxml/" + fxml));
        Scene scene = new Scene(view);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        Client.getInstance().setBill(newValue.intValue());
        Bill bill = Client.getInstance().getBill();
        balanceLabel.setText(String.valueOf(bill == null ? "" : bill.getBalance() + " PLN"));
    }
}
