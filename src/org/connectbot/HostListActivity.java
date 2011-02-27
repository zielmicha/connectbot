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
import org.connectbot.service.TerminalBridge;
import org.connectbot.service.TerminalManager;
import org.connectbot.transport.TransportFactory;
import org.connectbot.util.HostDatabase;
import org.connectbot.util.PreferenceConstants;
import org.connectbot.util.UpdateHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.nullwire.trace.ExceptionHandler;

public class HostListActivity extends ListActivity {
	public final static int REQUEST_EDIT = 1;

	public final static int REQUEST_EULA = 2;

	protected TerminalManager bound = null;

	protected HostDatabase hostdb;
	private List<HostBean> hosts;
	protected LayoutInflater inflater = null;

	protected boolean sortedByColor = false;

	private MenuItem sortcolor;

	private MenuItem sortlast;

	private Spinner transportSpinner;
	private TextView quickconnect;

	private SharedPreferences prefs = null;

	protected boolean makingShortcut = false;

	protected Handler updateHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			HostListActivity.this.updateList();
		}
	};

	private ServiceConnection connection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			bound = ((TerminalManager.TerminalBinder) service).getService();

			// update our listview binder to find the service
			HostListActivity.this.updateList();
		}

		public void onServiceDisconnected(ComponentName className) {
			bound = null;
			HostListActivity.this.updateList();
		}
	};

	@Override
	public void onStart() {
		super.onStart();

		// start the terminal manager service
		this.bindService(new Intent(this, TerminalManager.class), connection, Context.BIND_AUTO_CREATE);

		if(this.hostdb == null)
			this.hostdb = new HostDatabase(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		this.unbindService(connection);

		if(this.hostdb != null) {
			this.hostdb.close();
			this.hostdb = null;
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		ExceptionHandler.checkForTraces(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_EULA) {
			if(resultCode == Activity.RESULT_OK) {
				// yay they agreed, so store that info
				Editor edit = prefs.edit();
				edit.putBoolean(PreferenceConstants.EULA, true);
				edit.commit();
			} else {
				// user didnt agree, so close
				this.finish();
			}
		} else if (requestCode == REQUEST_EDIT) {
			this.updateList();
		}
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.act_hostlist);

		this.setTitle(String.format("%s: %s",
				getResources().getText(R.string.app_name),
				getResources().getText(R.string.title_hosts_list)));

		ExceptionHandler.register(this);

		// check for eula agreement
		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

		boolean agreed = prefs.getBoolean(PreferenceConstants.EULA, false);
		if(!agreed) {
			this.startActivityForResult(new Intent(this, WizardActivity.class), REQUEST_EULA);
		}

		// start thread to check for new version
		new UpdateHelper(this);

		this.makingShortcut = Intent.ACTION_CREATE_SHORTCUT.equals(getIntent().getAction())
								|| Intent.ACTION_PICK.equals(getIntent().getAction());

		// connect with hosts database and populate list
		this.hostdb = new HostDatabase(this);
		ListView list = this.getListView();

		this.sortedByColor = prefs.getBoolean(PreferenceConstants.SORT_BY_COLOR, false);

		//this.list.setSelector(R.drawable.highlight_disabled_pressed);

		list.setOnItemClickListener(new OnItemClickListener() {

			public synchronized void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				// launch off to console details
				HostBean host = (HostBean) parent.getAdapter().getItem(position);
				Uri uri = host.getUri();

				Intent contents = new Intent(Intent.ACTION_VIEW, uri);
				contents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				if (makingShortcut) {
					// create shortcut if requested
					ShortcutIconResource icon = Intent.ShortcutIconResource.fromContext(HostListActivity.this, R.drawable.icon);

					Intent intent = new Intent();
					intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, contents);
					intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, host.getNickname());
					intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

					setResult(RESULT_OK, intent);
					finish();

				} else {
					// otherwise just launch activity to show this host
					HostListActivity.this.startActivity(contents);
				}
			}
		});

		this.registerForContextMenu(list);

		quickconnect = (TextView) this.findViewById(R.id.front_quickconnect);
		quickconnect.setVisibility(makingShortcut ? View.GONE : View.VISIBLE);
		quickconnect.setOnKeyListener(new OnKeyListener() {

			public boolean onKey(View v, int keyCode, KeyEvent event) {

				if(event.getAction() == KeyEvent.ACTION_UP) return false;
				if(keyCode != KeyEvent.KEYCODE_ENTER) return false;

				return startConsoleActivity();
			}
		});

		transportSpinner = (Spinner)findViewById(R.id.transport_selection);
		transportSpinner.setVisibility(makingShortcut ? View.GONE : View.VISIBLE);
		ArrayAdapter<String> transportSelection = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, TransportFactory.getTransportNames());
		transportSelection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		transportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
				String formatHint = TransportFactory.getFormatHint(
						(String) transportSpinner.getSelectedItem(),
						HostListActivity.this);

				quickconnect.setHint(formatHint);
				quickconnect.setError(null);
				quickconnect.requestFocus();
			}
			public void onNothingSelected(AdapterView<?> arg0) { }
		});
		transportSpinner.setAdapter(transportSelection);

		this.inflater = LayoutInflater.from(this);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);

		// don't offer menus when creating shortcut
		if (makingShortcut) return true;

		sortcolor.setVisible(!sortedByColor);
		sortlast.setVisible(sortedByColor);

		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		// don't offer menus when creating shortcut
		if(makingShortcut) return true;

		// add host, ssh keys, about
		sortcolor = menu.add(R.string.list_menu_sortcolor);
		sortcolor.setIcon(android.R.drawable.ic_menu_share);
		sortcolor.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				sortedByColor = true;
				updateList();
				return true;
			}
		});

		sortlast = menu.add(R.string.list_menu_sortname);
		sortlast.setIcon(android.R.drawable.ic_menu_share);
		sortlast.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				sortedByColor = false;
				updateList();
				return true;
			}
		});

		MenuItem keys = menu.add(R.string.list_menu_pubkeys);
		keys.setIcon(android.R.drawable.ic_lock_lock);
		keys.setIntent(new Intent(HostListActivity.this, PubkeyListActivity.class));

		MenuItem colors = menu.add("Colors");
		colors.setIcon(android.R.drawable.ic_menu_slideshow);
		colors.setIntent(new Intent(HostListActivity.this, ColorsActivity.class));

		MenuItem settings = menu.add(R.string.list_menu_settings);
		settings.setIcon(android.R.drawable.ic_menu_preferences);
		settings.setIntent(new Intent(HostListActivity.this, SettingsActivity.class));

		MenuItem help = menu.add(R.string.title_help);
		help.setIcon(android.R.drawable.ic_menu_help);
		help.setIntent(new Intent(HostListActivity.this, HelpActivity.class));

		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

		// create menu to handle hosts

		// create menu to handle deleting and sharing lists
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		final HostBean host = (HostBean) this.getListView().getItemAtPosition(info.position);

		menu.setHeaderTitle(host.getNickname());

		// edit, disconnect, delete
		MenuItem connect = menu.add(R.string.list_host_disconnect);
		final TerminalBridge bridge = bound.getConnectedBridge(host);
		connect.setEnabled((bridge != null));
		connect.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				bridge.dispatchDisconnect(true);
				updateHandler.sendEmptyMessage(-1);
				return true;
			}
		});

		MenuItem edit = menu.add(R.string.list_host_edit);
		edit.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				Intent intent = new Intent(HostListActivity.this, HostEditorActivity.class);
				intent.putExtra(Intent.EXTRA_TITLE, host.getId());
				HostListActivity.this.startActivityForResult(intent, REQUEST_EDIT);
				return true;
			}
		});

		MenuItem portForwards = menu.add(R.string.list_host_portforwards);
		portForwards.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				Intent intent = new Intent(HostListActivity.this, PortForwardListActivity.class);
				intent.putExtra(Intent.EXTRA_TITLE, host.getId());
				HostListActivity.this.startActivityForResult(intent, REQUEST_EDIT);
				return true;
			}
		});
		if (!TransportFactory.canForwardPorts(host.getProtocol()))
			portForwards.setEnabled(false);

		MenuItem delete = menu.add(R.string.list_host_delete);
		delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				// prompt user to make sure they really want this
				new AlertDialog.Builder(HostListActivity.this)
					.setMessage(getString(R.string.delete_message, host.getNickname()))
					.setPositiveButton(R.string.delete_pos, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						// make sure we disconnect
							if(bridge != null)
								bridge.dispatchDisconnect(true);

							hostdb.deleteHost(host);
							updateHandler.sendEmptyMessage(-1);
						}
						})
					.setNegativeButton(R.string.delete_neg, null).create().show();

				return true;
			}
		});
	}

	/**
	 * Starts the activity to connect to the currently selected host. This
	 * should not be called from the UI thread since it accesses the db
	 * which accesses the disk.
	 *
	 * @return true if it successfully started the activity
	 */
	private boolean startConsoleActivity() {
		Uri uri = TransportFactory.getUri((String) transportSpinner
				.getSelectedItem(), quickconnect.getText().toString());

		if (uri == null) {
			quickconnect.setError(getString(R.string.list_format_error,
					TransportFactory.getFormatHint(
							(String) transportSpinner.getSelectedItem(),
							HostListActivity.this)));
			return false;
		}

		HostBean host = TransportFactory.findHost(hostdb, uri);
		if (host == null) {
			host = TransportFactory.getTransport(uri.getScheme()).createHost(uri);
			host.setColor(HostDatabase.COLOR_GRAY);
			host.setPubkeyId(HostDatabase.PUBKEYID_ANY);
			hostdb.saveHost(host);
		}

		Intent intent = new Intent(HostListActivity.this, ConsoleActivity.class);
		intent.setData(uri);
		startActivity(intent);

		return true;
	}

	/**
	 * Update the list view with all the hosts we have in our database. This
	 * should not be called from the UI thread since the database hits the
	 * disk.
	 */
	protected void updateList() {
		if (prefs.getBoolean(PreferenceConstants.SORT_BY_COLOR, false) != sortedByColor) {
			Editor edit = prefs.edit();
			edit.putBoolean(PreferenceConstants.SORT_BY_COLOR, sortedByColor);
			edit.commit();
		}

		if (hostdb == null) {
			hostdb = new HostDatabase(this);
		}

		hosts = hostdb.getHosts(sortedByColor);

		// Don't lose hosts that are connected via shortcuts but not in the database.
		if (bound != null) {
			for (TerminalBridge bridge : bound.bridges) {
				if (!hosts.contains(bridge.host))
					hosts.add(0, bridge.host);
			}
		}

		HostAdapter adapter = new HostAdapter(this, hosts, bound);

		setListAdapter(adapter);
	}
}
