package vilnius.tech.utils;

import javafx.application.Application;

public class Parameters {

    public static boolean getDropDatabase() {
        return dropDatabase;
    }

    public static void initialize(Application.Parameters parameters) {
        dropDatabase = parameters.getUnnamed().contains("dropDatabase");
    }



    private static boolean dropDatabase;

    private Parameters() { }
}
