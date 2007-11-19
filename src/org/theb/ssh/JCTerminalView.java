package org.theb.ssh;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetricsInt;
import android.util.Log;
import android.view.View;

import com.jcraft.jcterm.Emulator;
import com.jcraft.jcterm.EmulatorVT100;
import com.jcraft.jcterm.Term;
import com.trilead.ssh2.Session;

public class JCTerminalView extends View implements Term {
	private final Paint mPaint;
	Bitmap mBitmap;
	Canvas mCanvas;
	
	OutputStream out;
	InputStream in;
	Emulator emulator = null;
	
	private boolean mBold = false;
	private boolean mUnderline = false;
	private boolean mReverse = false;
	
	private int mDefaultForeground = Color.WHITE;
	private int mDefaultBackground = Color.BLACK;
	private int mForeground = Color.WHITE;
	private int mBackground = Color.BLACK;
	
	private boolean mAntialias = true;
	
	private int mTermWidth = 80;
	private int mTermHeight = 24;
	
	private int mCharHeight;
	private int mCharWidth;
	private int mDescent;
	private int mLineSpace = -2;
	
	private int x = 0;
	private int y = 0;
	
	private final Object[] mColors = {Color.BLACK, Color.RED, Color.GREEN, Color.YELLOW,
			Color.BLUE, Color.MAGENTA, Color.CYAN, Color.WHITE};
	
	private Session session;

	public JCTerminalView(Context c) {
		super(c);
		mPaint = new Paint();
		mPaint.setAntiAlias(mAntialias);
		mPaint.setColor(mDefaultForeground);
		
		setFont(Typeface.MONOSPACE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mBitmap != null)
			canvas.drawBitmap(mBitmap, 0, 0, null);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		Log.d("SSH/TerminalView", "onSizeChanged called");
		Bitmap newBitmap = Bitmap.createBitmap(w, h, false);
		Canvas newCanvas = new Canvas();
		
		newCanvas.setDevice(newBitmap);
		
		if (mBitmap != null)
			newCanvas.drawBitmap(mBitmap, 0, 0, mPaint);
		
		mBitmap = newBitmap;
		mCanvas = newCanvas;
		
		setSize(w, h);
	}
	
	private void setSize(int w, int h) {
		int column = w / getCharWidth();
		int row = h / getCharHeight();
		
		mTermWidth = column;
		mTermHeight = row;
		
		if (emulator != null)
			emulator.reset();
		
		clear_area(0, 0, w, h);
		
		// TODO: finish this method
	}

	private void setFont(Typeface typeface) {
		mPaint.setTypeface(typeface);
		mPaint.setTextSize(8);
		FontMetricsInt fm = mPaint.getFontMetricsInt();
		mDescent = fm.descent;
		
		float[] widths = new float[1];
		mPaint.getTextWidths("X", widths);
		mCharWidth = (int)widths[0];
		
		// Is this right?
		mCharHeight = Math.abs(fm.top) + Math.abs(fm.descent);
		Log.d("SSH", "character height is " + mCharHeight);
		// mCharHeight += mLineSpace * 2;
		// mDescent += mLineSpace;
	}

	public void beep() {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		mPaint.setColor(getBackgroundColor());
		mCanvas.drawRect(0, 0, mCanvas.getBitmapWidth(),
				mCanvas.getBitmapHeight(), mPaint);
		mPaint.setColor(getForegroundColor());
	}

	private int getBackgroundColor() {
		if (mReverse)
			return mForeground;
		return mBackground;
	}
	
	private int getForegroundColor() {
		if (mReverse)
			return mBackground;
		return mForeground;
	}

	public void clear_area(int x1, int y1, int x2, int y2) {
		mPaint.setColor(getBackgroundColor());
		if (mCanvas != null)
			mCanvas.drawRect(x1, y1, x2, y2, mPaint);
		mPaint.setColor(getForegroundColor());
	}

	public void drawBytes(byte[] buf, int s, int len, int x, int y) {
		String chars = null;
		try {
			chars = new String(buf, "ASCII");
			drawString(chars.substring(s, s+len), x, y);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Log.e("SSH", "Can't convert bytes to ASCII");
		}
	}

	public void drawString(String str, int x, int y) {
		// Log.d("SSH", "at (" + x + "," + y + ") drawing string: " + str);
		mCanvas.drawText(str, x, y - mDescent, mPaint);
		if (mBold)
			mCanvas.drawText(str, x + 1, y - mDescent, mPaint);
		
		if (mUnderline)
			mCanvas.drawLine(x, y - 1, x + str.length() * mCharWidth, y - 1, mPaint);
	}

	public void draw_cursor() {
		// TODO Auto-generated method stub
		
	}

	public int getCharHeight() {
		return mCharHeight;
	}

	public int getCharWidth() {
		return mCharWidth;
	}

	public Object getColor(int index) {
		if (mColors == null || index < 0 || mColors.length <= index)
			return null;
		return mColors[index];
	}

	public int getColumnCount() {
		return mTermWidth;
	}

	public int getRowCount() {
		return mTermHeight;
	}

	public int getTermHeight() {
		return mTermHeight * mCharHeight;
	}

	public int getTermWidth() {
		return mTermWidth * mCharWidth;
	}

	public void redraw(int x, int y, int width, int height) {
		// invalidate(x, y, x+width, y+height);
		postInvalidate();
	}

	public void resetAllAttributes() {
		mBold = false;
		mUnderline = false;
		mReverse = false;
		
		mBackground = mDefaultBackground;
		mForeground = mDefaultForeground;
		
		if (mPaint != null)
			mPaint.setColor(mForeground);
	}

	public void scroll_area(int x, int y, int w, int h, int dx, int dy) {
		// TODO: make scrolling more efficient (memory-wise)
		mCanvas.drawBitmap(Bitmap.createBitmap(mBitmap, x, y, w, h), x+dx, y+dy, mPaint);
	}

	private int toColor(Object o) {
		if (o instanceof Integer) {
			return ((Integer)o).intValue();
		}
		
		if (o instanceof String) {
			return Color.parseColor((String)o);
		}
		
		return Color.WHITE;
	}
	
	public void setBackGround(Object background) {
		mBackground = toColor(background);
	}

	public void setBold() {
		mBold = true;
	}

	public void setCursor(int x, int y) {
		//Log.d("SSH/setCursor", "Cursor placed at (" + x + ", " + y + ")");
		this.x = x;
		this.y = y;
	}

	public void setDefaultBackGround(Object background) {
		mDefaultBackground = toColor(background);
	}

	public void setDefaultForeGround(Object foreground) {
		mDefaultForeground = toColor(foreground);
	}

	public void setForeGround(Object foreground) {
		mForeground = toColor(foreground);
	}

	public void setReverse() {
		mReverse = true;
		if (mPaint != null)
			mPaint.setColor(getForegroundColor());
	}

	public void setUnderline() {
		mUnderline = true;
	}

	public void start(Session session) {
		this.session = session;
		in = session.getStdout();
		out = session.getStdin();
		
		emulator = new EmulatorVT100(this, in);
		emulator.reset();
		emulator.start();
		
		clear();
	}

}
