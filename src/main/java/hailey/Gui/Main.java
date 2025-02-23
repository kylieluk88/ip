package hailey.Gui;

import java.io.IOException;

import hailey.Hailey;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Hailey hailey = new Hailey("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Hailey");
            fxmlLoader.<hailey.Gui.MainWindow>getController().setHailey(hailey);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
