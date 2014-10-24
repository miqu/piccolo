package org.piccolo.infoLed.impl;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.piccolo.infoLed.LedController;

/**
 * Created by siim on 24/10/14.
 */
public class RgbLed implements LedController{

    private GpioController gpio;
    private GpioPinDigitalOutput greenPin;
    private GpioPinDigitalOutput

    RgbLed(GpioController controller){
        gpio = controller;

        GpioPinDigitalOutput greenPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "green", PinState.LOW);

    }

    @Override
    public void on() {

    }

    @Override
    public void off() {

    }


}
