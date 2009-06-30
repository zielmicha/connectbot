/**
 *
 */
package org.connectbot.ssh2;

/**
 * @author Kenny Root
 *
 */
public class ConnectionInfo {
	ConnectionInfo() { }

	public String kex;
	public String hostKey;
	public String c2sCrypto;
	public String s2cCrypto;
	public String c2sMAC;
	public String s2cMAC;
	public String c2sComp;
	public String s2cComp;
	public String c2sLang;
	public String s2cLang;
}
