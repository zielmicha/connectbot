/**
 *
 */
package org.connectbot.ssh2;

import android.util.Log;

/**
 * @author Kenny Root
 *
 */
public class Channel {
	private final static String TAG = "ConnectBot.ssh2.Channel";

	@SuppressWarnings("unused")
	private int mNativeContext = 0;

	private boolean hasPty = false;
	private NativeOutputStream stdin = null;
	private NativeInputStream stdout = null;
	private NativeInputStream stderr = null;

	Channel() { }

	public native boolean setEnv(String varname, String value);

	public boolean requestPTY(String emulation, int cols, int rows, int width, int height) {
		boolean result = native_requestPTY(emulation, cols, rows, width, height);

		if (result)
			hasPty = true;

		Log.d(TAG, "Successfully requested a PTY");
		return result;
	}

	public boolean openShell() {
		if (!hasPty)
			throw new IllegalStateException("PTY has not been requested");

		return native_openShell();
	}

	public NativeInputStream getStdout() {
		if (!hasPty)
			throw new IllegalStateException("PTY has not been requested");

		if (stdout == null) {
			stdout = new NativeInputStream(this, 0);
		}
		return stdout;
	}

	public NativeInputStream getStderr() {
		if (!hasPty)
			throw new IllegalStateException("PTY has not been requested");

		if (stderr == null) {
			stderr = new NativeInputStream(this, 1);
		}
		return stderr;
	}

	public NativeOutputStream getStdin() {
		if (!hasPty)
			throw new IllegalStateException("PTY has not been requested");

		if (stdin == null) {
			stdin = new NativeOutputStream(this);
		}
		return stdin;
	}

	@Override
	protected void finalize() {
		release();
	}

	private native final void release();

	private native final boolean native_requestPTY(String emulation, int cols, int rows, int width, int height);

	private native final boolean native_openShell();
}
