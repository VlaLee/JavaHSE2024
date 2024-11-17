import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Exceptions.IllegalInputException;
import Exceptions.IllegalDateException;

public class PersonDataAnalyzer {

    private final String name, surname, patronymic;
    private final LocalDate birthDate;
    private final int age;

    public PersonDataAnalyzer(String NSP, String dateOfBirth) throws
            NullPointerException, IllegalInputException, IllegalDateException {

        if (NSP == null) {
            throw new NullPointerException("Поле ФИО не может принимать значение null");
        }

        if (dateOfBirth == null) {
            throw new NullPointerException("Поле дата рождения не может принимать значение null");
        }

        String[] temp = NSP.split(" ");

        if (temp.length != 3) {
            throw new IllegalInputException("В поле ФИО было указано меньше/больше 3 слов");
        }

        this.surname = temp[0];
        this.name = temp[1];
        this.patronymic = temp[2];

        String pattern = "dd.MM.yyyy";

        try {

            birthDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(pattern));

            boolean isEqual = birthDate.format(DateTimeFormatter.ofPattern(pattern)).equals(dateOfBirth);

            if (!isEqual) {
                throw new IllegalDateException("Неверная или несуществующая дата");
            }

            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);

            if (period.getDays() < 0) {
                throw new IllegalDateException("Была указана будущая дата");
            }

            age = period.getYears();

        } catch (DateTimeParseException e) {
            throw new IllegalDateException("Неверный формат даты");
        }
    }

    public int getAge() {
        return age;
    }

    private String getAgeEnding() {
        int lastDigit = age % 10;
        int lastTwoDigit = age % 100;

        if (lastTwoDigit >= 11 && lastTwoDigit <= 14) {
            return "лет";
        }

        return switch (lastDigit) {
            case 1 -> "год";
            case 2, 3, 4 -> "года";
            default -> "лет";
        };
    }

    public String getSex() {
        char lastChar = patronymic.charAt(patronymic.length() - 1);

        return switch (lastChar) {
            case 'ч' -> "мужской";
            case 'а' -> "женский";
            default -> "НЕ ОПРЕДЕЛЕН";
        };
    }

    public String getInitials() {
        return String.format("%s %c.%c.", surname, name.charAt(0), patronymic.charAt(0));
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getDateOfBirth() {
        return birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Инициалы: ").append(getInitials()).append('\n');
        result.append("Пол: ").append(getSex()).append(".\n");

        result.append("Возраст: ").append(age).append(' ');
        result.append(getAgeEnding()).append('.');

        return new String(result);
    }
}
