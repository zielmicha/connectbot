/**
 *
 */
package org.connectbot.transport;


/**
 * @author Kenny Root
 *
 */
public class TransportFactory {
	private static String[] transportNames = {
		SSH.getProtocolName(),
		Telnet.getProtocolName(),
		Local.getProtocolName(),
	};

	/**
	 * @param protocol
	 * @return
	 */
	public static AbsTransport getTransport(String protocol) {
		if (SSH.getProtocolName().equals(protocol)) {
			return new SSH();
		} else if (Telnet.getProtocolName().equals(protocol)) {
			return new Telnet();
		} else if (Local.getProtocolName().equals(protocol)) {
			return new Local();
		} else {
			return null;
		}
	}

	public static String[] getTransportNames() {
		return transportNames;
	}
}
