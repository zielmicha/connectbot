package org.theb.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;

import org.theb.provider.HostDb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.KeyCharacterMap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

import com.trilead.ssh2.ChannelCondition;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.ConnectionMonitor;
import com.trilead.ssh2.Session;

public class SecureShell extends Activity {
	private TextView mOutput;
	private ConnectionThread mConn;
	private String mBuffer;
	private KeyCharacterMap mKMap;
	
	static final int PASSWORD_REQUEST = 0;
	
	private static final int HOSTNAME_INDEX = 1;
	private static final int USERNAME_INDEX = 2;
	private static final int PORT_INDEX = 3;
	
	// We change the meta state when the user presses the center button.
	private boolean mMetaState = false;
	
	private static final String[] PROJECTION = new String[] {
		HostDb.Hosts._ID, // 0
		HostDb.Hosts.HOSTNAME, // 1
		HostDb.Hosts.USERNAME, // 2
		HostDb.Hosts.PORT, // 3
	};
	
	private Cursor mCursor;
	
	// Terminal window
	private TerminalView mTerminal;
	
	// Store the username, hostname, and port from the database.
	private String mHostname;
	private String mUsername;
	private int mPort;
	
	// The toggle for the original thread to release the indeterminate waiting graphic.
	private ProgressDialog progress;
	private boolean mIsWaiting;
	private String mWaitingTitle;
	private String mWaitingMessage;
	
	// Connection lost reason.
	private String mDisconnectReason;
	
	// This is for the password dialog.
	Semaphore sPass;
	String mPassword = null;
	
	Connection conn;
	Session sess;
	InputStream stdin;
	InputStream stderr;
	OutputStream stdout;
	int x;
	int y;
		
	final Handler mHandler = new Handler();
	
	final Runnable mUpdateView = new Runnable() {
		public void run() {
			updateViewInUI();
		}
	};
	
	class ConnectionThread extends Thread {
		String hostname;
		String username;
		int port;
		
		public ConnectionThread(String hostname, String username, int port) {
			this.hostname = hostname;
			this.username = username;
			this.port = port;
		}
		
		public void run() {
			conn = new Connection(hostname, port);

			conn.addConnectionMonitor(mConnectionMonitor);
			
            setWaiting(true, "Connection",
            		"Connecting to " + hostname + "...");
			
			Log.d("SSH", "Starting connection attempt...");

	        try {
				conn.connect(new InteractiveHostKeyVerifier());

				setWaiting(true, "Authenticating",
						"Trying to authenticate...");
				
				Log.d("SSH", "Starting authentication...");
				
//				boolean enableKeyboardInteractive = true;
//				boolean enableDSA = true;
//				boolean enableRSA = true;
				
				while (true) {
					/*
					if ((enableDSA || enableRSA ) &&
							mConn.isAuthMethodAvailable(username, "publickey");
							*/
					
					if (conn.isAuthMethodAvailable(username, "password")) {
						Log.d("SSH", "Trying password authentication...");
						setWaiting(true, "Authenticating",
								"Trying to authenticate using password...");
						
						// Set a semaphore that is unset by the returning dialog.
						sPass = new Semaphore(0);
						askPassword();
						
						// Wait for the user to answer.
						sPass.acquire();
						sPass = null;
						if (mPassword == null)
							continue;
						
						boolean res = conn.authenticateWithPassword(username, mPassword);
						if (res == true)
							break;
						
						continue;
					}
					
					throw new IOException("No supported authentication methods available.");
				}
				
				Log.d("SSH", "Opening session...");
				setWaiting(true, "Session", "Requesting shell...");
				
				sess = conn.openSession();
				
		        y = mTerminal.getRowCount();
		        x = mTerminal.getColumnCount();
		        Log.d("SSH", "Requesting PTY of size " + x + "x" + y);
		        
				sess.requestPTY("vt100", x, y, mTerminal.getWidth(), mTerminal.getHeight(), null);

				Log.d("SSH", "Requesting shell...");
				sess.startShell();

				stdout = sess.getStdin();
				stderr = sess.getStderr();
				stdin = sess.getStdout();

				setWaiting(false, null, null);
			} catch (IOException e) {
				Log.e("SSH", e.getMessage());
				setWaiting(false, null, null);
				return;
			} catch (InterruptedException e) {
				// This thread is coming to an end. Let us exit.
				Log.e("SSH", "Connection thread interrupted.");
				return;
			}
			
			mTerminal.start(sess);
		}
	}
	
