package org.theb.ssh;

import android.os.Handler;

public interface FeedbackUI {
	public void setWaiting(boolean isWaiting, String title, String message);
	public Handler getHandler();
	public void askPassword();
}
