package org.piccolo;
/**
 * Piccolo an accesscontrol system in Java for different platforms.
 */
import com.pi4j.io.gpio.*;
import org.piccolo.infoLed.LedController;
import org.piccolo.infoLed.impl.RgbLed;

public class PiccoloRunner implements Runnable{

    // Method for setting up the environment on the PI for running the Piccolo
    public void setup(){
        //Setup the reader

        //Setup the Led connection
        //Setup the lock
        //Setup the Beeper
    }

    public static void main(String ... args){
        runLedController();
    }


    public void run() {

    }

    public static void runLedController(){
        // create gpio controller instance
        final GpioController gpio = GpioFactory.getInstance();

        try {
            //create ledController
            LedController controller = new RgbLed(gpio, RaspiPin.GPIO_00, RaspiPin.GPIO_01, RaspiPin.GPIO_02);
        } finally {
            gpio.shutdown();
        }
    }

    private static void blink(LedController ledController) {
        for (int i=0;i<10;i++) {
            ledController.on();

            try {
                Thread.sleep(1500);
                ledController.off();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
