/**
 *
 */
package org.connectbot.transport;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.connectbot.bean.HostBean;
import org.connectbot.service.TerminalBridge;
import org.connectbot.service.TerminalManager;

import android.R;
import android.util.Log;

/**
 * @author Kenny Root
 *
 */
public class Local extends AbsTransport {
	private static final String TAG = "ConnectBot.Local";
	private static final String PROTOCOL = "local";

	private static Method mExec_openSubprocess;
	private static Method mExec_waitFor;
	private static Method mExec_setPtyWindowSize;

	private FileDescriptor shellFd;

	private FileInputStream is;
	private FileOutputStream os;

	static {
		initPrivateAPI();
	}

	private static void initPrivateAPI() {
		try {
			Class<?> mExec = Class.forName("android.os.Exec");
			mExec_openSubprocess = mExec.getMethod("createSubprocess",
					String.class, String.class, String.class, int[].class);
			mExec_waitFor = mExec.getMethod("waitFor", int.class);
			mExec_setPtyWindowSize = mExec.getMethod("setPtyWindowSize",
					FileDescriptor.class, int.class, int.class, int.class, int.class);
		} catch (NoSuchMethodException e) {
			// Give up
		} catch (ClassNotFoundException e) {
			// Give up
		}
	}

	/**
	 *
	 */
	public Local() {
	}

	/**
	 * @param host
	 * @param bridge
	 * @param manager
	 */
	public Local(HostBean host, TerminalBridge bridge, TerminalManager manager) {
		super(host, bridge, manager);
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#getProtocol()
	 */
	public static String getProtocolName() {
		return PROTOCOL;
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#close()
	 */
	@Override
	public void close() {
		try {
			os.close();
			is.close();
			os = null;
			is = null;
		} catch (IOException e) {
			Log.e(TAG, "Couldn't close shell", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#connect()
	 */
	@Override
	public void connect() {
		int[] pids = new int[1];
			try {
				shellFd = (FileDescriptor)mExec_openSubprocess.invoke(null, "/system/bin/sh", "-", null, pids);
			} catch (Exception e) {
				bridge.outputLine(R.string.local_shell_unavailable);
				return;
			}

			final int shellPid = pids[0];
			Runnable exitWatcher = new Runnable() {
				public void run() {
					try {
						mExec_waitFor.invoke(null, shellPid);
					} catch (Exception e) {
						Log.e(TAG, "Couldn't wait for shell exit", e);
					}

					bridge.dispatchDisconnect(false);
				}
			};
			new Thread(exitWatcher).start();

			is = new FileInputStream(shellFd);
			os = new FileOutputStream(shellFd);

			bridge.onConnected();
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#flush()
	 */
	@Override
	public void flush() throws IOException {
		os.flush();
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#getDefaultNickname(java.lang.String, java.lang.String, int)
	 */
	@Override
	public String getDefaultNickname(String username, String hostname, int port) {
		return "local://";
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#getDefaultPort()
	 */
	@Override
	public int getDefaultPort() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#isConnected()
	 */
	@Override
	public boolean isConnected() {
		return is != null && os != null;
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#isSessionOpen()
	 */
	@Override
	public boolean isSessionOpen() {
		return is != null && os != null;
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#read(byte[], int, int)
	 */
	@Override
	public int read(byte[] buffer, int start, int len) throws IOException {
		return is.read(buffer, start, len);
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#setDimensions(int, int, int, int)
	 */
	@Override
	public void setDimensions(int columns, int rows, int width, int height) {
		try {
			mExec_setPtyWindowSize.invoke(null, shellFd, columns, rows, width, height);
		} catch (Exception e) {
			Log.e(TAG, "Couldn't resize pty", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#write(byte[])
	 */
	@Override
	public void write(byte[] buffer) throws IOException {
		os.write(buffer);
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#write(int)
	 */
	@Override
	public void write(int c) throws IOException {
		os.write(c);
	}

}
