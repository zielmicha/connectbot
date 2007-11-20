package org.theb.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.ConnectionMonitor;
import com.trilead.ssh2.Session;

public class TrileadConnectionThread extends ConnectionThread {
	private String hostname;
	private String username;
	private String password;
	private int port;

	private Connection connection;
	private Session session;

	private InputStream stdOut;
	private OutputStream stdIn;

	private Semaphore sPass;
	
	protected FeedbackUI ui;
	protected Terminal term;
	
	private String disconnectReason;

	public TrileadConnectionThread(FeedbackUI ui, Terminal term, String hostname, String username, int port) {
		super(ui, hostname, username, port);
		this.ui = ui;
		this.term = term;
		this.hostname = hostname;
		this.username = username;
		this.port = port;
	}

	@Override
	public void finish() {
		if (session != null) {
			session.close();
			session = null;
		}

		if (connection != null) {
			connection.close();
			connection = null;
		}
	}

	@Override
	public InputStream getReader() {
		return stdOut;
	}

	@Override
	public OutputStream getWriter() {
		return stdIn;
	}

	@Override
	public void run() {
		connection = new Connection(hostname, port);

		connection.addConnectionMonitor(mConnectionMonitor);

		ui.setWaiting(true, "Connection", "Connecting to " + hostname + "...");

		Log.d("SSH", "Starting connection attempt...");

		try {
			connection.connect(new InteractiveHostKeyVerifier());

			ui.setWaiting(true, "Authenticating", "Trying to authenticate...");

			Log.d("SSH", "Starting authentication...");

			// boolean enableKeyboardInteractive = true;
			// boolean enableDSA = true;
			// boolean enableRSA = true;

			while (true) {
				/*
				 * if ((enableDSA || enableRSA ) &&
				 * mConn.isAuthMethodAvailable(username, "publickey");
				 */

				if (connection.isAuthMethodAvailable(username, "password")) {
					Log.d("SSH", "Trying password authentication...");
					ui.setWaiting(true, "Authenticating",
							"Trying to authenticate using password...");

					// Set a semaphore that is unset by the returning dialog.
					sPass = new Semaphore(0);
					ui.askPassword();

					// Wait for the user to answer.
					sPass.acquire();
					sPass = null;
					if (password == null)
						continue;

					boolean res = connection.authenticateWithPassword(username,
							password);
					password = null;
					if (res == true)
						break;

					continue;
				}

				throw new IOException(
						"No supported authentication methods available.");
			}

			Log.d("SSH", "Opening session...");
			ui.setWaiting(true, "Session", "Requesting shell...");

			session = connection.openSession();

			int y = term.getRowCount();
			int x = term.getColumnCount();
			Log.d("SSH", "Requesting PTY of size " + x + "x" + y);

			session.requestPTY("vt100", x, y, term.getWidth(), term.getHeight(), null);

			Log.d("SSH", "Requesting shell...");
			session.startShell();

			stdIn = session.getStdin();
			// stderr = session.getStderr();
			stdOut = session.getStdout();

			ui.setWaiting(false, null, null);
		} catch (IOException e) {
			Log.e("SSH", e.getMessage());
			ui.setWaiting(false, null, null);
			return;
		} catch (InterruptedException e) {
			// This thread is coming to an end. Let us exit.
			Log.e("SSH", "Connection thread interrupted.");
			return;
		}

		term.start(session);
	}

	@Override
	public void setPassword(String password) {
		if (password == null)
			this.password = "";
		else
			this.password = password;
		sPass.release();
	}
	
    final Runnable mDisconnectAlert = new Runnable() {
    	public void run() {
			AlertDialog d = AlertDialog.show((Context) ui,
					"Connection Lost", disconnectReason, "Ok", false);
			d.show();
			// TODO: Return to previous activity if connection fails.
	    }
    };
    
    final ConnectionMonitor mConnectionMonitor = new ConnectionMonitor() {
    	public void connectionLost(Throwable reason) {
    		Log.d("SSH", "Connection ended.");
    		disconnectReason = reason.getMessage();
    		ui.getHandler().post(mDisconnectAlert);
    	}
    };
}
