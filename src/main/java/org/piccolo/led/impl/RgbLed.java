package org.piccolo.led.impl;

import com.pi4j.io.gpio.*;

import org.piccolo.PinLayout;
import org.piccolo.led.LedController;

/**
 * Created by siim on 24/10/14.
 */
public class RgbLed implements LedController{

    private GpioController gpio;
    private GpioPinDigitalOutput greenPin;
    private GpioPinDigitalOutput redPin;
    private GpioPinDigitalOutput bluePin;

    public RgbLed(GpioController controller){
        gpio = controller;

        greenPin = gpio.provisionDigitalOutputPin(PinLayout.GREEN_LED.pin, "green", PinState.LOW);
        redPin = gpio.provisionDigitalOutputPin(PinLayout.RED_LED.pin, "red", PinState.LOW);
        bluePin = gpio.provisionDigitalOutputPin(PinLayout.BLUE_LED.pin, "blue", PinState.LOW);
    }

    @Override
    public void on(LedColor color) {
        switch (color){
            case blue:
                bluePin.setState(true);
                break;
            case red:
                redPin.setState(true);
                break;
            case green:
                greenPin.setState(true);
                break;
        }
    }

    @Override
    public void off() {
        greenPin.setState(false);
        redPin.setState(false);
        bluePin.setState(false);
    }

    public void blink(LedColor color, long timeInMillis){
        try {
            on(color);
            Thread.sleep(timeInMillis);
            off();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void onSuccess() {
		blink(LedColor.green, 5000);
	}

	@Override
	public void onFail() {
        blink(LedColor.red, 5000);
	}

    private void blink(LedColor ledColor) {
        for (int i=0;i<10;i++) {
            on(ledColor);

            try {
                Thread.sleep(1500);
                off();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
