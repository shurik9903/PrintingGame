package sample.Server.Model.MyFunction;

import sample.Server.Model.Meteor.IMeteor;

import java.util.ArrayList;

public interface IMyFunction {
    //Назначение свободного уникльного ID для метеоритов
    int setID(ArrayList<IMeteor> meteors);

    //Функция получение случайных чисел в диапазоне
    int getRandomNumber(int min, int max);

    //Функиця перевода английских букв в русские
    String RusText(Character c);

    //Конвертирование анг. текста в рус.; получение индекса буквы
    int IndexRusEng(ArrayList<String> arr);

    //Получение индекса цифры
    int IndexNumber(ArrayList<String> arr);

    //Функция получения слачайного слово из файла словаря
    String getRandomWord();
}
