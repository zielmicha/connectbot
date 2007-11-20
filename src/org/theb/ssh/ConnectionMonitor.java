package org.theb.ssh;

public interface ConnectionMonitor {
	public void connectionLost(Throwable reason);
}
