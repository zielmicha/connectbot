/*
	ConnectBot: simple, powerful, open-source SSH client for Android
	Copyright (C) 2007-2008 Kenny Root, Jeffrey Sharkey

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.connectbot.util;

import com.jcraft.jsch.Util;

/**
 * @author Kenny Root
 *
 */
public class XmlBuilder {
	private StringBuilder sb;

	public XmlBuilder() {
		sb = new StringBuilder();
	}

	public XmlBuilder append(String data) {
		sb.append(data);

		return this;
	}

	public XmlBuilder append(String field, Object data) {
		if (data == null) {
			sb.append(String.format("<%s/>", field));
		} else if (data instanceof String) {
			String input = (String) data;
			boolean binary = false;

			for (byte b : input.getBytes()) {
				if (b < 0x20 || b > 0x7e) {
					binary = true;
					break;
				}
			}

			if (binary) {
				byte[] val = input.getBytes();
				input = new String(Util.toBase64(val, 0, val.length));
			}
			sb.append(String.format("<%s>%s</%s>", field, input, field));
		} else if (data instanceof Integer) {
			sb.append(String.format("<%s>%d</%s>", field, data, field));
		} else if (data instanceof Long) {
			sb.append(String.format("<%s>%d</%s>", field, data, field));
		} else if (data instanceof byte[]) {
			byte[] val = (byte[]) data;
			sb.append(String.format("<%s>%s</%s>", field, new String(Util.toBase64(val, 0, val.length)), field));
		} else if (data instanceof Boolean) {
			sb.append(String.format("<%s>%s</%s>", field, data, field));
		}

		return this;
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}
