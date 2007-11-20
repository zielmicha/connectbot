package org.theb.ssh;

import java.io.InputStream;
import java.io.OutputStream;

public interface Terminal {
	public int getWidth();
	public int getHeight();
	public int getRowCount();
	public int getColumnCount();
	public void start(Object session);
	public InputStream getInput();
	public OutputStream getOutput();
	public byte[] getKeyCode(int keyCode);
}
