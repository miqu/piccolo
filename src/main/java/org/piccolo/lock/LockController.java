package org.piccolo.lock;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

import org.piccolo.FeedbackController;
import org.piccolo.PinLayout;

/**
 * Created by mrt on 24.10.2014.
 */
public class LockController implements FeedbackController {

    private GpioPinDigitalOutput unlockPin;

    public LockController(GpioController gpio){
        unlockPin = gpio.provisionDigitalOutputPin(PinLayout.UNLOCK.pin, "unlockPin", PinState.LOW);
    }

    private void unlock(long timeInMillis){
        unlockPin.blink(timeInMillis);
    }

	public void onSuccess() {
		unlock(5000);
	}

	public void onFail() {
		// do nothing
	}
}
