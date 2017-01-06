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

        boolean result = Client.getInstance().login(login, password);
        resultText.setText(String.valueOf(result));
        if (result) {
            showHome(event);
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
