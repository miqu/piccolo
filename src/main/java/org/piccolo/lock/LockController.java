package org.piccolo.lock;

import com.pi4j.io.gpio.GpioPin;

/**
 * A controller for opening the lock
 */
public interface LockController {

    /**
     * Opens the the lock connected to the pin given as parameter.
     * @param gpioPinToOpen The pin of the lockcontrol
     */
    public void openLock(GpioPin gpioPinToOpen);
}
