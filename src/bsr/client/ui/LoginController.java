package bsr.client.ui;

import bsr.client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by Maciej on 2017-01-01.
 */
public class LoginController {

    @FXML
    private TextField loginText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Text resultText;

    @FXML
    protected void onLoginClick(ActionEvent event) throws IOException {
        String login = loginText.getText();
        String password = passwordText.getText();

        Client client = Client.getInstance();
        long accountId = client.getBankService().login(login, password);
        client.getBills(accountId);

        if (accountId >= 0) {
            showHome(event);
        } else {
            resultText.setText("Nieprawidłowe dane logowania");
        }
    }

    private void showHome(ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
        Scene scene = new Scene(view);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
