package vilnius.tech.session;

import vilnius.tech.session.Session;

import java.io.*;
import java.util.Scanner;

public class Serializer {
    public static Session loadSession(Scanner scanner) {
        String filename = getFilename(scanner);

        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
            return (Session) stream.readObject();
        }
        catch (ClassNotFoundException exception) {
            System.out.printf("File '%s' is corrupted and cannot be loaded.%n", filename);
            return new Session();
        }
        catch (IOException exception) {
            System.out.printf("File '%s' cannot be opened.%n", filename);
            return new Session();
        }
    }

    public static void saveSession(Scanner scanner, Session session) {
        String filename = getFilename(scanner);

        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename))) {
            stream.writeObject(session);
        }
        catch (IOException exception) {
            System.out.printf("Failed writing data to '%s'.%n", filename);
        }
    }

    private static final String DEFAULT_FILENAME = "database";
    private static String getFilename(Scanner scanner) {
        System.out.print("Enter file name: ");
        String filename = scanner.nextLine();
        return String.format("%s.act", filename.isEmpty() ? DEFAULT_FILENAME : filename);
    }

    private Serializer() {

    }
}
