package vilnius.tech;

import vilnius.tech.controller.CompanyController;
import vilnius.tech.controller.Serializer;
import vilnius.tech.dal.Session;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Session session = Serializer.loadSession(scanner);

        new CompanyController(session, 0).manage(scanner);


    }
}
