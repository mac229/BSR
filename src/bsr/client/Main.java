package bsr.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Maciej on 2017-01-01.
 */
public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("BSR - Maciej Kozłowski");
        stage.setScene(scene);
        stage.show();
    }
}
