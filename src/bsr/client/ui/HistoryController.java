package bsr.client.ui;

import bsr.client.Client;
import bsr.server.exception.NotFound;
import bsr.server.model.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Maciej on 2017-01-01.
 */
public class HistoryController implements Initializable {

    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<Transaction> history = Client.getInstance().getHistory();
            for (Transaction transfer : history) {
                listView.getItems().addAll(transfer.toString());
            }
        } catch (NotFound notFound) {
            listView.getItems().add("Nie znaleziono rachunku");
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
