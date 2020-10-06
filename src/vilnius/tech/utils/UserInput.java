package vilnius.tech.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserInput {

    public static String getString(Scanner scanner, String prompt) {
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }

    public static boolean getUserConfirmation(Scanner scanner, String prompt) {
        return availableConfirmationResponses.contains(getString(scanner, String.format("%s (yes/no)", prompt)).toLowerCase());
    }

    public static boolean getDeleteConfirmation(Scanner scanner, String toDelete, String references, int referenceCount) {
        return getUserConfirmation(scanner, String.format("This %s has %s %s references. Do you want to delete it?", toDelete, referenceCount, references));
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

    private static List<String> availableConfirmationResponses = new ArrayList<>();
    static {
        availableConfirmationResponses.add("yes");
        availableConfirmationResponses.add("y");
    }
}
