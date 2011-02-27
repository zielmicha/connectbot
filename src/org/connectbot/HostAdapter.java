/*
 * ConnectBot: simple, powerful, open-source SSH client for Android
 * Copyright 2007 Kenny Root, Jeffrey Sharkey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.connectbot;

import java.util.List;

import org.connectbot.bean.HostBean;
import org.connectbot.service.TerminalManager;
import org.connectbot.util.HostDatabase;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Kenny Root
 *
 */
/* package */ class HostAdapter extends ArrayAdapter<HostBean> {
	private static final int STATE_UNKNOWN = 0;
	private static final int STATE_CONNECTED = 1;
	private static final int STATE_DISCONNECTED = 2;

	// Possible states according to the indices of states listed above.
	private static final int[][] IMAGE_STATES = new int[][] {
		new int[] { },
		new int[] { android.R.attr.state_checked },
		new int[] { android.R.attr.state_expanded }
	};

	private static final Object sLock = new Object();
	private static volatile boolean sInitialized = false;

	private static ColorStateList sRed, sGreen, sBlue;

	private List<HostBean> mHosts;
	private final TerminalManager mManager;
	private final LayoutInflater mInflater;

	class ViewHolder {
		public TextView nickname;
		public TextView caption;
		public ImageView icon;
	}

	public HostAdapter(Context context, List<HostBean> hosts, TerminalManager manager) {
		super(context, R.layout.item_host, hosts);

		mInflater = LayoutInflater.from(context);
		mHosts = hosts;
		mManager = manager;

		if (!sInitialized) {
			synchronized (sLock) {
				if (!sInitialized) {
					initializeColorsLocked(context);
				}
			}
		}
	}

	private static void initializeColorsLocked(Context context) {
		sRed = context.getResources().getColorStateList(R.color.red);
		sGreen = context.getResources().getColorStateList(R.color.green);
		sBlue = context.getResources().getColorStateList(R.color.blue);
	}

	/**
	 * Check if we're connected to a terminal with the given host.
	 */
	private int getConnectedState(HostBean host) {
		// always disconnected if we dont have backend service
		if (mManager == null)
			return STATE_UNKNOWN;

		if (mManager.getConnectedBridge(host) != null)
			return STATE_CONNECTED;

		if (mManager.disconnected.contains(host))
			return STATE_DISCONNECTED;

		return STATE_UNKNOWN;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_host, null, false);

			holder = new ViewHolder();

			holder.nickname = (TextView)convertView.findViewById(android.R.id.text1);
			holder.caption = (TextView)convertView.findViewById(android.R.id.text2);
			holder.icon = (ImageView)convertView.findViewById(android.R.id.icon);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final HostBean host = mHosts.get(position);
		if (host == null) {
			// Well, something bad happened. We can't continue.
			Log.e("HostAdapter", "Host bean is null!");

			holder.nickname.setText("Error during lookup");
			holder.caption.setText("see 'adb logcat' for more");
			return convertView;
		}

		holder.nickname.setText(host.getNickname());

		holder.icon.setImageState(IMAGE_STATES[getConnectedState(host)], true);

		final ColorStateList chosen;
		if (HostDatabase.COLOR_RED.equals(host.getColor()))
			chosen = sRed;
		else if (HostDatabase.COLOR_GREEN.equals(host.getColor()))
			chosen = sGreen;
		else if (HostDatabase.COLOR_BLUE.equals(host.getColor()))
			chosen = sBlue;
		else
			chosen = null;

		final Context context = convertView.getContext();

		if (chosen != null) {
			// set color normally if not selected
			holder.nickname.setTextColor(chosen);
			holder.caption.setTextColor(chosen);
		} else {
			// selected, so revert back to default black text
			holder.nickname.setTextAppearance(context, android.R.attr.textAppearanceLarge);
			holder.caption.setTextAppearance(context, android.R.attr.textAppearanceSmall);
		}

		final long now = System.currentTimeMillis() / 1000;

		final String nice;
		if (host.getLastConnect() > 0) {
			int minutes = (int)((now - host.getLastConnect()) / 60);
			if (minutes >= 60) {
				int hours = (minutes / 60);
				if (hours >= 24) {
					int days = (hours / 24);
					nice = context.getString(R.string.bind_days, days);
				} else
					nice = context.getString(R.string.bind_hours, hours);
			} else
				nice = context.getString(R.string.bind_minutes, minutes);
		} else {
			nice = context.getString(R.string.bind_never);
		}

		holder.caption.setText(nice);

		return convertView;
	}
}
