package org.theb.ssh;

import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.view.View;

public abstract class TerminalView extends View {
	public TerminalView(Context context) { super(context); }
	public abstract int getRowCount();
	public abstract int getColumnCount();
	public abstract void start(Object session);
	public abstract InputStream getInput();
	public abstract OutputStream getOutput();
	public abstract byte[] getKeyCode(int keyCode);
}
