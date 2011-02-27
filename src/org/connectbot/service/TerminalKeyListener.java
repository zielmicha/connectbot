/*
 * ConnectBot: simple, powerful, open-source SSH client for Android
 * Copyright 2010 Kenny Root, Jeffrey Sharkey
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
package org.connectbot.service;

import org.connectbot.TerminalView;
import org.connectbot.bean.SelectionArea;
import org.connectbot.util.PreferenceConstants;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import de.mud.terminal.vt320;

/**
 * @author Kenny Root
 *
 */
public class TerminalKeyListener implements OnKeyListener, OnSharedPreferenceChangeListener {
	private static final String TAG = "ConnectBot.OnKeyListener";

	public final static int META_CTRL_ON = 0x01;
	public final static int META_CTRL_LOCK = 0x02;
	public final static int META_ALT_ON = 0x04;
	public final static int META_ALT_LOCK = 0x08;
	public final static int META_SHIFT_ON = 0x10;
	public final static int META_SHIFT_LOCK = 0x20;
	public final static int META_SLASH = 0x40;
	public final static int META_TAB = 0x80;

	// The bit mask of momentary and lock states for each
	public final static int META_CTRL_MASK = META_CTRL_ON | META_CTRL_LOCK;
	public final static int META_ALT_MASK = META_ALT_ON | META_ALT_LOCK;
	public final static int META_SHIFT_MASK = META_SHIFT_ON | META_SHIFT_LOCK;

	// All the transient key codes
	public final static int META_TRANSIENT = META_CTRL_ON | META_ALT_ON | META_SHIFT_ON;

	private final TerminalKeyReceiver mReceiver;

	private String mKeymode = null;
	private boolean mHardKeyboard = false;

	private int mMetaState = 0;

	private int mDeadKey = 0;

	private boolean mSelectingForCopy = false;
	private final SelectionArea mSelectionArea;

	private final SharedPreferences mPrefs;

	public TerminalKeyListener(Context context, TerminalKeyReceiver receiver) {
		mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		mPrefs.registerOnSharedPreferenceChangeListener(this);

		mHardKeyboard = (context.getResources().getConfiguration().keyboard == Configuration.KEYBOARD_QWERTY);

		mReceiver = receiver;
		mSelectionArea = new SelectionArea();
		updateKeymode();
	}

	/**
	 * Handle onKey() events coming down from a {@link TerminalView} above us.
	 * Modify the keys to make more sense to a host then pass it to the
	 * transport.
	 */
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// Ignore all key-up events except for the special keys
		if (event.getAction() == KeyEvent.ACTION_UP) {
			// There's nothing here for virtual keyboard users.
			if (!mHardKeyboard)
				return false;

			if (PreferenceConstants.KEYMODE_RIGHT.equals(mKeymode)) {
				if (keyCode == KeyEvent.KEYCODE_ALT_RIGHT && (mMetaState & META_SLASH) != 0) {
					mMetaState &= ~(META_SLASH | META_TRANSIENT);
					mReceiver.sendKey('/');
					return true;
				} else if (keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT && (mMetaState & META_TAB) != 0) {
					mMetaState &= ~(META_TAB | META_TRANSIENT);
					mReceiver.sendKey(0x09);
					return true;
				}
			} else if (PreferenceConstants.KEYMODE_LEFT.equals(mKeymode)) {
				if (keyCode == KeyEvent.KEYCODE_ALT_LEFT && (mMetaState & META_SLASH) != 0) {
					mMetaState &= ~(META_SLASH | META_TRANSIENT);
					mReceiver.sendKey('/');
					return true;
				} else if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT && (mMetaState & META_TAB) != 0) {
					mMetaState &= ~(META_TAB | META_TRANSIENT);
					mReceiver.sendKey(0x09);
					return true;
				}
			}

