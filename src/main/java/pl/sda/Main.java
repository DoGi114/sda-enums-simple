package pl.sda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String ANSWER_FILE_NAME = "Answers.txt";

    public static void main(String[] args) {
        String name;
        String surname;
        Answer pastaAnswer;
        Answer weatherAnswer;
        Answer fogAnswer;

        Scanner scanner = new Scanner(System.in);
        printMenu();
        String modeChoice = scanner.nextLine();
        if ("1".equals(modeChoice)) {
            StringBuilder answerStringBuilder = new StringBuilder();
            System.out.println("Imie:");
            name = scanner.nextLine();
            answerStringBuilder.append(name);
            answerStringBuilder.append(";");
            System.out.println("Nazwisko:");
            surname = scanner.nextLine();
            answerStringBuilder.append(surname);
            answerStringBuilder.append(";");
            System.out.println("Czy makaron jest Twoim ulubionym daniem?");
            for(Answer answer : Answer.values()){
                System.out.println(String.format("%s. %s", answer.ordinal() + 1, answer));
            }
            pastaAnswer = Answer.valueOfDisplayText(scanner.nextLine());
            if(pastaAnswer == null){
                System.err.println("Your answer is not valid.");
                System.exit(1);
            }
            answerStringBuilder.append(pastaAnswer);
            answerStringBuilder.append(";");
            System.out.println("Czy pogoda ostatnio była ładna?");
            for(Answer answer : Answer.values()){
                System.out.println(String.format("%s. %s", answer.ordinal() + 1, answer));
            }
            weatherAnswer = Answer.valueOfDisplayText(scanner.nextLine());
            if(weatherAnswer == null){
                System.err.println("Your answer is not valid.");
                System.exit(1);
            }
            answerStringBuilder.append(weatherAnswer);
            answerStringBuilder.append(";");
            System.out.println("Czy ostry jest cień mgły?");
            for(Answer answer : Answer.values()){
                System.out.println(String.format("%s. %s", answer.ordinal() + 1, answer));
            }
            fogAnswer = Answer.valueOfDisplayText(scanner.nextLine());
            if(fogAnswer == null){
                System.err.println("Your answer is not valid.");
                System.exit(1);
            }
            answerStringBuilder.append(fogAnswer);
            answerStringBuilder.append(System.lineSeparator());

            try {
                Files.writeString(Paths.get(ANSWER_FILE_NAME), answerStringBuilder.toString(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println("No such a directory.");
            }
        } else {
            try {
                List<String> resultList = Files.readAllLines(Paths.get(ANSWER_FILE_NAME));
                System.out.println("Results:");
                for(String record : resultList){
                    String[] answers = record.split(";");
                    name = answers[0];
                    surname = answers[1];
                    pastaAnswer = Answer.valueOfDisplayText(answers[2]);
                    weatherAnswer = Answer.valueOfDisplayText(answers[3]);
                    fogAnswer = Answer.valueOfDisplayText(answers[4]);
                    System.out.println(String.format("Imie %s nazwisko %s 1.%s 2.%s 3.%s", name,surname,pastaAnswer,weatherAnswer,fogAnswer));
                }
            } catch (IOException e) {
                System.err.println("No such a directory.");
            }
        }

    }

    private static void printMenu() {
        System.out.println("1. Tryb ankiety");
        System.out.println("2. Tryb wyników");
    }
}
