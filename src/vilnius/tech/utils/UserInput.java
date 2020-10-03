package vilnius.tech.utils;

import java.util.Optional;
import java.util.Scanner;

public class UserInput {

    public static String getString(Scanner scanner, String prompt) {
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }

    public static Optional<Integer> toOid(String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        }
        catch (NumberFormatException exception) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> getOid(Scanner scanner, String prompt) {
        return toOid(UserInput.getString(scanner, prompt));
    }
}
