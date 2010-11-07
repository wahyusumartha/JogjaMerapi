package com.github.jogjamerapi.ui.component;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;

public class HeaderField extends Field {

	/*
	 * Setting width and height for this header
	 */
	private int fieldWidth = Display.getWidth();
	private int fieldHeight;

	/*
	 * Variable for text and font that used
	 */
	private String text;
	private Font headerFont;

	/*
	 * Gradient Items
	 */
	private int[] upperX_PTS;
	private int[] upperY_PTS;
	private int[] upperDrawColors;
	private int[] lowerX_PTS;
	private int[] lowerY_PTS;
	private int[] lowerDrawColors;

	public HeaderField(String text) {
		this.text = text;
		headerFont = fieldFont();

		if (fieldWidth < 321) {
			fieldHeight = 45;
		} else {
			fieldHeight = 60;
		}

		/*
		 * Set Coordinate when Drawing Header Gradient Color
		 */
		upperX_PTS = new int[] { 0, 0, fieldWidth, fieldWidth };
		upperY_PTS = new int[] { 0, fieldHeight / 2, fieldHeight / 2, 0 };
		upperDrawColors = new int[] { 0x5d5e61, 0x3d3f45, 0x3d3f45, 0x5d5e61 };
		lowerX_PTS = new int[] { 0, 0, fieldWidth, fieldWidth };
		lowerY_PTS = new int[] { fieldHeight / 2, fieldHeight, fieldHeight,
				fieldHeight / 2 };
		lowerDrawColors = new int[] { 0x2d2f33, 0x1e2126, 0x1e2126, 0x2d2f33 };
	}

	public int getPreferredHeight() {
		return fieldHeight;
	}

	public int getPreferredWidth() {
		return fieldWidth;
	}

	protected void layout(int width, int height) {
		setExtent(getPreferredWidth(), getPreferredHeight());

	}

	protected void paint(Graphics graphics) {
		/*
		 * Drawing gradient color
		 */
		graphics.drawShadedFilledPath(upperX_PTS, upperY_PTS, null,
				upperDrawColors, null);
		graphics.drawShadedFilledPath(lowerX_PTS, lowerY_PTS, null,
				lowerDrawColors, null);



		graphics.setFont(headerFont);
		int textWidth = headerFont.getAdvance(text);
		int yLoc = (fieldHeight - headerFont.getHeight()) / 2;

		/*
		 * Slight Embossed Style
		 */
		graphics.setColor(0x424546);
		graphics.drawText(text, (fieldWidth - textWidth) / 2, yLoc - 2);

		graphics.setColor(0xffffff);
		graphics.drawText(text, (fieldWidth - textWidth) / 2, yLoc);

	}

	private Font fieldFont() {
		if (fieldWidth < 321) {
			try {
				FontFamily fontFamily = FontFamily.forName("System");
				return fontFamily.getFont(Font.BOLD, 18);
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				return Font.getDefault();
			}
		} else {
			try {
				FontFamily fontFamily = FontFamily.forName("System");
				return fontFamily.getFont(Font.BOLD, 28);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return Font.getDefault();
			}
		}
	}

}
