package sample.Client.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public interface IModel {
    void Initialize(GraphicsContext GameCanvas, GraphicsContext GamePanelCanvas, AnchorPane APMenu, HBox HBScore, HBox HBEnergy);

    void DrawScore(int Score, int OldScore, HBox HBScore);

    //Функиця перевода английских букв в русские
    String RusText(Character c);

    //Конвертирование анг. текста в рус.; получение индекса буквы
    int IndexRusEng(ArrayList<String> arr);

    //Получение индекса цифры
    int IndexNumber(ArrayList<String> arr);
}
