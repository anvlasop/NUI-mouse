package gr.teicrete.leapmouse;

/**
 * Created by Anestis Vlasopoulos on 8/12/2016.
 */

import com.leapmotion.leap.*;


public class Main{

    public static void main (String[] args)
    {
        ConfiguredListener listener = new ConfiguredListener();
        Controller controller = new Controller();
        controller.addListener(listener);

        try
        {
            System.in.read();
        } catch (Exception e)
        {
            System.out.printf(e.getMessage());
        }

        controller.removeListener(listener);

    }
}
