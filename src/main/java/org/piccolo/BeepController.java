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

    public BeepController(GpioController gpio, Pin buzzerPin){
        buzzer = gpio.provisionDigitalOutputPin(buzzerPin, "buzzerPin", PinState.LOW);
    }
	public void onSuccess() {

        try {
            buzzer.high();
            Thread.sleep(3000);
            buzzer.low();
        } catch (InterruptedException e) {
            e.printStackTrace();
            buzzer.low();
        }

	}

	public void onFail() {
        try {
            buzzer.high();
            Thread.sleep(200);
            buzzer.low();
            Thread.sleep(300);
            buzzer.high();
            Thread.sleep(150);
            buzzer.low();
            Thread.sleep(100);
            buzzer.high();
            Thread.sleep(150);
            buzzer.low();
            Thread.sleep(300);
            buzzer.high();
            Thread.sleep(200);
            buzzer.low();
            Thread.sleep(200);
            buzzer.high();
            Thread.sleep(200);
            buzzer.low();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
}
