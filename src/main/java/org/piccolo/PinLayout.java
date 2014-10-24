package org.piccolo;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Pin layout for Raspberry Pi.
 */
public enum PinLayout {
	RED_LED(RaspiPin.GPIO_00),
	GREEN_LED(RaspiPin.GPIO_01),
	BLUE_LED(RaspiPin.GPIO_02), 
	UNLOCK(RaspiPin.GPIO_03),
	BEEP(RaspiPin.GPIO_04);
	
	private PinLayout(Pin pin) {
		this.pin = pin;
	}

	public final Pin pin;
}
