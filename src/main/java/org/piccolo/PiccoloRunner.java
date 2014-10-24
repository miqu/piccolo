package org.piccolo;
/**
 * Piccolo an accesscontrol system in Java for different platforms.
 */
import com.pi4j.io.gpio.*;

public class PiccoloRunner implements Runnable{

    // Method for setting up the environment on the PI for running the Piccolo
    public void setup(){
        //Setup the reader

        //Setup the Led connection
        //Setup the lock
        //Setup the Beeper
    }

    public static void main(String ... args){
        // create gpio controller instance
        final GpioController gpio = GpioFactory.getInstance();
        try {
	        // provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
	        GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
	                "My LED",           // PIN FRIENDLY NAME (optional)
	                PinState.LOW);      // PIN STARTUP STATE (optional)
	        blink(myLed);
        } finally {
        	gpio.shutdown();
        }
    }


    private static void blink(GpioPinDigitalOutput myLed) {
        for (int i=0;i<10;i++) {
            myLed.pulse(1000);

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {

    }
}
