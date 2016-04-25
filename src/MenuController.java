import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Austin on 4/15/2016.
 */
public class MenuController extends Application{

    @FXML
    private Button begin;
    @FXML
    private Button help;


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent root;
        Scene scene;
        Stage stage;

        try {
            if (event.getSource() == begin) {
                root = FXMLLoader.load(getClass().getResource("game.fxml"));
                scene = new Scene(root, 1280, 875);

                stage = (Stage) begin.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

                centerWindow(stage);
            }

            if (event.getSource() == help) {
                root = FXMLLoader.load(getClass().getResource("help.fxml"));
                scene = new Scene(root, 250, 150);

                stage = new Stage();
                stage.setScene(scene);
                stage.show();

                centerWindow(stage);
            }
        }catch (IOException e){
            e.printStackTrace();

        }


    }

    public void centerWindow(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));



        Scene scene = new Scene(root, 300, 400);


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
