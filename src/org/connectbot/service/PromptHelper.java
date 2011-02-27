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

package org.connectbot.service;

import java.util.concurrent.Semaphore;

import android.os.Handler;
import android.os.Message;

/**
 * Helps provide a relay for prompts and responses between a possible user
 * interface and some underlying service.
 *
 * @author jsharkey
 */
public class PromptHelper {
	private final Object mTag;

	private Handler mHandler = null;

	private Semaphore mPromptToken;
	private Semaphore mPromptResponse;

	public String promptInstructions = null;
	public String promptHint = null;
	public Object promptRequested = null;

	private Object response = null;

	public PromptHelper(Object tag) {
		mTag = tag;

		// Threads must acquire this before they can send a prompt.
		mPromptToken = new Semaphore(1);

		// Responses will release this semaphore.
		mPromptResponse = new Semaphore(0);
	}


	/**
	 * Register a user interface handler, if available.
	 */
	public void setHandler(Handler handler) {
		mHandler = handler;
	}

	/**
	 * Set an incoming value from an above user interface. Will automatically
	 * notify any waiting requests.
	 */
	public void setResponse(Object value) {
		response = value;
		promptRequested = null;
		promptInstructions = null;
		promptHint = null;
		mPromptResponse.release();
	}

	/**
	 * Return the internal response value just before erasing and returning it.
	 */
	protected Object popResponse() {
		Object value = response;
		response = null;
		return value;
	}


	/**
	 * Request a prompt response from parent. This is a blocking call until user
	 * interface returns a value.
	 * Only one thread can call this at a time. cancelPrompt() will force this to
	 * immediately return.
	 */
	private Object requestPrompt(String instructions, String hint, Object type) throws InterruptedException {
		Object response = null;

		mPromptToken.acquire();

		try {
			promptInstructions = instructions;
			promptHint = hint;
			promptRequested = type;

			// notify any parent watching for live events
			if (mHandler != null)
				Message.obtain(mHandler, -1, mTag).sendToTarget();

			// acquire lock until user passes back value
			mPromptResponse.acquire();

			response = popResponse();
		} finally {
			mPromptToken.release();
		}

		return response;
	}

	/**
	 * Request a string response from parent. This is a blocking call until user
	 * interface returns a value.
	 * @param hint prompt hint for user to answer
	 * @return string user has entered
	 */
	public String requestStringPrompt(String instructions, String hint) {
		String value = null;
		try {
			value = (String)this.requestPrompt(instructions, hint, String.class);
		} catch(Exception e) {
		}
		return value;
	}

	/**
	 * Request a boolean response from parent. This is a blocking call until user
	 * interface returns a value.
	 * @param hint prompt hint for user to answer
	 * @return choice user has made (yes/no)
	 */
	public Boolean requestBooleanPrompt(String instructions, String hint) {
		Boolean value = null;
		try {
			value = (Boolean)this.requestPrompt(instructions, hint, Boolean.class);
		} catch(Exception e) {
		}
		return value;
	}

	/**
	 * Cancel an in-progress prompt.
	 */
	public void cancelPrompt() {
		if (!mPromptToken.tryAcquire()) {
			// A thread has the token, so try to interrupt it
			response = null;
			mPromptResponse.release();
		} else {
			// No threads have acquired the token
			mPromptToken.release();
		}
	}
}
