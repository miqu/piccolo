package org.piccolo;
/**
 * Piccolo an accesscontrol system in Java for different platforms.
 */
import java.io.IOException;

import com.pi4j.io.gpio.*;

import org.piccolo.Authorization.AuthorizationController;
import org.piccolo.Authorization.impl.AuthorizationControllerImpl;
import org.piccolo.infoLed.LedController;
import org.piccolo.infoLed.impl.RgbLed;

public class PiccoloRunner {
	
	private GpioController gpio;
	private ReaderController readerController;
	private AuthorizationController authorizationController;

    public static void main(String ... args){
    	PiccoloRunner piccoloRunner= new PiccoloRunner();
    	try {
    		piccoloRunner.setup();
    		piccoloRunner.run();
    	} finally {
    		piccoloRunner.cleanup();
    	}
    }

    // Method for setting up the environment on the PI for running the Piccolo
    void setup(){
    	gpio = GpioFactory.getInstance();
        //Setup the reader
    	readerController = new ReaderController();
    	//Setup authorization
    	authorizationController = new AuthorizationControllerImpl();
        //Setup the Led connection
        //Setup the lock
        //Setup the Beeper
    }
    
    void cleanup() {
    	if (gpio != null) {
    		gpio.shutdown();
    	}
    }

    void run() {
    	for (int i = 0; i < 3; i++) {
    		try {
    			String id = readerController.readId();
    			if (authorizationController.requestAccess(id)) {
    				runLedController();
    			} else {
    				runLedController();
    			}
    		} catch (IOException ioException) {
    			ioException.printStackTrace();
    		}
    	}
    }

    void runLedController(){
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
