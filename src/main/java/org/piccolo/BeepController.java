package org.piccolo;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

/**
 * Operates the buzzer. on Pin 4
 */
public class BeepController implements FeedbackController {


    private final GpioPinDigitalOutput buzzer;

    public BeepController(GpioController gpio){
        buzzer = gpio.provisionDigitalOutputPin(PinLayout.BEEP.pin, "buzzerPin", PinState.LOW);
    }
	public void onSuccess() {
        buzzer.high();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            buzzer.low();
        }
	}

	public void onFail() {
        deniedSound(buzzer);
    }

    private void deniedSound(GpioPinDigitalOutput buzzer){
        int [] sleeptimes = {200,300,150,100,150,300,200,200,200};
        try {
        for(int sleeptime = 0; sleeptime < sleeptimes.length ; sleeptime++){

            if(sleeptime % 2==0) {
                buzzer.high();
            }
            else {
                buzzer.low();
            }
            Thread.sleep(sleeptimes[sleeptime]);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            buzzer.low();
        }
    }
}
