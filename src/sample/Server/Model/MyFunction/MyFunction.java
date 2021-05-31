package sample.Server.Model.MyFunction;

import sample.Server.Model.Meteor.IMeteor;
import sample.Server.Model.Meteor.Meteor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class MyFunction implements IMyFunction{

    //Назначение свободного уникльного ID для метеоритов
    @Override
    public int setID(ArrayList<IMeteor> meteors) {

        if (meteors.size() == 0) return 1;

        ArrayList<Integer> BusyID = new ArrayList<>();
        for (IMeteor meteor : meteors)
            BusyID.add(meteor.getID());
        for (int i = 1; i < 1000; i++) {
            if (!BusyID.contains(i)) return i;
        }
        return 0;
    }

    //Функция получение случайных чисел в диапазоне
    @Override
    public int getRandomNumber(int min, int max) {
        if (min > max) return max + new Random().nextInt(min - max + 1);
        else return min + new Random().nextInt(max - min + 1);
    }

    //Функиця перевода английских букв в русские
    @Override
    public String RusText(Character c) {

        c = Character.toLowerCase(c);
        if (c == 'ё') c = 'е';

        ArrayList<Character> rus = new ArrayList<>(
                Arrays.asList('й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з', 'х',
                        'ъ', 'ф', 'ы', 'в', 'а', 'п', 'р', 'о', 'л', 'д', 'ж',
                        'э', 'я', 'ч', 'с', 'м', 'и', 'т', 'ь', 'б', 'ю', 'е'));

        ArrayList<Character> eng = new ArrayList<>(
                Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[',
                        ']', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';',
                        '\'', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '`'));

        if (rus.contains(c)) return c.toString();
        if (eng.contains(c)) return rus.get(eng.indexOf(c)).toString();
        return "";
    }

    //Конвертирование анг. текста в рус.; получение индекса буквы
    @Override
    public int IndexRusEng(ArrayList<String> arr) {

        ArrayList<Character> rus = new ArrayList<>(
                Arrays.asList('й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з', 'х',
                        'ъ', 'ф', 'ы', 'в', 'а', 'п', 'р', 'о', 'л', 'д', 'ж',
                        'э', 'я', 'ч', 'с', 'м', 'и', 'т', 'ь', 'б', 'ю', 'е'));

        ArrayList<Character> eng = new ArrayList<>(
                Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[',
                        ']', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';',
                        '\'', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '`'));

        for (Character i : rus)
            if (arr.contains(i.toString()))
                return arr.indexOf(i.toString());

        for (Character i : eng)
            if (arr.contains(i.toString()))
                return arr.indexOf(i.toString());

        return -1;

    }

    //Получение индекса цифры
    @Override
    public int IndexNumber(ArrayList<String> arr) {
        ArrayList<Character> num = new ArrayList<>(
                Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

        for (Character i : num)
            if (arr != null && arr.contains(i.toString()))
                return arr.indexOf(i.toString());

        return -1;
    }

    //Функция получения слачайного слово из файла словаря
    @Override
    public String getRandomWord() {
        try (Stream<String> lines = Files.lines(Paths.get("./src/sample/Data/Text.txt"), StandardCharsets.UTF_8)) {
            return lines.skip(getRandomNumber(0, 10392)).findFirst().get();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
