/**
 *
 */
package org.connectbot.ssh2;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

/**
 * @author kenny
 *
 */
public class NativeOutputStream extends OutputStream {
	private WeakReference<Channel> channelRef;
	private byte[] buffer;

	NativeOutputStream(Channel channel) {
		channelRef = new WeakReference<Channel>(channel);
		buffer = new byte[1];
	}

	@Override
	public void write(int oneByte) throws IOException {
		synchronized (buffer) {
			buffer[0] = (byte)oneByte;
			write(buffer, 0, 1);
		}
	}

	@Override
	public void close() throws IOException { }

	@Override
	public void flush() throws IOException { }

	@Override
	public void write(byte[] buffer, int offset, int count) throws IOException {
		Channel channel = channelRef.get();
		if (channel == null)
			throw new IOException("Channel has gone away");

		native_write(channel, buffer, offset, count);
	}

	@Override
	public void write(byte[] buffer) throws IOException {
		write(buffer, 0, buffer.length);
	}

	private native int native_write(Object channel, byte[] buffer, int offset, int count);
}
