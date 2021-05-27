package sample.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import sample.Client.Model.Model;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    Model model;

    //Окно отрисовки объектов
    @FXML
    private Canvas GameCanvas;

    @FXML
    private Canvas GamePanelCanvas;

    @FXML
    private AnchorPane APMenu;

    @FXML
    private HBox HBEnergy;

    @FXML
    private HBox HBScore;

    //Инициализация при запуске формы
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new Model();
        model.Initialize(GameCanvas.getGraphicsContext2D(), GamePanelCanvas.getGraphicsContext2D(), APMenu, HBScore, HBEnergy);
    }
}