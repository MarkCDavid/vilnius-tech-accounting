package vilnius.tech.controller;

import vilnius.tech.dal.Company;

import java.io.*;
import java.util.Scanner;

public class Serializer {
    public static Company loadCompany(Scanner scanner) {
        String filename = getFilename(scanner);

        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
            return (Company) stream.readObject();
        }
        catch (ClassNotFoundException exception) {
            System.out.printf("File '%s' is corrupted and cannot be loaded.%n", filename);
            return new CompanyController().create(scanner);
        }
        catch (IOException exception) {
            System.out.printf("File '%s' cannot be opened.%n", filename);
            return new CompanyController().create(scanner);
        }
    }

    public static void saveCompany(Scanner scanner, Company company) {
        String filename = getFilename(scanner);

        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename))) {
            stream.writeObject(company);
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
