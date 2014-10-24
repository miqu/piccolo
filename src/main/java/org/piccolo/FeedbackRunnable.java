package org.piccolo;

public class FeedbackRunnable implements Runnable {
	private FeedbackController feedbackController;
	private boolean authorized;

	FeedbackRunnable(FeedbackController feedbackController, boolean authorized) {
		this.feedbackController = feedbackController;
		this.authorized = authorized;
	}

	@Override
	public void run() {
		if (authorized) {
			feedbackController.onSuccess();
		} else {
			feedbackController.onFail();
		}
	}

}
