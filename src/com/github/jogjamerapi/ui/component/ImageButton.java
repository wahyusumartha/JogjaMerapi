package com.github.jogjamerapi.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Characters;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.BitmapField;

public class ImageButton extends BitmapField {
	private Bitmap unFocusImage;
	private Bitmap FocusImage;
	
	private static final int HORIZONTAN_PADDING = 4; 
	private static final int VERTICAL_PADDING =  8;
	
	
	public ImageButton(Bitmap unFocusImage, Bitmap focusImage) {
		super(null, FOCUSABLE);
		this.unFocusImage = unFocusImage;
		FocusImage = focusImage;
		setBitmap(unFocusImage);
	}


	protected boolean navigationClick(int status, int time) {
		doAction();
		return super.navigationClick(status, time);
	}
	
	
	
	protected boolean keyChar(char character, int status, int time) {
		if(character == Characters.ENTER){ 
			doAction();
			return true;
		}
		return false;
	}


	/* 
	 * Implement this method when you want to add Action on this Component
	 */
	protected void doAction(){
		
	}


	public int getPreferredHeight() {
		return unFocusImage.getHeight() + 2 * VERTICAL_PADDING;
	}


	public int getPreferredWidth() {
		return unFocusImage.getWidth() + 2 * HORIZONTAN_PADDING;
	}


	protected void layout(int width, int height) {
		setExtent(getPreferredWidth(), getPreferredHeight());
	}


	protected void drawFocus(Graphics graphics, boolean on) {

	}


	protected void onFocus(int direction) {
		setBitmap(FocusImage);
		super.onFocus(direction);
		invalidate();
		getScreen().invalidate();
	}


	protected void onUnfocus() {
		setBitmap(unFocusImage);
		super.onUnfocus();
		invalidate();
		getScreen().invalidate();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