			return false;
		}

		// check for terminal resizing keys
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			mReceiver.increaseFontSize();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			mReceiver.decreaseFontSize();
			return true;
		}

		if (keyCode == KeyEvent.KEYCODE_UNKNOWN && event.getAction() == KeyEvent.ACTION_MULTIPLE) {
			mReceiver.sendString(event.getCharacters());
			return true;
		}

		int curMetaState = event.getMetaState();

		if ((mMetaState & META_SHIFT_MASK) != 0) {
			curMetaState |= KeyEvent.META_SHIFT_ON;
		}

		if ((mMetaState & META_ALT_MASK) != 0) {
			curMetaState |= KeyEvent.META_ALT_ON;
		}

		int key = event.getUnicodeChar(curMetaState);

		if ((key & KeyCharacterMap.COMBINING_ACCENT) != 0) {
			mDeadKey = key & KeyCharacterMap.COMBINING_ACCENT_MASK;
			return true;
		}

		if (mDeadKey != 0) {
			key = KeyCharacterMap.getDeadChar(mDeadKey, keyCode);
			mDeadKey = 0;
		}

		// try handling keymode shortcuts
		if (mHardKeyboard && event.getRepeatCount() == 0) {
			if (PreferenceConstants.KEYMODE_RIGHT.equals(mKeymode)) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_ALT_RIGHT:
					mMetaState |= META_SLASH;
					return true;
				case KeyEvent.KEYCODE_SHIFT_RIGHT:
					mMetaState |= META_TAB;
					return true;
				case KeyEvent.KEYCODE_SHIFT_LEFT:
					metaPress(META_SHIFT_ON);
					return true;
				case KeyEvent.KEYCODE_ALT_LEFT:
					metaPress(META_ALT_ON);
					return true;
				}
			} else if (PreferenceConstants.KEYMODE_LEFT.equals(mKeymode)) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_ALT_LEFT:
					mMetaState |= META_SLASH;
					return true;
				case KeyEvent.KEYCODE_SHIFT_LEFT:
					mMetaState |= META_TAB;
					return true;
				case KeyEvent.KEYCODE_SHIFT_RIGHT:
					metaPress(META_SHIFT_ON);
					return true;
				case KeyEvent.KEYCODE_ALT_RIGHT:
					metaPress(META_ALT_ON);
					return true;
				}
			} else {
				switch (keyCode) {
				case KeyEvent.KEYCODE_ALT_LEFT:
				case KeyEvent.KEYCODE_ALT_RIGHT:
					metaPress(META_ALT_ON);
					return true;
				case KeyEvent.KEYCODE_SHIFT_LEFT:
				case KeyEvent.KEYCODE_SHIFT_RIGHT:
					metaPress(META_SHIFT_ON);
					return true;
				}
			}
		}

		// look for special chars
		switch (keyCode) {
		case KeyEvent.KEYCODE_CAMERA:

			// check to see which shortcut the camera button triggers
			String camera = mPrefs.getString(PreferenceConstants.CAMERA,
					PreferenceConstants.CAMERA_CTRLA_SPACE);
			if (PreferenceConstants.CAMERA_CTRLA_SPACE.equals(camera)) {
				mReceiver.sendKey(0x01);
				mReceiver.sendKey(' ');
			} else if (PreferenceConstants.CAMERA_CTRLA.equals(camera)) {
				mReceiver.sendKey(0x01);
			} else if (PreferenceConstants.CAMERA_ESC.equals(camera)) {
				sendEscape();
			} else if (PreferenceConstants.CAMERA_ESC_A.equals(camera)) {
				sendEscape();
				mReceiver.sendKey('a');
			}

			break;

		case KeyEvent.KEYCODE_DEL:
			mReceiver.sendSpecialKey(vt320.KEY_BACK_SPACE, getStateForBuffer());
			mMetaState &= ~META_TRANSIENT;
			return true;
		case KeyEvent.KEYCODE_ENTER:
			mReceiver.sendKey(vt320.KEY_ENTER);
			mMetaState &= ~META_TRANSIENT;
			return true;

		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (mSelectingForCopy) {
				mSelectionArea.decrementColumn();
				mReceiver.onMetaStateChange();
			} else {
				mReceiver.sendSpecialKey(vt320.KEY_LEFT, getStateForBuffer());
				mMetaState &= ~META_TRANSIENT;
				mReceiver.onKeyFeedback();
			}
			return true;

		case KeyEvent.KEYCODE_DPAD_UP:
			if (mSelectingForCopy) {
				mSelectionArea.decrementRow();
				mReceiver.onMetaStateChange();
			} else {
				mReceiver.sendSpecialKey(vt320.KEY_UP, getStateForBuffer());
				mMetaState &= ~META_TRANSIENT;
				mReceiver.onKeyFeedback();
			}
			return true;

		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (mSelectingForCopy) {
				mSelectionArea.incrementRow();
				mReceiver.onMetaStateChange();
			} else {
				mReceiver.sendSpecialKey(vt320.KEY_DOWN, getStateForBuffer());
				mMetaState &= ~META_TRANSIENT;
				mReceiver.onKeyFeedback();
			}
			return true;

		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (mSelectingForCopy) {
				mSelectionArea.incrementColumn();
				mReceiver.onMetaStateChange();
			} else {
				mReceiver.sendSpecialKey(vt320.KEY_RIGHT, getStateForBuffer());
				mMetaState &= ~META_TRANSIENT;
				mReceiver.onKeyFeedback();
			}
			return true;

		case KeyEvent.KEYCODE_DPAD_CENTER:
			if (mSelectingForCopy) {
				if (mSelectionArea.isSelectingOrigin())
					mSelectionArea.finishSelectingOrigin();
				else {
					mReceiver.copyText(mSelectionArea);
					mSelectingForCopy = false;
					mSelectionArea.reset();
				}
			} else {
				if ((mMetaState & META_CTRL_ON) != 0) {
					mReceiver.sendKey(vt320.KEY_ESCAPE);
					mMetaState &= ~META_CTRL_ON;
				} else
					metaPress(META_CTRL_ON);
			}
			mReceiver.onMetaStateChange();
			return true;
		}

		final boolean printing = (key != 0);

		// otherwise pass through to existing session
		// print normal keys
		if (printing) {
			mMetaState &= ~(META_SLASH | META_TAB);

			// Remove shift and alt modifiers
			final int lastMetaState = mMetaState;
			mMetaState &= ~(META_SHIFT_ON | META_ALT_ON);
			if (mMetaState != lastMetaState) {
				mReceiver.onMetaStateChange();
			}

			if ((mMetaState & META_CTRL_MASK) != 0) {
				mMetaState &= ~META_CTRL_ON;
				mReceiver.onMetaStateChange();

				// If there is no hard keyboard,
				// CTRL-1 through CTRL-9 will send F1 through F9
				if (!mHardKeyboard && sendFunctionKey(keyCode))
					return true;

				// Support CTRL-a through CTRL-z
				if (key >= 0x61 && key <= 0x7A)
					key -= 0x60;
				// Support CTRL-A through CTRL-_
				else if (key >= 0x41 && key <= 0x5F)
					key -= 0x40;
				// CTRL-space sends NULL
				else if (key == 0x20)
					key = 0x00;
				// CTRL-? sends DEL
				else if (key == 0x3F)
					key = 0x7F;
			}

			// handle pressing f-keys
			if (mHardKeyboard && (curMetaState & KeyEvent.META_SHIFT_ON) != 0
					&& sendFunctionKey(keyCode))
				return true;

			if (key < 0x80)
				mReceiver.sendKey(key);
			else
				// TODO write encoding routine that doesn't allocate each time
				mReceiver.sendString(new String(Character.toChars(key)));

			return true;
		}

		return false;
	}

	public void sendEscape() {
		mReceiver.sendKey(vt320.KEY_ESCAPE);
	}

	/**
	 * @param key
	 * @return successful
	 */
	private boolean sendFunctionKey(int keyCode) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_1:
			mReceiver.sendSpecialKey(vt320.KEY_F1, 0);
			return true;
		case KeyEvent.KEYCODE_2:
			mReceiver.sendSpecialKey(vt320.KEY_F2, 0);
			return true;
		case KeyEvent.KEYCODE_3:
			mReceiver.sendSpecialKey(vt320.KEY_F3, 0);
			return true;
		case KeyEvent.KEYCODE_4:
			mReceiver.sendSpecialKey(vt320.KEY_F4, 0);
			return true;
		case KeyEvent.KEYCODE_5:
			mReceiver.sendSpecialKey(vt320.KEY_F5, 0);
			return true;
		case KeyEvent.KEYCODE_6:
			mReceiver.sendSpecialKey(vt320.KEY_F6, 0);
			return true;
		case KeyEvent.KEYCODE_7:
			mReceiver.sendSpecialKey(vt320.KEY_F7, 0);
			return true;
		case KeyEvent.KEYCODE_8:
			mReceiver.sendSpecialKey(vt320.KEY_F8, 0);
			return true;
		case KeyEvent.KEYCODE_9:
			mReceiver.sendSpecialKey(vt320.KEY_F9, 0);
			return true;
		case KeyEvent.KEYCODE_0:
			mReceiver.sendSpecialKey(vt320.KEY_F10, 0);
			return true;
		default:
			return false;
		}
	}

	/**
	 * Handle meta key presses where the key can be locked on.
	 * <p>
	 * 1st press: next key to have meta state<br />
	 * 2nd press: meta state is locked on<br />
	 * 3rd press: disable meta state
	 *
	 * @param code
	 */
	public void metaPress(int code) {
		if ((mMetaState & (code << 1)) != 0) {
			mMetaState &= ~(code << 1);
		} else if ((mMetaState & code) != 0) {
			mMetaState &= ~code;
			mMetaState |= code << 1;
		} else
			mMetaState |= code;
		mReceiver.onMetaStateChange();
	}

	public void setTerminalKeyMode(String keymode) {
		this.mKeymode = keymode;
	}

	private int getStateForBuffer() {
		int bufferState = 0;

		if ((mMetaState & META_CTRL_MASK) != 0)
			bufferState |= vt320.KEY_CONTROL;
		if ((mMetaState & META_SHIFT_MASK) != 0)
			bufferState |= vt320.KEY_SHIFT;
		if ((mMetaState & META_ALT_MASK) != 0)
			bufferState |= vt320.KEY_ALT;

		return bufferState;
	}

	public int getMetaState() {
		return mMetaState;
	}

	public int getDeadKey() {
		return mDeadKey;
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (PreferenceConstants.KEYMODE.equals(key)) {
			updateKeymode();
		}
	}

	private void updateKeymode() {
		mKeymode = mPrefs.getString(PreferenceConstants.KEYMODE, PreferenceConstants.KEYMODE_RIGHT);
	}
}
