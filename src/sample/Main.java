package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}