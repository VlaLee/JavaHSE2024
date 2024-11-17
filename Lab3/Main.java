import Exceptions.IllegalDateException;
import Exceptions.IllegalInputException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ФИО:");
        String NSP = scanner.nextLine();

        System.out.println("Введите дату рождения в формате \"день.месяц.год\" (например 01.01.1970):");
        String dateOfBirth = scanner.nextLine();

        try {

            PersonDataAnalyzer personDataAnalyzer = new PersonDataAnalyzer(NSP, dateOfBirth);
            System.out.println(personDataAnalyzer);

        } catch (NullPointerException | IllegalInputException | IllegalDateException e) {
            System.out.println(e.getMessage());
        }
    }
}