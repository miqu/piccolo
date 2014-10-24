package org.piccolo;
/**
 * Piccolo an accesscontrol system in Java for different platforms.
 */
import com.pi4j.io.gpio.*;

public class PiccoloRunner implements Runnable{

    GpioPinDigitalOutput myLed;
    GpioPinDigitalOutput lock;
    GpioPinDigitalOutput beeper;



    // Method for setting up the environment on the PI for running the Piccolo
    public void setup(){

        final GpioController gpio = GpioFactory.getInstance();
        //Setup the reader

        //Setup the Led connection
        // provision gpio pins #04 as an output pin and make sure is is set to LOW at startup
        myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
                "My LED",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)
        //Setup the lock
        lock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,   // PIN NUMBER
                "Lock control",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)
        //Setup the Beeper
    }

    public static void main(String ... args){
        // create gpio controller instance
       PiccoloRunner pr= new PiccoloRunner();
        pr.setup();
        pr.run();
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
        if(myLed==null) {
            throw new IllegalStateException("Lediä ei ole alustettu aja setup ennen kuin ajat varsinaista luokkaa");
        }
        blink(myLed);
    }
}
