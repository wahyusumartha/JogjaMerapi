package com.github.jogjamerapi.ui.component;

import com.github.jogjamerapi.util.image.ImageUtil;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;

public class TransparanMenu extends PopupScreen {

	private static final int HEIGHT = 40;
	private static final int POS_X = 0;
	private static final int POS_Y = Display.getHeight() - HEIGHT;

	private Bitmap bg;
	private HorizontalFieldManager imageManager;

	private ImageUtil imageUtil = new ImageUtil();

	public TransparanMenu() {
		super(new HorizontalFieldManager(NO_HORIZONTAL_SCROLL) {

			public int getPreferredHeight() {
				return getScreen().getPreferredHeight();
			}

			public int getPreferredWidth() {
				return getScreen().getPreferredWidth();
			}

			protected void sublayout(int maxWidth, int maxHeight) {
				super.sublayout(maxWidth, maxHeight);
				setExtent(getPreferredWidth(), getPreferredHeight());
			}
		});

		/*
		 * Take Snapshot from Screen Above
		 */
		bg = new Bitmap(getScreen().getPreferredWidth(), getScreen()
				.getPreferredHeight());
		Display.screenshot(bg, POS_X, POS_Y, getScreen().getPreferredWidth(),
				getScreen().getPreferredHeight());

		/*
		 * Image Button Container
		 */
		imageManager = new HorizontalFieldManager(HORIZONTAL_SCROLL) {

			public int getPreferredHeight() {
				return getScreen().getPreferredHeight();
			}

			public int getPreferredWidth() {
				return getScreen().getPreferredWidth();
			}

			protected void sublayout(int maxWidth, int maxHeight) {
				super.sublayout(maxWidth, maxHeight);
				setExtent(getPreferredWidth(), getPreferredHeight());
			}

			protected void paintBackground(Graphics graphics) {
				int oldColor = graphics.getColor();
				int alpha = graphics.getGlobalAlpha();

				/*
				 * Add background that taken from snapshot
				 */
				graphics.drawBitmap(0, 0, getPreferredWidth(),
						getPreferredHeight(), bg, 0, 0);

				/*
				 * Set Semi Transparant Color
				 */
				graphics.setGlobalAlpha(0x88);
				graphics.setColor(0x000000);
				graphics.fillRect(0, 0, getPreferredWidth(),
						getPreferredHeight());

				graphics.setGlobalAlpha(alpha);
				graphics.setColor(oldColor);
			}

		};
		imageManager.add(new ImageButton(imageUtil.resizeBitmap(Bitmap
				.getBitmapResource("tweet-grey.png"), 35, 35), imageUtil
				.resizeBitmap(Bitmap.getBitmapResource("tweet-blue.png"), 35,
						35)) {

			protected void doAction() {
				Dialog.alert("TEST");
			}

		});
		imageManager.add(new ImageButton(imageUtil.resizeBitmap(Bitmap
				.getBitmapResource("NeedleWhite.png"), 35, 35), imageUtil
				.resizeBitmap(
						Bitmap.getBitmapResource("NeedleLeftYellow2.png"), 35,
						35)) {

			protected void doAction() {
				Dialog.alert("TEST 2");
			}

		});
		imageManager.add(new ImageButton(imageUtil.resizeBitmap(Bitmap
				.getBitmapResource("file-help-icon.png"), 35, 35),
				imageUtil.resizeBitmap(Bitmap
						.getBitmapResource("help-icon.png"), 35, 35)) {

			protected void doAction() {
				Dialog.alert("Ini help");
			}

		});
		add(imageManager);

	}

	public int getPreferredHeight() {
		return HEIGHT;
	}

	public int getPreferredWidth() {
		return 130;
	}

	protected void sublayout(int width, int height) {
		super.sublayout(width, height);
		setPosition((Display.getWidth() - getPreferredWidth()) / 2, POS_Y);
		setExtent(getPreferredWidth(), getPreferredHeight());
	}

	protected boolean keyDown(int keycode, int time) {
		if (keycode == 1769472) {
			close();
			return true;
		}
		invalidate();
		return super.keyDown(keycode, time);
	}

	protected void applyTheme() {
	}

}
