package org.piccolo;
/**
 * Piccolo an accesscontrol system in Java for different platforms.
 */
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import com.pi4j.io.gpio.*;

import org.piccolo.auth.AuthorizationController;
import org.piccolo.auth.impl.AuthorizationControllerImpl;
import org.piccolo.led.LedController;
import org.piccolo.led.impl.RgbLed;

public class PiccoloRunner {
	
	private GpioController gpio;
	private ReaderController readerController;
	private AuthorizationController authorizationController;
	private Collection<FeedbackController> feedbackControllers;

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
    	feedbackControllers = new LinkedList<FeedbackController>();
        //Setup the Led connection
    	feedbackControllers.add(new RgbLed(gpio, RaspiPin.GPIO_00, RaspiPin.GPIO_01, RaspiPin.GPIO_02));
        //Setup the lock
    	feedbackControllers.add(new LockController());
        //Setup the Beeper
    }
    
    void cleanup() {
    	if (gpio != null) {
    		gpio.shutdown();
    	}
    }

    void run() {
		Collection<Thread> feedbackThreads = new LinkedList<Thread>();
    	for (int i = 0; i < 3; i++) {
    		try {
    			String id = readerController.readId();
    			boolean authorized = authorizationController.requestAccess(id);
    			for (FeedbackController feedbackController : feedbackControllers) {
    				Thread feedbackThread = new Thread(new FeedbackRunnable(feedbackController, authorized));
    				feedbackThreads.add(feedbackThread);
    				feedbackThread.start();
    			}
    			for (Thread feedbackThread : feedbackThreads) {
    				try {
    					feedbackThread.join();
    				} catch (InterruptedException interruptedException) {
    					interruptedException.printStackTrace();
    				}
    			}
    			feedbackThreads.clear();
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
