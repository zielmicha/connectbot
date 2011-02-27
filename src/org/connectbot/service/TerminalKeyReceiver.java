/**
 *
 */
package org.connectbot.service;

import org.connectbot.bean.SelectionArea;

/**
 * @author Kenny Root
 *
 */
public interface TerminalKeyReceiver {

	/**
	 * @param keyCode single unmodified key to send to terminal
	 */
	void sendKey(int keyCode);

	/**
	 * @param keyCode single key which will have modifiers applied to it
	 * @param modifiers special modifiers for key
	 */
	void sendSpecialKey(int keyCode, int modifiers);

	/**
	 * @param str string ot send to terminal emulator
	 */
	void sendString(String str);

	/**
	 * Increase the font size in terminal listener
	 */
	void increaseFontSize();

	/**
	 * Decreate the font size in terminal listener
	 */
	void decreaseFontSize();

	/**
	 * Called when the meta state of the key listener changes for possible
	 * UI update.
	 */
	void onMetaStateChange();

	/**
	 * Called when there is a key press that should provide feedback to the
	 * user. Currently this is used to indicate a vibration.
	 */
	void onKeyFeedback();

	/**
	 * @param selectionArea
	 */
	void copyText(SelectionArea selectionArea);
}
