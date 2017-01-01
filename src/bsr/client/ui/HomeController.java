package bsr.client.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Maciej on 2017-01-01.
 */
public class HomeController {

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

    private void showView(ActionEvent event, String fxml) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("../fxml/" + fxml));
        Scene scene = new Scene(view);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

}
