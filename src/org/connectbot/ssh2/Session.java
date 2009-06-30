/**
 *
 */
package org.connectbot.ssh2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Kenny Root
 *
 */
public class Session {
	static {
		System.loadLibrary("cbssh2");
	}

	public static final int CHANNEL_TYPE_SESSION = 1;
	public static final int CHANNEL_TYPE_DIRECT_TCPIP = 2;
	public static final int CHANNEL_TYPE_FORWARD_LISTEN = 3;

	public final static int METHOD_KEX = 0;
	public final static int METHOD_HOSTKEY = 1;
	public final static int METHOD_CRYPT_CS = 2;
	public final static int METHOD_CRYPT_SC = 3;
	public final static int METHOD_MAC_CS = 4;
	public final static int METHOD_MAC_SC = 5;
	public final static int METHOD_COMP_CS = 6;
	public final static int METHOD_COMP_SC = 7;
	public final static int METHOD_LANG_CS = 8;
	public final static int METHOD_LANG_SC = 9;

	@SuppressWarnings("unused")
	private int mNativeContext = 0;

	private String hostname;
	private int port;
	private final Socket sock = new Socket();

	private ConnectionInfo mConnectionInfo = null;

	public Session(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;

		setup();
	}

	public native void setBanner(String banner);

	public boolean connect() throws UnknownHostException, IOException {
		return connect(0);
	}

	public boolean connect(int connectTimeout) throws UnknownHostException, IOException {
		InetAddress addr = InetAddress.getByName(hostname);
		sock.connect(new InetSocketAddress(addr, port), connectTimeout);

		return startup(sock);
	}

	public ConnectionInfo getConnectionInfo() {
		if (mConnectionInfo == null) {
			mConnectionInfo = new ConnectionInfo();
			getMethods(mConnectionInfo);
		}
		return mConnectionInfo;
	}

	public native byte[] getFingerprint();

	public native String getAuthenticationMethods(String username);

	public native boolean authenticatePassword(String username, String password);

	public native boolean isAuthenticated();

	public Channel openSession() {
		Channel channel = new Channel();
		native_openSession(channel);
		return channel;
	}

	public native boolean close();

	@Override
	protected void finalize() {
		release();
	}

	private native final void setup();

	private native boolean startup(Object socket);

	private native void getMethods(Object connectionInfo);

	private native void native_openSession(Object channel);

	private native final void release();
}