    @Override
    public void onCreate(Bundle savedValues) {
        super.onCreate(savedValues);
        
        requestWindowFeature(Window.FEATURE_PROGRESS);
        mTerminal = new JCTerminalView(this);
        setContentView(mTerminal);
        //mOutput = (TextView) findViewById(R.id.output);
        
        Log.d("SSH", "using URI " + getIntent().getData().toString());
        
        mCursor = managedQuery(getIntent().getData(), PROJECTION, null, null);
        mCursor.first();
        
        mHostname = mCursor.getString(HOSTNAME_INDEX);
        mUsername = mCursor.getString(USERNAME_INDEX);
        mPort = mCursor.getInt(PORT_INDEX);
        
        String title = "SSH: " + mUsername + "@" + mHostname;
        if (mPort != 22)
        	title += Integer.toString(mPort);
        
        this.setTitle(title);
        
        mConn = new ConnectionThread(mHostname, mUsername, mPort);
        
        mKMap = KeyCharacterMap.load(KeyCharacterMap.BUILT_IN_KEYBOARD);

        Log.d("SSH", "Starting new ConnectionThread");
        mConn.start();
    }
    
    public void setWaiting(boolean isWaiting, String title, String message) {
    	mIsWaiting = isWaiting;
    	mWaitingTitle = title;
    	mWaitingMessage = message;
    	mHandler.post(mUpdateWaiting);
    }
    
	final Runnable mUpdateWaiting = new Runnable() {
		public void run() {
	    	if (mIsWaiting) {
	    		if (progress == null)
					progress = ProgressDialog.show(SecureShell.this, mWaitingTitle, mWaitingMessage, true, false);
				else {
	    			progress.setTitle(mWaitingTitle);
	    			progress.setMessage(mWaitingMessage);
	    		}
	    	} else {
	    		if (progress != null) {
	    			progress.dismiss();
	    			progress = null;
	    		}
	    	}
		}
	};

	public String askPassword() {
    	Intent intent = new Intent(this, PasswordDialog.class);
    	this.startSubActivity(intent, PASSWORD_REQUEST);
    	return null;
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            String data, Bundle extras)
	{
	    if (requestCode == PASSWORD_REQUEST) {
	
	        // If the request was cancelled, then we didn't get anything.
	        if (resultCode == RESULT_CANCELED)
				mPassword = "";
			else
	            mPassword = data;
	        
            sPass.release();
	    }
	}
    
	@Override
    public void onDestroy() {
    	super.onDestroy();
    	
    	if (sess != null) {
    		sess.close();
    		sess = null;
    	}
    	
    	if (conn != null) {
    		conn.close();
    		conn = null;
    	}
    	
    	finish();
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
    	if (stdout != null) {
	    	try {
	    		if (mKMap.isPrintingKey(keyCode)
	    				|| keyCode == KeyEvent.KEYCODE_SPACE) {
			    	int c = mKMap.get(keyCode, msg.getMetaState());
			    	if (mMetaState) {
			    		// Support CTRL-A through CTRL-Z
			    		if (c >= 0x61 && c <= 0x79)
			    			c -= 0x60;
			    		else if (c >= 0x40 && c <= 0x59)
			    			c -= 0x39;
			    		mMetaState = false;
			    	}
					stdout.write(c);
	    		} else	if (keyCode == KeyEvent.KEYCODE_DEL) {
					stdout.write(0x08); // CTRL-H
	    		} else if (keyCode == KeyEvent.KEYCODE_NEWLINE
	    				|| keyCode == KeyEvent.KEYCODE_DPAD_LEFT
	    				|| keyCode == KeyEvent.KEYCODE_DPAD_UP
	    				|| keyCode == KeyEvent.KEYCODE_DPAD_DOWN
	    				|| keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
	    			byte[] output = mTerminal.getKeyCode(keyCode);
	    			if (output != null)
	    				stdout.write(output);
	    		} else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
	    			if (mMetaState) {
	    				stdout.write(0x1B); // ESCAPE
	    				mMetaState = false;
	    			} else {
	    				mMetaState = true;
	    			}
	    		} else {
	    			// This is not something we handle.
	    			return super.onKeyDown(keyCode, msg);
	    		}
				return true;
			} catch (IOException e) {
				Log.e("SSH", "Can't write to stdout: "+ e.getMessage());
			}
    	}
		return super.onKeyDown(keyCode, msg);
    }
    
    public void updateViewInUI() {
    	mOutput.setText(mBuffer);
    }

    final Runnable mDisconnectAlert = new Runnable() {
    	public void run() {
    		if (SecureShell.this == null)
    			return;
    		
			AlertDialog d = AlertDialog.show(SecureShell.this,
					"Connection Lost", mDisconnectReason, "Ok", false);
			d.show();
			// TODO: Return to previous activity if connection fails.
	    }
    };
    
    final ConnectionMonitor mConnectionMonitor = new ConnectionMonitor() {
    	public void connectionLost(Throwable reason) {
    		Log.d("SSH", "Connection ended.");
    		mDisconnectReason = reason.getMessage();
    		mHandler.post(mDisconnectAlert);
    	}
    };
}
