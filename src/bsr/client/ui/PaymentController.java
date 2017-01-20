package bsr.client.ui;

import bsr.Constants;
import bsr.client.Client;
import bsr.server.exception.NotFound;
import bsr.server.exception.TooSmallBalance;
import bsr.server.model.Payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Maciej on 2017-01-02.
 */
public class PaymentController {

    @FXML
    private TextField amountText;

    @FXML
    private Text resultText;

    @FXML
    protected void onPaymentOutClick(ActionEvent event) throws IOException {
        try {
            double amount = Double.parseDouble(amountText.getText());
            if (amount < 0.01) {
                throw new NumberFormatException();
            }
            Client client = Client.getInstance();
            Payment payment = new Payment(amount, client.getBill().getNumber());
            client.getBankService().paymentOut(payment);
        } catch (NumberFormatException ignored) {
            resultText.setText("Nieprawidłowa wartość");
        } catch (NotFound notFound) {
            resultText.setText("Nie znaleziono konta");
        } catch (TooSmallBalance tooSmallBalance) {
            resultText.setText("Za małe saldo");
        }
    }

    @FXML
    protected void onPaymentInClick(ActionEvent event) throws IOException {
        try {
            double amount = Double.parseDouble(amountText.getText());
            if (amount < 0.01) {
                throw new NumberFormatException();
            }
            Client client = Client.getInstance();
            Payment payment = new Payment(amount, client.getBill().getNumber());
            Client.getInstance().getBankService().paymentIn(payment);
        } catch (NumberFormatException ignored) {
            resultText.setText("Nieprawidłowa wartość");
        } catch (NotFound notFound) {
            resultText.setText("Nie znaleziono konta");
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
}
