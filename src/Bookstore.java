import controller.Controller;
import sdk.Config;

/**
 * Created by Christofferpalsgaard on 24/11/2016.
 */
public class Bookstore {
    public static void main(String[] args) {
        Config.initConfig();

        new Controller().mainMenu();

    }
}
