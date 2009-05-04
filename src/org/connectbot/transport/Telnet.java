/**
 *
 */
package org.connectbot.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.connectbot.bean.HostBean;
import org.connectbot.service.TerminalBridge;
import org.connectbot.service.TerminalManager;

import android.util.Log;
import de.mud.telnet.TelnetProtocolHandler;

/**
 * Telnet implementation borrowed from the JTA package.
 *
 * @author  Matthias L. Jugel, Marcus Meißner, Kenny Root
 *
 */
public class Telnet extends AbsTransport {

	private static final String TAG = "ConnectBot.Telnet";
	private static final String PROTOCOL = "telnet";

	private TelnetProtocolHandler handler;
	private Socket socket;

	private InputStream is;
	private OutputStream os;
	private int width;
	private int height;

	private boolean connected = false;

	/**
	 *
	 */
	public Telnet() {
		handler = new TelnetProtocolHandler() {
			/** get the current terminal type */
			@Override
			public String getTerminalType() {
				return getEmulation();
			}

			/** get the current window size */
			@Override
			public int[] getWindowSize() {
				return new int[] { width, height };
			}

			/** notify about local echo */
			@Override
			public void setLocalEcho(boolean echo) {
				/* EMPTY */
			}

			/** write data to our back end */
			@Override
			public void write(byte[] b) throws IOException {
				os.write(b);
			}

			/** sent on IAC EOR (prompt terminator for remote access systems). */
			@Override
			public void notifyEndOfRecord() {
			}
		};
	}

	/**
	 * @param host
	 * @param bridge
	 * @param manager
	 */
	public Telnet(HostBean host, TerminalBridge bridge, TerminalManager manager) {
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
		if (socket != null)
			try {
				socket.close();
			} catch (IOException e) {
				Log.d(TAG, "Error closing telnet socket.", e);
			}
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#connect()
	 */
	@Override
	public void connect() {
		try {
			socket = new Socket(host.getHostname(), host.getPort());

			connected = true;

			is = socket.getInputStream();
			os = socket.getOutputStream();

			bridge.onConnected();
		} catch (UnknownHostException e) {
			Log.d(TAG, "IO Exception connecting to host", e);
		} catch (IOException e) {
			Log.d(TAG, "IO Exception connecting to host", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#flush()
	 */
	@Override
	public void flush() throws IOException {
		os.flush();
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#getDefaultPort()
	 */
	@Override
	public int getDefaultPort() {
		return 23;
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#isConnected()
	 */
	@Override
	public boolean isConnected() {
		return connected ;
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#isSessionOpen()
	 */
	@Override
	public boolean isSessionOpen() {
		return connected;
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#read(byte[], int, int)
	 */
	@Override
	public int read(byte[] buffer, int start, int len) throws IOException {
		/* process all already read bytes */
		int n = 0;

		do {
			n = handler.negotiate(buffer, start);
			if (n > 0)
				return start + n;
		} while (n == 0);

		while (n <= 0) {
			do {
				n = handler.negotiate(buffer, start);
				if (n > 0)
					return n;
			} while (n == 0);
			n = is.read(buffer, start, len);
			if (n < 0) {
				bridge.dispatchDisconnect(false);
				throw new IOException("Remote end closed connection.");
			}

			handler.inputfeed(buffer, start, n - start);
			n = handler.negotiate(buffer, start);
		}
		return n;
	}

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#setDimensions(int, int, int, int)
	 */
	@Override
	public void setDimensions(int columns, int rows, int width, int height) {
		// TODO Auto-generated method stub

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

	/* (non-Javadoc)
	 * @see org.connectbot.transport.AbsTransport#getDefaultNickname(java.lang.String, java.lang.String, int)
	 */
	@Override
	public String getDefaultNickname(String username, String hostname, int port) {
		if (port == 23) {
			return String.format("%s", hostname);
		} else {
			return String.format("%s:%d", hostname, port);
		}
	}
}
