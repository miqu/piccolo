package org.piccolo.led;

import org.piccolo.FeedbackController;
import org.piccolo.led.impl.LedColor;

/**
 * Created by mrt on 24.10.2014.
 */
public interface LedController extends FeedbackController {

    void on(LedColor color);
    void off();
}
