package vilnius.tech.utils;

import javafx.application.Application;

public class Parameters {

    public static boolean getDropDatabase() {
        return dropDatabase;
    }

    public static boolean getAutoConnect() {
        return autoConnect;
    }

    public static void initialize(Application.Parameters parameters) {
        dropDatabase = parameters.getUnnamed().contains("dropDatabase");
        autoConnect = parameters.getUnnamed().contains("autoConnect");
    }



    private static boolean dropDatabase;

    private static boolean autoConnect;

    private Parameters() { }
}
