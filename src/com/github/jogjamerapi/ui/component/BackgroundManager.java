package com.github.jogjamerapi.ui.component;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class BackgroundManager extends VerticalFieldManager {

	public BackgroundManager() {
		super(NO_HORIZONTAL_SCROLL | NO_VERTICAL_SCROLL | USE_ALL_HEIGHT | USE_ALL_WIDTH );
	}

	protected void sublayout(int width, int height) {
//		 width = Display.getWidth();
//		 height = Display.getHeight();
		super.sublayout(width, height);
		setExtent(width, height);

	}

	protected void paintBackground(Graphics graphics) {
		int[] upperX_PTS = new int[] { 0, 0, getWidth(), getWidth() };
		int[] upperY_PTS = new int[] { 0, getHeight() / 2, getHeight() / 2, 0 };

		int[] lowerX_PTS = new int[] { 0, 0, getWidth(), getWidth() };
		int[] lowerY_PTS = new int[] { getHeight() / 2, getHeight(),
				getHeight(), getHeight() / 2 };

		int[] upperDrawColors = new int[] { 0x2f3233, 0x403434, 0x403434,
				0x2f3233 };
		int[] lowerDrawColors = new int[] { 0x2f3233, 0x403434, 0x403434,
				0x2f3233 };

		graphics.drawShadedFilledPath(upperX_PTS, upperY_PTS, null,
				upperDrawColors, null);
		graphics.drawShadedFilledPath(lowerX_PTS, lowerY_PTS, null,
				lowerDrawColors, null);

	}

}
