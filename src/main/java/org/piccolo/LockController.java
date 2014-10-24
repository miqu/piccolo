package org.piccolo;

/**
 * Created by mrt on 24.10.2014.
 */
public class LockController implements FeedbackController {

	public void onSuccess() {
		// TODO open lock
		System.out.println("Welcome!");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
		System.out.println("Door locked again");
	}

	public void onFail() {
		// do nothing
		System.out.println("Who's there?!");
	}
}
