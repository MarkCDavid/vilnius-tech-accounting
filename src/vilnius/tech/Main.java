package vilnius.tech;

import vilnius.tech.controller.CompanyController;
import vilnius.tech.controller.Serializer;
import vilnius.tech.dal.Company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Company company = Serializer.loadCompany(scanner);

        new CompanyController(company, 0).manage(scanner);


    }
}
