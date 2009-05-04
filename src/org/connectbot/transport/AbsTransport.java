/**
 *
 */
package org.connectbot.transport;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.connectbot.bean.HostBean;
import org.connectbot.bean.PortForwardBean;
import org.connectbot.service.TerminalBridge;
import org.connectbot.service.TerminalManager;

/**
 * @author Kenny Root
 *
 */
public abstract class AbsTransport {
	HostBean host;
	TerminalBridge bridge;
	TerminalManager manager;

	String emulation;

	public AbsTransport() {}

	public AbsTransport(HostBean host, TerminalBridge bridge, TerminalManager manager) {
		this.host = host;
		this.bridge = bridge;
		this.manager = manager;
	}

	/**
	 * @return protocol part of the URI
	 */
	public static String getProtocolName() {
		return null;
	}

	public abstract void connect();
	public abstract int read(byte[] buffer, int start, int len) throws IOException;
	public abstract void write(byte[] buffer) throws IOException;
	public abstract void write(int c) throws IOException;
	public abstract void flush() throws IOException;
	public abstract void close();

	public abstract void setDimensions(int columns, int rows, int width, int height);

	public void setOptions(Map<String,String> options) {
		// do nothing
	}
	public Map<String,String> getOptions() {
		return null;
	}

	public void setCompression(boolean compression) {
		// do nothing
	}

	public void setEmulation(String emulation) {
		this.emulation = emulation;
	}

	public String getEmulation() {
		return emulation;
	}

	public void setHost(HostBean host) {
		this.host = host;
	}

	public void setBridge(TerminalBridge bridge) {
		this.bridge = bridge;
	}

	public void setManager(TerminalManager manager) {
		this.manager = manager;
	}

	/**
	 * Whether or not this transport type can forward ports.
	 * @return true on ability to forward ports
	 */
	public boolean canForwardPorts() {
		return false;
	}

	/**
	 * Adds the {@link PortForwardBean} to the list.
	 * @param portForward the port forward bean to add
	 * @return true on successful addition
	 */
	public boolean addPortForward(PortForwardBean portForward) {
		return false;
	}

	/**
	 * Enables a port forward member. After calling this method, the port forward should
	 * be operational iff it could be enabled by the transport.
	 * @param portForward member of our current port forwards list to enable
	 * @return true on successful port forward setup
	 */
	public boolean enablePortForward(PortForwardBean portForward) {
		return false;
	}

	/**
	 * Disables a port forward member. After calling this method, the port forward should
	 * be non-functioning iff it could be disabled by the transport.
	 * @param portForward member of our current port forwards list to enable
	 * @return true on successful port forward tear-down
	 */
	public boolean disablePortForward(PortForwardBean portForward) {
		return false;
	}

	/**
	 * Removes the {@link PortForwardBean} from the available port forwards.
	 * @param portForward the port forward bean to remove
	 * @return true on successful removal
	 */
	public boolean removePortForward(PortForwardBean portForward) {
		return false;
	}

	/**
	 * @return the list of port forwards
	 */
	public List<PortForwardBean> getPortForwards() {
		return null;
	}

	public abstract boolean isConnected();
	public abstract boolean isSessionOpen();

	/**
	 * @return int default port for protocol
	 */
	public abstract int getDefaultPort();

	/**
	 * @param username
	 * @param hostname
	 * @param port
	 * @return
	 */
	public abstract String getDefaultNickname(String username, String hostname, int port);
}
