package bsr.client.ui;

import bsr.client.Client;
import bsr.server.exception.NotFound;
import bsr.server.exception.TooSmallBalance;
import bsr.server.model.Bill;
import bsr.server.model.Transfer;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Maciej on 2017-01-02.
 */
public class TransferController implements Initializable, ChangeListener<Number> {

    public static final int BILL_NUMBER_LENGTH = 26;
    @FXML
    private ChoiceBox<Bill> billsChoiceBox;

    @FXML
    private TextField receiverText;

    @FXML
    private TextField amountText;

    @FXML
    private TextField titleText;

    @FXML
    private Text resultText;

    private Bill bill;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        receiverText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (receiverText.getText().length() > BILL_NUMBER_LENGTH) {
                    String s = receiverText.getText().substring(0, BILL_NUMBER_LENGTH);
                    receiverText.setText(s);
                }
            }
        });
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
    public void onTransferClick(ActionEvent event) {
        String receiver = receiverText.getText();
        String title = titleText.getText();

        if (bill == null) {
            resultText.setText("Wybierz swój rachunek");
            return;
        }

        if (bill.getNumber().equals(receiver)) {
            resultText.setText("Te same numery konta");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText.getText());
            if (amount < 0.01) {
                throw new NumberFormatException();
            }
            Transfer transfer = new Transfer(title, (int) (amount * 100), bill.getNumber(), receiver);
            Client.getInstance().getBankService().transfer(transfer);
            resultText.setText("Przelew zaakceptowano.");
        } catch (NumberFormatException ignored) {
            resultText.setText("Nieprawidłowa wartość kwoty");
        } catch (NotFound notFound) {
            resultText.setText("Nie znaleziono konta");
        } catch (TooSmallBalance tooSmallBalance) {
            resultText.setText("Za małe saldo");
        } catch (IOException e) {
            resultText.setText("Brak Internetu");
        }
    }

    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        showHome(event);
    }

    private void showHome(ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
        Scene scene = new Scene(view);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        Client.getInstance().setBill(newValue.intValue());
        bill = Client.getInstance().getBill();
    }
}
