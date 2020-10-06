package vilnius.tech;

import vilnius.tech.controller.MainController;
import vilnius.tech.controller.Serializer;
import vilnius.tech.dal.Session;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Session session = Serializer.loadSession(scanner);

        new MainController(session).manage(scanner);


    }
}
