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
        if (client.getBankService().login(login, password)) {
            client.setLogin(login);
            client.fetchBills();
            showView(event, "home.fxml");
        } else {
            resultText.setText("Nieprawidłowe dane logowania");
        }
    }

    @FXML
    protected void onAddAccountClick(ActionEvent event) throws IOException {
        showView(event, "add_account.fxml");
    }

    private void showView(ActionEvent event, String fxml) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("../fxml/" + fxml));
        Scene scene = new Scene(view);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
