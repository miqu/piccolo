package org.piccolo.led;

import org.piccolo.FeedbackController;

/**
 * Created by mrt on 24.10.2014.
 */
public interface LedController extends FeedbackController {

    void on();
    void off();
}
