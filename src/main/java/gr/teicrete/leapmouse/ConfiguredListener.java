package gr.teicrete.leapmouse;

/**
 * Created by Anestis Vlasopoulos on 8/12/2016.
 *
 */
import com.leapmotion.leap.*;
import com.leapmotion.leap.Frame;

import java.awt.*;
import java.awt.event.InputEvent;

public class ConfiguredListener extends Listener{

    private Robot robot;

    /**
     *  Enable the gestures we want to utilize
     *  after the Controller object connects to the Leap Motion software and the Leap Motion hardware device is plugged in,
     *  or when this Listener object is added to a Controller that is already connected
     *
     * @param controller
     */
    public void onConnect(Controller controller) {
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }

    /**
     *  All the business logic of the application is here.
     *  This method is called every time a new Frame of hand and finger is available
     *
     * @param controller
     */
    public void onFrame(Controller controller) {
        initializeRobot();
        Frame frame = controller.frame();
        InteractionBox box = frame.interactionBox();

        for (Finger f : frame.fingers()) {
            if (f.type() == Finger.Type.TYPE_INDEX) {
                Vector fingerPosition = f.stabilizedTipPosition();
                Vector fingerPositionBox = box.normalizePoint(fingerPosition);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                robot.mouseMove((int)(screenSize.width * fingerPositionBox.getX()), (int)(screenSize.height * fingerPositionBox.getY()));
            }
        }

        for (Gesture g : frame.gestures()) {
            if (g.type() == Gesture.Type.TYPE_CIRCLE) {
                CircleGesture c = new CircleGesture();
                //check if the circle is clockwise
                if (c.pointable().direction().angleTo(c.normal()) <= Math.PI/4) {
                    robot.mouseWheel(1);
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else /*check if the cycle is counterclockwise*/ {
                    robot.mouseWheel(1);
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if (g.type() == Gesture.Type.TYPE_SCREEN_TAP) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
        }
    }


    /**
     * Initializes the Robot Object.
     * Needs to be included in a try catch statement
     *
     */
    private void initializeRobot() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
