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
        redPin = gpio.provisionDigitalOutputPin(blue, "red", PinState.LOW);
        bluePin = gpio.provisionDigitalOutputPin(red, "blue", PinState.LOW);
    }

    @Override
    public void on() {
        greenPin.setState(PinState.HIGH);
    }

    @Override
    public void off() {
        greenPin.setState(PinState.LOW);
    }


}
