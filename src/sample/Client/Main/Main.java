package sample.Client.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;



public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../Visual/GameWindow.fxml"));
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
        //Фокусировка на объекта указанного в fxml для перехвата event-ов
        root.requestFocus();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
