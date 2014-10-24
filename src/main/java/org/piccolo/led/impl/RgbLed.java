package org.piccolo.led.impl;

import com.pi4j.io.gpio.*;

import org.piccolo.led.LedController;

/**
 * Created by siim on 24/10/14.
 */
public class RgbLed implements LedController{

    private GpioController gpio;
    private GpioPinDigitalOutput greenPin;
    private GpioPinDigitalOutput redPin;
    private GpioPinDigitalOutput bluePin;

    public RgbLed(GpioController controller, Pin red, Pin green, Pin blue){
        gpio = controller;

        greenPin = gpio.provisionDigitalOutputPin(green, "green", PinState.LOW);
        redPin = gpio.provisionDigitalOutputPin(red, "red", PinState.LOW);
        bluePin = gpio.provisionDigitalOutputPin(blue, "blue", PinState.LOW);
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

}
