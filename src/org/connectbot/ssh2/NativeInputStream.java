/**
 *
 */
package org.connectbot.ssh2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/**
 * @author Kenny Root
 *
 */
public class NativeInputStream extends InputStream {
	private WeakReference<Channel> channelRef;
	private int type;

	NativeInputStream(Channel channel, int type) {
		channelRef = new WeakReference<Channel>(channel);
		this.type = type;
	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int available() throws IOException {
		Channel channel = channelRef.get();
		if (channel == null)
			throw new IOException("Channel has gone away");

		return native_available(channel);
	}

	@Override
	public void close() throws IOException { }

	@Override
	public boolean markSupported() {
		return false;
	}

	@Override
	public int read(byte[] b, int offset, int length) throws IOException {
		Channel channel = channelRef.get();
		if (channel == null)
			throw new IOException("Channel has gone away");

		return native_read(channel, type, b, offset, length);
	}

	public int read(ByteBuffer b, int offset, int length) throws IOException {
		Channel channel = channelRef.get();
		if (channel == null)
			throw new IOException("Channel has gone away");

		return native_readByteBuffer(channel, type, b, offset, length);
	}

	@Override
	public int read(byte[] b) throws IOException {
		return read(b, 0, b.length);
	}

	@Override
	public synchronized void reset() throws IOException { }

	private native int native_read(Object channel, int type, byte[] b, int offset, int length) throws IOException;
	private native int native_readByteBuffer(Channel channel, int type, ByteBuffer b, int offset, int length) throws IOException;
	private native int native_available(Object channel);
}
