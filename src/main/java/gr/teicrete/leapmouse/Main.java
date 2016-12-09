package gr.teicrete.leapmouse;

/**
 * Created by Anestis Vlasopoulos on 8/12/2016.
 *
 * This is the entry point of the application
 */

import com.leapmotion.leap.*;


public class Main {

    public static void main (String[] args) {
        ConfiguredListener listener = new ConfiguredListener();
        Controller controller = new Controller();
        controller.addListener(listener);

        try {
            System.in.read();
        } catch (Exception e) {
            System.out.printf("Exception occured: " + e.getMessage());
        }

        controller.removeListener(listener);
    }
}
