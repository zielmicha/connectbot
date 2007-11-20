package org.theb.ssh;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class ConnectionThread extends Thread {
	public ConnectionThread(FeedbackUI ui, String hostname, String username, int port) {}
	public abstract void finish();
	public abstract InputStream getReader();
	public abstract OutputStream getWriter();
	public abstract void setPassword(String password);
}
