package bsr.client.ui;

import bsr.client.Client;
import bsr.server.exception.NotFound;
import bsr.server.exception.TooSmallBalance;
import bsr.server.model.Payment;
import bsr.server.model.Transfer;
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
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Maciej on 2017-01-02.
 */
public class TransferController {

    @FXML
    private TextField senderText;

    @FXML
    private TextField receiverText;

    @FXML
    private TextField amountText;

    @FXML
    private TextField titleText;

    @FXML
    private Text resultText;

    @FXML
    public void onTransferClick(ActionEvent event) {
        String sender = senderText.getText();
        String receiver = receiverText.getText();
        String title = titleText.getText();

        if (sender.isEmpty() || receiver.isEmpty() || title.isEmpty()) {
            resultText.setText("Pola nie mogą być puste");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText.getText());
            Transfer transfer = new Transfer("title", amount, "sender", "receiver");
            Client.getInstance().getBankService().transfer(transfer);
        } catch (NumberFormatException ignored) {
            resultText.setText("Nieprawidłowa wartość kwoty");
        } catch (NotFound notFound) {
            resultText.setText("Nie znaleziono konta");
        } catch (TooSmallBalance tooSmallBalance) {
            resultText.setText("Za małe saldo");
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
