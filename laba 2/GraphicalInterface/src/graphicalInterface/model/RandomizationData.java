package graphicalInterface.model;

import java.util.Random;

public class RandomizationData {
    private static String[] surnameList = {
            "Смирнов", "Иванов", "Кузнецов", "Соколов", "Попов", "Лебедев", "Козлов", "Новиков", "Морозов", "Петров", "Волков",
            "Соловьёв", "Васильев", "Зайцев", "Павлов", "Семёнов", "Голубев", "Виноградов", "Богданов", "Воробьёв", "Фёдоров", "Михайлов", "Беляев", "Тарасов", "Белов"
    },
            nameList = {
                    "Кирилл", "Степан", "Святослав", "Роман", "Артём", "Александр", "Анатолий", "Вячеслав", "Руслан", "Пётр", "Роман", "Гавриил", "Анатолий", "Кирилл", "Александр"
            },
            patronymicList = {
                    "Витальевич", "Богданович", "Степанович", "Ростиславович", "Степанович", "Витальевич", "Валерьевич", "Святославович",
                    "Ильич", "Львович", "Романович", "Владимирович", "Юрьевич", "Валентинович", "Васильеви"
            },
            computerLanguageList = {
                    "Java", "C++", "C", "C#", "Python", "JavaScript", "Ruby", "Perl"
            };

    public static String reqSurname() {
        return surnameList[new Random().nextInt(surnameList.length)];
    }

    public static String reqName() {
        return nameList[new Random().nextInt(nameList.length)];
    }

    public static String reqPatronymic() {
        return patronymicList[new Random().nextInt(patronymicList.length)];
    }

    public static String reqComputerLanguage() {
        return computerLanguageList[new Random().nextInt(computerLanguageList.length)];
    }

    public static String reqGroupNumber() {
        return (char) (65 + new Random().nextInt(25)) + "" + (char) (65 + new Random().nextInt(25)) + new Random().nextInt(9999);
    }

    public static int reqCourseNumber() {
        return 1 + new Random().nextInt(5);
    }

    public static int reqFinishedWorksCounter() {
        return new Random().nextInt(15);
    }

    public static int reqTotalWorksNumber() {
        return 15 + new Random().nextInt(10 + 1);
    }

}
